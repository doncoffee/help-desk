package com.innowise.helpdesk.repo.custom;

import com.innowise.helpdesk.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomCommentRepository {

    List<Comment> findAllCommentsByTicketId(Long ticketId);
}
