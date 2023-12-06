package com.innowise.helpdesk.repo.custom;

import com.innowise.helpdesk.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomAttachmentRepo {

    Optional<Attachment> findAttachmentByTicketId(Long ticketId);
}
