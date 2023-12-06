package com.innowise.helpdesk.service.tickets;

import com.innowise.helpdesk.dto.TicketCreateEditDto;
import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.entity.State;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<TicketReadDto> getTicketsByUser();
    List<TicketReadDto> getMyTickets();
    TicketReadDto createTicket(TicketCreateEditDto ticketCreateEditDto,
                                     MultipartFile multipartFile);
    Optional<TicketReadDto> editTicket(Long id,
                                       TicketCreateEditDto ticketCreateEditDto,
                                       MultipartFile multipartFile);

    Optional<TicketReadDto> setTicketsState(Long id, State state);
}
