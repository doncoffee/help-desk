package com.innowise.helpdesk.service.tickets;

import com.innowise.helpdesk.dto.HistoryDto;
import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Ticket;

import java.util.List;

public interface HistoryService {

    List<HistoryDto> getEntireHistoryByTicketId(Long ticketId);
    void createTicketHistory(Ticket ticket);
    void editTicketHistory(Ticket ticket);
    void statusChangedHistory(Ticket ticket, State oldState, State newState);
    void fileAttachedHistory(Ticket ticket, String filename);
}
