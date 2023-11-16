package com.innowise.helpdesk.service;

import com.innowise.helpdesk.dto.TicketReadDto;

import java.util.List;

public interface TicketService {

    List<TicketReadDto> getTicketsByUser();
    List<TicketReadDto> getMyTickets();
}
