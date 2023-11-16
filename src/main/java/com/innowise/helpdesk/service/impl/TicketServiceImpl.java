package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.mapper.impl.TicketReadMapper;
import com.innowise.helpdesk.repo.TicketRepository;
import com.innowise.helpdesk.repo.UserRepository;
import com.innowise.helpdesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.innowise.helpdesk.util.Constants.INVALID_EMAIL_OR_PASSWORD;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketReadMapper ticketMapper;

    @Override
    public List<TicketReadDto> getTicketsByUser() {
        User user = getUserFromContextHolder();

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
        User user = getUserFromContextHolder();
        return getTicketsByOwnerId(user);
    }

    private User getUserFromContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        return userRepository.findUserByEmail(loggedInUserEmail)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_EMAIL_OR_PASSWORD));
    }

    private List<TicketReadDto> getTicketsByOwnerId(User user) {
        return ticketRepository.findByOwnerId(user).stream()
                .map(ticketMapper::mapToDto)
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

        managerTickets.addAll(newTicketsByEmployees);
        managerTickets.addAll(approvedByManager);

        return sortByUrgencyAndDesiredDate(managerTickets).stream()
                .map(ticketMapper::mapToDto)
                .toList();
    }

    private List<TicketReadDto> getAllEngineerTickets(User user) {
        // 2.3. Engineer can see tickets created by all Employees and Managers in Approved status
        List<Ticket> approvedTickets = ticketRepository.findByState(State.APPROVED);

        // 2.3.2. Engineer can see tickets which have him as an Assignee In Progress and Done statuses
        List<Ticket> assignedTickets = ticketRepository
                .findByAssigneeIdAndStateIn(user, Arrays.asList(State.IN_PROGRESS, State.DONE));

        approvedTickets.addAll(assignedTickets);

        return sortByUrgencyAndDesiredDate(approvedTickets).stream()
                .map(ticketMapper::mapToDto)
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
}