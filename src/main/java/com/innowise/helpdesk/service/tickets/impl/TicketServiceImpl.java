package com.innowise.helpdesk.service.tickets.impl;

import com.innowise.helpdesk.dto.TicketCreateEditDto;
import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.entity.*;
import com.innowise.helpdesk.mapper.impl.TicketCreateEditMapper;
import com.innowise.helpdesk.mapper.impl.TicketReadMapper;
import com.innowise.helpdesk.repo.CategoryRepository;
import com.innowise.helpdesk.repo.TicketRepository;
import com.innowise.helpdesk.service.email.EmailService;
import com.innowise.helpdesk.service.tickets.HistoryService;
import com.innowise.helpdesk.service.tickets.TicketService;
import com.innowise.helpdesk.service.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.function.Predicate.not;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final TicketReadMapper ticketReadMapper;
    private final TicketCreateEditMapper ticketCreateEditMapper;
    private final HistoryService historyService;
    private final EmailService emailService;

    @Override
    public List<TicketReadDto> getTicketsByUser() {
        User user = userService.getUserFromContextHolder();

        return switch (user.getRole()) {
            case EMPLOYEE ->
                    getTicketsByOwnerId(user);
            case MANAGER ->
                    getAllManagerTickets(user);
            case ENGINEER ->
                    getAllEngineerTickets(user);
        };
    }

    @Override
    public List<TicketReadDto> getMyTickets() {
        User user = userService.getUserFromContextHolder();
        return getTicketsByOwnerId(user);
    }

    @Override
    public TicketReadDto createTicket(TicketCreateEditDto ticketCreateEditDto,
                                      MultipartFile multipartFile) {
        return Optional.of(ticketCreateEditDto)
                .map(ticketCreateEditMapper::mapToEntity)
                .map(ticket -> {
                    ticket.setOwnerId(userService.getUserFromContextHolder());
                    ticket.setAssigneeId(userService.getUserFromContextHolder());
                    ticket.setCategory(getCategory(ticketCreateEditDto.getCategory()));
                    setChildEntitiesToParent(ticket, ticketCreateEditDto, multipartFile);
                    historyService.createTicketHistory(ticket);
                    return ticket;
                })
                .map(ticketRepository::save)
                .map(ticketReadMapper::mapToDto)
                .orElseThrow();
    }

    @Override
    public Optional<TicketReadDto> editTicket(Long id,
                                              TicketCreateEditDto ticketCreateEditDto,
                                              MultipartFile multipartFile) {
        return ticketRepository.findById(id)
                .map(entity -> {
                    copy(ticketCreateEditDto, entity, multipartFile);
                    historyService.editTicketHistory(entity);
                    return entity;
                })
                .map(ticketRepository::save)
                .map(ticketReadMapper::mapToDto);
    }

    @Override
    public Optional<TicketReadDto> setTicketsState(Long id, State state) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    historyService.statusChangedHistory(ticket, ticket.getState(), state);
                    ticket.setState(state);
                    ticket.setApproverId(userService.getUserFromContextHolder());
