package com.innowise.helpdesk.controller;

import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.innowise.helpdesk.util.Constants.*;

@RestController
@RequestMapping(TICKET_CONTROLLER_PATH)
@CrossOrigin(origins = UI_URL)
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketReadDto>> findAllTickets() {
        List<TicketReadDto> tickets = ticketService.getTicketsByUser();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping(FIND_MY_TICKETS_URL)
    public ResponseEntity<List<TicketReadDto>> findMyTickets() {
        List<TicketReadDto> tickets = ticketService.getMyTickets();
        return ResponseEntity.ok(tickets);
    }
}
