package com.innowise.helpdesk.service.tickets;

import com.innowise.helpdesk.dto.TicketOverviewDto;

import java.util.Optional;

public interface OverviewService {

    Optional<TicketOverviewDto> findById(Long id);
}
