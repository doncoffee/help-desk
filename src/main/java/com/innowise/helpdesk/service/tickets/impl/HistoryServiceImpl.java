package com.innowise.helpdesk.service.tickets.impl;

import com.innowise.helpdesk.dto.HistoryDto;
import com.innowise.helpdesk.entity.History;
import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.mapper.impl.HistoryMapper;
import com.innowise.helpdesk.repo.HistoryRepository;
import com.innowise.helpdesk.service.tickets.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.innowise.helpdesk.util.Constants.*;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    @Override
    public List<HistoryDto> getEntireHistoryByTicketId(Long ticketId) {
        return historyRepository.findEntireHistoryByTicketId(ticketId)
                .stream().map(historyMapper::mapToDto)
                .toList();
    }

    @Override
    public void createTicketHistory(Ticket ticket) {
        History history = History.builder()
                .action(TICKET_CREATION_HISTORY)
                .description(TICKET_CREATION_HISTORY)
                .ticket(ticket)
                .user(ticket.getOwnerId())
                .dateTime(LocalDateTime.now())
                .build();
        System.out.println(history.getDateTime());
        historyRepository.save(history);
    }

    @Override
    public void editTicketHistory(Ticket ticket) {
        History history = History.builder()
                .action(TICKET_IS_EDITED_HISTORY)
                .description(TICKET_IS_EDITED_HISTORY)
                .ticket(ticket)
                .user(ticket.getOwnerId())
                .dateTime(LocalDateTime.now())
                .build();
        historyRepository.save(history);
    }

    @Override
    public void statusChangedHistory(Ticket ticket, State oldState, State newState) {
        String description = String.format(
                TICKET_STATUS_CHANGING_DESCRIPTION, oldState, newState);
        History history = History.builder()
                .action(TICKET_STATUS_CHANGING_ACTION_HISTORY)
                .description(description)
                .ticket(ticket)
                .user(ticket.getOwnerId())
                .dateTime(LocalDateTime.now())
                .build();
        historyRepository.save(history);
    }

    @Override
    public void fileAttachedHistory(Ticket ticket, String filename) {
        String description = String.format(FILE_ATTACHED_DESCRIPTION_HISTORY, filename);
        History history = History.builder()
                .action(FILE_ACTION_HISTORY)
                .description(description)
                .ticket(ticket)
                .user(ticket.getOwnerId())
                .dateTime(LocalDateTime.now())
                .build();
        historyRepository.save(history);
    }
}
