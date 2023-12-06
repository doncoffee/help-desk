package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.entity.Attachment;
import com.innowise.helpdesk.entity.Category;
import com.innowise.helpdesk.repo.custom.CustomAttachmentRepo;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

import static com.innowise.helpdesk.entity.QAttachment.attachment;

public class CustomAttachmentRepoImpl implements CustomAttachmentRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Attachment> findAttachmentByTicketId(Long ticketId) {
        return Optional.ofNullable(new JPAQuery<Category>(entityManager)
                .select(attachment)
                .from(attachment)
                .where(attachment.ticket.id.eq(ticketId))
                .fetchOne());
    }
}
