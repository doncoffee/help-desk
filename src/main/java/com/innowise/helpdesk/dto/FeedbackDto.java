package com.innowise.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

import static com.innowise.helpdesk.util.Constants.*;

@Value
@Builder
public class FeedbackDto {

    @NotNull(message = NULL_RATE_VALUE_MESSAGE)
    Integer rate;
    LocalDateTime dateTime;
    @NotBlank(message = BLANK_FEEDBACK_VALIDATION_MESSAGE)
    @Size(max = COMMENT_MAX_SIZE,
            message = COMMENT_VALIDATION_ERROR_SIZE_MESSAGE)
    String text;
}
