package com.innowise.helpdesk.service.tickets.impl;

import com.innowise.helpdesk.dto.CommentDto;
import com.innowise.helpdesk.entity.Comment;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.mapper.impl.CommentMapper;
import com.innowise.helpdesk.repo.CommentRepository;
import com.innowise.helpdesk.repo.TicketRepository;
import com.innowise.helpdesk.service.tickets.CommentService;
import com.innowise.helpdesk.service.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;

    @Override
    public List<CommentDto> getAllCommentsByTicketId(Long ticketId) {
        return commentRepository.findAllCommentsByTicketId(ticketId)
                .stream().map(commentMapper::mapToDto)
                .toList();
    }

    @Override
    public CommentDto createComment(Long ticketId, String text) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow();
        return Optional.of(text)
                .map(commentText -> Comment.builder()
                        .user(userService.getUserFromContextHolder())
                        .ticket(ticket)
                        .dateTime(LocalDateTime.now())
                        .text(commentText)
                        .build())
                .map(commentRepository::save)
                .map(commentMapper::mapToDto)
                .orElseThrow();
    }
}