//                    emailService.sendEmail(ticket.getOwnerId().getEmail(), id, state);
                    return ticket;
                })
                .map(ticketRepository::save)
                .map(ticketReadMapper::mapToDto);
    }

    private List<TicketReadDto> getTicketsByOwnerId(User user) {
        return sortByUrgencyAndDesiredDate(ticketRepository.findByOwnerId(user)).stream()
                .map(ticketReadMapper::mapToDto)
                .toList();
    }

    private List<TicketReadDto> getAllManagerTickets(User user) {
        // 2.2. Manager can see tickets created by him in all statuses
        List<Ticket> managerTickets = ticketRepository.findByOwnerId(user);

        // 2.2.2. Manager can see tickets created by all Employees in New
        List<Ticket> newTicketsByEmployees = ticketRepository.findByOwnerIdAndState(user, State.NEW);

        // 2.2.3. Manager can see tickets which have him as an Approver in specific statuses
        List<Ticket> approvedByManager = ticketRepository
                .findByApproverIdAndStateIn(user, Arrays.asList(State.APPROVED,
                        State.DECLINED, State.CANCELLED, State.IN_PROGRESS, State.DONE));

        List<Ticket> allTickets = new ArrayList<>();
        allTickets.addAll(managerTickets);
        allTickets.addAll(newTicketsByEmployees);
        allTickets.addAll(approvedByManager);

        // remove duplicates if there are the same from lists
        Set<Ticket> uniqueTickets = new HashSet<>(allTickets);

        // Convert the set back to a list
        List<Ticket> uniqueTicketList = new ArrayList<>(uniqueTickets);

        return sortByUrgencyAndDesiredDate(uniqueTicketList).stream()
                .map(ticketReadMapper::mapToDto)
                .toList();
    }

    private List<TicketReadDto> getAllEngineerTickets(User user) {
        // 2.3. Engineer can see tickets created by all Employees and Managers in Approved status
        List<Ticket> approvedTickets = ticketRepository.findByState(State.APPROVED);

        // 2.3.2. Engineer can see tickets which have him as an Assignee In Progress and Done statuses
        List<Ticket> assignedTickets = ticketRepository
                .findByAssigneeIdAndStateIn(user, Arrays.asList(State.IN_PROGRESS, State.DONE));

        List<Ticket> allTickets = new ArrayList<>();
        allTickets.addAll(approvedTickets);
        allTickets.addAll(assignedTickets);

        // remove duplicates if there are the same from lists
        Set<Ticket> uniqueTickets = new HashSet<>(allTickets);

        // Convert the set back to a list
        List<Ticket> uniqueTicketList = new ArrayList<>(uniqueTickets);

        return sortByUrgencyAndDesiredDate(uniqueTicketList).stream()
                .map(ticketReadMapper::mapToDto)
                .toList();
    }

    private List<Ticket> sortByUrgencyAndDesiredDate(List<Ticket> list) {
        return list.stream()
                .sorted(createUrgencyAndDesiredDateComparator())
                .toList();
    }

    private Comparator<Ticket> createUrgencyAndDesiredDateComparator() {
        return Comparator.comparing((Ticket t) -> switch (t.getUrgency()) {
            case CRITICAL -> 4;
            case HIGH -> 3;
            case AVERAGE -> 2;
            case LOW -> 1;
        }).thenComparing(Ticket::getDesiredResolutionDate).reversed();
    }


    private void copy(TicketCreateEditDto ticketCreateEditDto,
                      Ticket ticket, MultipartFile multipartFile) {
        ticket.setCategory(getCategory(ticketCreateEditDto.getCategory()));
        ticket.setName(ticketCreateEditDto.getName());
        ticket.setDescription(ticketCreateEditDto.getDescription());
        ticket.setUrgency(ticketCreateEditDto.getUrgency());
        ticket.setCreatedOn(LocalDateTime.now());
        ticket.setDesiredResolutionDate(ticketCreateEditDto.getDesiredResolutionDate());
        ticket.setOwnerId(userService.getUserFromContextHolder());
        ticket.setAssigneeId(userService.getUserFromContextHolder());

        setChildEntitiesToParent(ticket, ticketCreateEditDto, multipartFile);
    }

    public Category getCategory(String name) {
        return Optional.ofNullable(name)
                .flatMap(categoryRepository::findCategoryByName)
                .orElse(null);
    }

    private void setChildEntitiesToParent(Ticket ticket,
                                         TicketCreateEditDto object, MultipartFile multipart) {
        List<Comment> commentList = ticket.getComments();
        List<Attachment> attachmentList = ticket.getAttachments();

        Optional.of(object.getComment())
                .filter(comment -> !comment.isEmpty())
                .ifPresent(comment -> commentList.add(Comment.builder()
                        .text(comment)
                        .ticket(ticket)
                        .dateTime(LocalDateTime.now())
                        .user(userService.getUserFromContextHolder())
                        .build()));
        ticket.setComments(commentList);

        Optional.ofNullable(multipart)
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(multipartFile -> {
                    Attachment newAttachment;
                    try {
                        newAttachment = Attachment.builder()
                                .name(multipartFile.getOriginalFilename())
                                .blob(multipartFile.getBytes())
                                .ticket(ticket)
                                .build();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    historyService.fileAttachedHistory(ticket, newAttachment.getName());
                    attachmentList.add(newAttachment);
                    ticket.setAttachments(attachmentList);
                });
    }
}