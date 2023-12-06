package com.innowise.helpdesk.service.tickets;

import com.innowise.helpdesk.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllCommentsByTicketId(Long ticketId);
    CommentDto createComment(Long ticketId, String text);
}
