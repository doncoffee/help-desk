package com.innowise.helpdesk.service.tickets;

import com.innowise.helpdesk.dto.AttachmentDto;

import java.util.Optional;

public interface AttachmentService {

    Optional<AttachmentDto> findAttachedFile(Long ticketId);
}
