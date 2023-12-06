package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.FeedbackDto;
import com.innowise.helpdesk.entity.Feedback;
import com.innowise.helpdesk.mapper.Mapper;
import org.springframework.stereotype.Component;

import static com.innowise.helpdesk.util.Constants.UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS;

@Component
public class FeedbackMapper implements Mapper<Feedback, FeedbackDto> {

    @Override
    public Feedback mapToEntity(FeedbackDto object) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS);
    }

    @Override
    public FeedbackDto mapToDto(Feedback object) {
        return FeedbackDto.builder()
                .dateTime(object.getDateTime())
                .rate(object.getRate())
                .text(object.getText())
                .build();
    }
}
