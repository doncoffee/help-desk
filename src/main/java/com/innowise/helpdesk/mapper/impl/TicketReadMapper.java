package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.mapper.Mapper;
import org.springframework.stereotype.Component;

import static com.innowise.helpdesk.util.Constants.UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS;

@Component
public class TicketReadMapper implements Mapper<Ticket, TicketReadDto> {

    @Override
    public Ticket mapToEntity(TicketReadDto object) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS);
    }

    @Override
    public TicketReadDto mapToDto(Ticket object) {
        return TicketReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .desiredResolutionDate(object.getDesiredResolutionDate())
                .urgency(object.getUrgency())
                .state(object.getState())
                .build();
    }
}
