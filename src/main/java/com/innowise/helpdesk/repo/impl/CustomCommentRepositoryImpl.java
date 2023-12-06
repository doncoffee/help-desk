package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.entity.Comment;
import com.innowise.helpdesk.repo.custom.CustomCommentRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import static com.innowise.helpdesk.entity.QComment.comment;

public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findAllCommentsByTicketId(Long ticketId) {
        return new JPAQuery<Comment>(entityManager)
                .select(comment)
                .from(comment)
                .where(comment.ticket.id.eq(ticketId))
                .fetch();
    }
}
