package com.innowise.helpdesk.dto;

import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Urgency;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class TicketReadDto {

    Long id;
    String name;
    LocalDate desiredResolutionDate;
    Urgency urgency;
    State state;
}
