package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.entity.History;
import com.innowise.helpdesk.repo.custom.CustomHistoryRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import static com.innowise.helpdesk.entity.QHistory.history;

public class CustomHistoryRepositoryImpl implements CustomHistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<History> findEntireHistoryByTicketId(Long ticketId) {
        return new JPAQuery<History>(entityManager)
                .select(history)
                .from(history)
                .where(history.ticket.id.eq(ticketId))
                .fetch();
    }
}
