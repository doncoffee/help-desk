package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.entity.Urgency;
import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.repo.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.innowise.helpdesk.util.MockUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class CustomTicketRepositoryImplTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void findByOwnerId() {
        // setting up
        User user = createUser();
        user.setId(1L);

        Ticket ticketWithLowUrgency = createTicketWithLowUrgency();
        ticketWithLowUrgency.setOwnerId(user);
        ticketRepository.save(ticketWithLowUrgency);

        Ticket ticketWithCriticalUrgency = createTicketWithCriticalUrgency();
        ticketWithCriticalUrgency.setOwnerId(user);
        ticketRepository.save(ticketWithCriticalUrgency);

        // invoking test method
        List<Ticket> resultList = ticketRepository.findByOwnerId(user);

        // assertions
        assertFalse(resultList.isEmpty());
        assertEquals(1L, resultList.stream().findAny().get().getOwnerId().getId());
        // assuming I have another tickets from existing db and result is sorted
        assertEquals(Urgency.CRITICAL, resultList.get(0).getUrgency());
        assertEquals(Urgency.LOW, resultList.get(resultList.size() - 1).getUrgency());

    }

    @Test
    void findByOwnerIdAndState() {
        // setting up
        User user = createUser();
        user.setId(1L);

        Ticket ticketWithLowUrgency = createTicketWithLowUrgency();
        ticketWithLowUrgency.setOwnerId(user);
        ticketWithLowUrgency.setState(State.NEW);
        ticketRepository.save(ticketWithLowUrgency);

        Ticket ticketWithCriticalUrgency = createTicketWithCriticalUrgency();
        ticketWithCriticalUrgency.setOwnerId(user);
        ticketWithCriticalUrgency.setState(State.APPROVED);
        ticketRepository.save(ticketWithCriticalUrgency);

        // invoking test method
        List<Ticket> stateNewList = ticketRepository.findByOwnerIdAndState(user, State.NEW);
        List<Ticket> stateApprovedList = ticketRepository.findByOwnerIdAndState(user, State.APPROVED);

        // assertions
        assertFalse(stateNewList.isEmpty());
        assertFalse(stateApprovedList.isEmpty());
        assertEquals(1L, stateNewList.stream().findAny().get().getOwnerId().getId());
        assertEquals(1L, stateApprovedList.stream().findAny().get().getOwnerId().getId());
        assertEquals(State.NEW, stateNewList.stream().findAny().get().getState());
        assertEquals(State.APPROVED, stateApprovedList.stream().findAny().get().getState());
    }

    @Test
    void findByApproverIdAndStateIn() {
        // setting up
        User user = createUser();
        user.setId(1L);

        Ticket ticketWithLowUrgency = createTicketWithLowUrgency();
        ticketWithLowUrgency.setApproverId(user);
        ticketWithLowUrgency.setState(State.APPROVED);
        ticketRepository.save(ticketWithLowUrgency);

        Ticket ticketWithCriticalUrgency = createTicketWithCriticalUrgency();
        ticketWithCriticalUrgency.setApproverId(user);
        ticketWithCriticalUrgency.setState(State.DECLINED);
        ticketRepository.save(ticketWithCriticalUrgency);

        // invoking test method
        List<Ticket> approvedTicketList = ticketRepository
                .findByApproverIdAndStateIn(user, Arrays.asList(State.APPROVED,
                        State.DECLINED));

        // assertions
        assertFalse(approvedTicketList.isEmpty());
        assertEquals(1L, approvedTicketList.stream().findAny().get().getApproverId().getId());
        assertEquals(State.APPROVED, approvedTicketList.stream()
                .filter(ticket -> State.APPROVED.equals(ticket.getState()))
                .findFirst().get().getState());
        assertEquals(State.DECLINED, approvedTicketList.stream()
                .filter(ticket -> State.DECLINED.equals(ticket.getState()))
                .findFirst().get().getState());
    }

    @Test
    void findByState() {
        // setting up
        Ticket ticketWithLowUrgency = createTicketWithLowUrgency();
        ticketWithLowUrgency.setState(State.NEW);
        ticketRepository.save(ticketWithLowUrgency);

        Ticket ticketWithCriticalUrgency = createTicketWithCriticalUrgency();
        ticketWithCriticalUrgency.setState(State.NEW);
        ticketRepository.save(ticketWithCriticalUrgency);

        // invoking
        List<Ticket> resultList = ticketRepository.findByState(State.NEW);

        // assertions
        assertFalse(resultList.isEmpty());
        assertEquals(State.NEW, resultList.stream().findAny().get().getState());
    }

    @Test
    void findByAssigneeIdAndStateIn() {
        // setting up
        User user = createUser();
        user.setId(1L);

        Ticket ticketWithLowUrgency = createTicketWithLowUrgency();
        ticketWithLowUrgency.setAssigneeId(user);
        ticketWithLowUrgency.setState(State.APPROVED);
        ticketRepository.save(ticketWithLowUrgency);

        Ticket ticketWithCriticalUrgency = createTicketWithCriticalUrgency();
        ticketWithCriticalUrgency.setAssigneeId(user);
        ticketWithCriticalUrgency.setState(State.DECLINED);
        ticketRepository.save(ticketWithCriticalUrgency);

        // invoking test method
        List<Ticket> assigneeTicketList = ticketRepository
                .findByAssigneeIdAndStateIn(user, Arrays.asList(State.APPROVED,
                        State.DECLINED));

        // assertions
        assertFalse(assigneeTicketList.isEmpty());
        assertEquals(1L, assigneeTicketList.stream().findAny().get().getAssigneeId().getId());
        assertEquals(State.APPROVED, assigneeTicketList.stream()
                .filter(ticket -> State.APPROVED.equals(ticket.getState()))
                .findFirst().get().getState());
        assertEquals(State.DECLINED, assigneeTicketList.stream()
                .filter(ticket -> State.DECLINED.equals(ticket.getState()))
                .findFirst().get().getState());
    }
}