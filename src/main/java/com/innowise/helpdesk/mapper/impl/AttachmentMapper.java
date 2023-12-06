package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.AttachmentDto;
import com.innowise.helpdesk.entity.Attachment;
import com.innowise.helpdesk.mapper.Mapper;
import org.springframework.stereotype.Component;

import static com.innowise.helpdesk.util.Constants.UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS;

@Component
public class AttachmentMapper implements Mapper<Attachment, AttachmentDto> {

    @Override
    public Attachment mapToEntity(AttachmentDto object) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_IN_CURRENT_MAPPER_CLASS);
    }

    @Override
    public AttachmentDto mapToDto(Attachment object) {
        return AttachmentDto.builder()
                .name(object.getName())
                .blob(object.getBlob())
                .build();
    }
}
