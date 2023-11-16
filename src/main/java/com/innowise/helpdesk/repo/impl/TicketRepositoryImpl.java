package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.entity.Urgency;
import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.repo.custom.TicketRepositoryCustom;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import static com.innowise.helpdesk.entity.QTicket.ticket;

public class TicketRepositoryImpl implements TicketRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ticket> findByOwnerId(User user) {
        return new JPAQuery<Ticket>(entityManager)
                .select(ticket)
                .from(ticket)
                .where(ticket.ownerId.id.eq(user.getId()))
                .orderBy(orderByUrgency(), ticket.desiredResolutionDate.desc())
                .fetch();
    }

    @Override
    public List<Ticket> findByOwnerIdAndState(User user, State state) {
        return new JPAQuery<Ticket>(entityManager)
                .select(ticket)
                .from(ticket)
                .where(ticket.ownerId.id.eq(user.getId())
                        .and(ticket.state.eq(state)))
                .fetch();
    }

    @Override
    public List<Ticket> findByApproverIdAndStateIn(User user, List<State> states) {
        return new JPAQuery<Ticket>(entityManager)
                .select(ticket)
                .from(ticket)
                .where(ticket.approverId.id.eq(user.getId())
                        .and(ticket.state.in(states)))
                .fetch();
    }

    @Override
    public List<Ticket> findByState(State state) {
        return new JPAQuery<Ticket>(entityManager)
                .select(ticket)
                .from(ticket)
                .where(ticket.state.eq(state))
                .fetch();
    }

    @Override
    public List<Ticket> findByAssigneeIdAndStateIn(User user, List<State> states) {
        return new JPAQuery<Ticket>(entityManager)
                .select(ticket)
                .from(ticket)
                .where(ticket.assigneeId.id.eq(user.getId())
                        .and(ticket.state.in(states)))
                .fetch();
    }

    private OrderSpecifier<Integer> orderByUrgency() {
        NumberExpression<Integer> cases = new CaseBuilder()
                .when(ticket.urgency.eq(Urgency.LOW))
                .then(4)
                .when(ticket.urgency.eq(Urgency.AVERAGE))
                .then(3)
                .when(ticket.urgency.eq(Urgency.HIGH))
                .then(2)
                .when(ticket.urgency.eq(Urgency.CRITICAL))
                .then(1)
                .otherwise(0);
        return new OrderSpecifier<>(Order.ASC, cases);
    }
}
