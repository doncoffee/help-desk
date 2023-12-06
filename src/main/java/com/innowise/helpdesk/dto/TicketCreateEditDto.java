package com.innowise.helpdesk.dto;

import com.innowise.helpdesk.entity.Urgency;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.innowise.helpdesk.util.Constants.*;

@Value
@Builder
public class TicketCreateEditDto {

    String category;
    @Size(max = TICKET_NAME_MAX_SIZE,
            message = TICKET_NAME_VALIDATION_ERROR_SIZE_MESSAGE)
    String name;
    @Size(max = TICKET_DESCRIPTION_MAX_SIZE,
            message = TICKET_DESCRIPTION_VALIDATION_ERROR_SIZE_MESSAGE)
    String description;
    Urgency urgency;
    @DateTimeFormat(pattern = TICKET_DATE_PATTERN)
    LocalDate desiredResolutionDate;
    @Size(max = COMMENT_MAX_SIZE,
            message = COMMENT_VALIDATION_ERROR_SIZE_MESSAGE)
    String comment;
}
