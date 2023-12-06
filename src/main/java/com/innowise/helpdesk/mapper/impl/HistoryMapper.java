package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.HistoryDto;
import com.innowise.helpdesk.entity.History;
import com.innowise.helpdesk.mapper.Mapper;
import org.springframework.stereotype.Component;

import static com.innowise.helpdesk.util.Constants.UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS;

@Component
public class HistoryMapper implements Mapper<History, HistoryDto> {

    @Override
    public History mapToEntity(HistoryDto object) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS);
    }

    @Override
    public HistoryDto mapToDto(History object) {
        return HistoryDto.builder()
                .date(object.getDateTime())
                .user(object.getUser().getFirstname()
                        .concat(" ").concat(object.getUser().getLastname()))
                .action(object.getAction())
                .description(object.getDescription())
                .build();
    }
}
