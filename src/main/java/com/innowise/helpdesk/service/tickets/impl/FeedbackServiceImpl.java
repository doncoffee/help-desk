package com.innowise.helpdesk.service.tickets.impl;

import com.innowise.helpdesk.dto.FeedbackDto;
import com.innowise.helpdesk.entity.Feedback;
import com.innowise.helpdesk.entity.Ticket;
import com.innowise.helpdesk.mapper.impl.FeedbackMapper;
import com.innowise.helpdesk.repo.FeedbackRepo;
import com.innowise.helpdesk.repo.TicketRepository;
import com.innowise.helpdesk.service.email.EmailService;
import com.innowise.helpdesk.service.tickets.FeedbackService;
import com.innowise.helpdesk.service.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final FeedbackMapper feedbackMapper;
    private final EmailService emailService;

    @Override
    public FeedbackDto createFeedback(Long ticketId, FeedbackDto feedbackDto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow();

        Feedback feedback = Optional.of(feedbackDto)
                .map(fd -> Feedback.builder()
                        .user(userService.getUserFromContextHolder())
                        .ticket(ticket)
                        .dateTime(LocalDateTime.now())
                        .text(fd.getText())
                        .rate(fd.getRate())
                        .build())
                .map(feedbackRepo::save)
                .orElseThrow();

//        emailService.sendFeedbackEmail(ticket.getAssigneeId().getEmail(), ticketId);

        return feedbackMapper.mapToDto(feedback);
    }
}
