package com.innowise.helpdesk.service.email;

import com.innowise.helpdesk.entity.State;

public interface EmailService {

    void sendEmail(String to, Long ticketId, State state);
    void sendFeedbackEmail(String to, Long ticketId);
}
