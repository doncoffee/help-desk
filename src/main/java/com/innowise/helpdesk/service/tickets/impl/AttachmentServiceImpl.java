package com.innowise.helpdesk.service.tickets.impl;

import com.innowise.helpdesk.dto.AttachmentDto;
import com.innowise.helpdesk.mapper.impl.AttachmentMapper;
import com.innowise.helpdesk.repo.AttachmentRepository;
import com.innowise.helpdesk.service.tickets.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    public Optional<AttachmentDto> findAttachedFile(Long ticketId) {
        return attachmentRepository
                .findAttachmentByTicketId(ticketId)
                .map(attachmentMapper::mapToDto);
    }
}
