package com.innowise.helpdesk.repo.custom;

import com.innowise.helpdesk.entity.History;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomHistoryRepository {

    List<History> findEntireHistoryByTicketId(Long ticketId);
}
