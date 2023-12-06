package com.innowise.helpdesk.service.tickets;

import com.innowise.helpdesk.dto.FeedbackDto;

public interface FeedbackService {

    FeedbackDto createFeedback(Long ticketId, FeedbackDto feedbackDto);
}
