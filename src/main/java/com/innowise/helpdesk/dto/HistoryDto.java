package com.innowise.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

import static com.innowise.helpdesk.util.Constants.DATE_TIME_FORMAT_PATTERN;

@Value
@Builder
public class HistoryDto {

    @JsonFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    LocalDateTime date;
    String user;
    String action;
    String description;
}
