package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.repo.TicketRepository;
import com.innowise.helpdesk.service.tickets.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TicketServiceImplTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void getTicketsByUser() {
    }

    @Test
    void getMyTickets() {
    }
}