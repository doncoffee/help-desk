package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.TicketOverviewDto;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.mapper.Mapper;
import org.springframework.stereotype.Component;

import static com.innowise.helpdesk.util.Constants.UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS;

@Component
public class TicketOverviewMapper implements Mapper<Ticket, TicketOverviewDto> {

    @Override
    public Ticket mapToEntity(TicketOverviewDto object) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS);
    }

    @Override
    public TicketOverviewDto mapToDto(Ticket object) {
        return TicketOverviewDto.builder()
                .category(object.getCategory().getName())
                .createdOn(object.getCreatedOn())
                .state(object.getState())
                .urgency(object.getUrgency())
                .desiredResolutionDate(object.getDesiredResolutionDate())
                .owner(object.getOwnerId().getFirstname()
                        .concat(" ").concat(object.getOwnerId().getLastname()))
                .approver(object.getApproverId() == null ? null :
                        object.getApproverId().getFirstname()
                        .concat(" ").concat(object.getApproverId().getLastname()))
                .assignee(object.getAssigneeId().getFirstname()
                        .concat(" ").concat(object.getAssigneeId().getLastname()))
                .description(object.getDescription())
                .build();
    }
}
