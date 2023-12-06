package com.innowise.helpdesk.repo;

import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.repo.custom.CustomTicketRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CustomTicketRepository, JpaRepository<Ticket, Long> {
}
