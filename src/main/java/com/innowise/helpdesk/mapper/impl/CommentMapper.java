package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.CommentDto;
import com.innowise.helpdesk.entity.Comment;
import com.innowise.helpdesk.mapper.Mapper;
import org.springframework.stereotype.Component;

import static com.innowise.helpdesk.util.Constants.UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS;

@Component
public class CommentMapper implements Mapper<Comment, CommentDto> {

    @Override
    public Comment mapToEntity(CommentDto object) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS);
    }

    @Override
    public CommentDto mapToDto(Comment object) {
        return CommentDto.builder()
                .date(object.getDateTime())
                .user(object.getUser().getFirstname()
                        .concat(" ").concat(object.getUser().getLastname()))
                .text(object.getText())
                .build();
    }
}
