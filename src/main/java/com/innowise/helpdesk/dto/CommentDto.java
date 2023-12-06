package com.innowise.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

import static com.innowise.helpdesk.util.Constants.*;

@Value
@Builder
public class CommentDto {

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime date;
    String user;
    @Size(max = COMMENT_MAX_SIZE,
            message = COMMENT_VALIDATION_ERROR_SIZE_MESSAGE)
    String text;
}
