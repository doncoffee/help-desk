package com.innowise.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Urgency;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.innowise.helpdesk.util.Constants.DATE_TIME_FORMAT_PATTERN;

@Value
@Setter
@Builder
public class TicketOverviewDto {

    String category;
    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime createdOn;
    State state;
    Urgency urgency;
    LocalDate desiredResolutionDate;
    String owner;
    String approver;
    String assignee;
    String description;
}
