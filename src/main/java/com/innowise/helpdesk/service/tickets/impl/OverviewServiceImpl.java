package com.innowise.helpdesk.service.tickets.impl;

import com.innowise.helpdesk.dto.TicketOverviewDto;
import com.innowise.helpdesk.mapper.impl.TicketOverviewMapper;
import com.innowise.helpdesk.repo.TicketRepository;
import com.innowise.helpdesk.service.tickets.OverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OverviewServiceImpl implements OverviewService {

    private final TicketRepository ticketRepository;
    private final TicketOverviewMapper ticketOverviewMapper;

    @Override
    public Optional<TicketOverviewDto> findById(Long id) {
        return ticketRepository.findById(id)
                .map(ticketOverviewMapper::mapToDto);
    }
}
