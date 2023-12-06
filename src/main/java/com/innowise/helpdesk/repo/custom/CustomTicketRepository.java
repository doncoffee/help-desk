package com.innowise.helpdesk.repo.custom;

import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomTicketRepository {

    List<Ticket> findByOwnerId(User user);
    List<Ticket> findByOwnerIdAndState(User user, State state);
    List<Ticket> findByApproverIdAndStateIn(User user, List<State> states);
    List<Ticket> findByState(State state);
    List<Ticket> findByAssigneeIdAndStateIn(User user, List<State> states);
}
