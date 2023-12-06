package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.TicketCreateEditDto;
import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.innowise.helpdesk.util.Constants.UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS;

@Component
public class TicketCreateEditMapper implements Mapper<Ticket, TicketCreateEditDto> {

    @Override
    public Ticket mapToEntity(TicketCreateEditDto object) {
        return Ticket.builder()
                .name(object.getName())
                .description(object.getDescription())
                .urgency(object.getUrgency())
                .createdOn(LocalDateTime.now())
                .state(State.NEW)
                .desiredResolutionDate(object.getDesiredResolutionDate())
                .build();
    }

    @Override
    public TicketCreateEditDto mapToDto(Ticket object) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS);
    }
}
