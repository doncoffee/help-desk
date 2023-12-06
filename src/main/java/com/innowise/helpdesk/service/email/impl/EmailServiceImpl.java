package com.innowise.helpdesk.service.email.impl;

import com.innowise.helpdesk.entity.State;
import com.innowise.helpdesk.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.innowise.helpdesk.util.Constants.*;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(String to, Long ticketId, State state) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);

        switch (state) {
            case NEW -> {
                message.setSubject(NEW_STATE_SUBJECT);
                message.setText(String.format(NEW_STATE_TEXT, ticketId));
            }
            case APPROVED -> {
                message.setSubject(APPROVED_STATE_SUBJECT);
                message.setText(String.format(APPROVED_STATE_TEXT, ticketId));
            }
            case DECLINED -> {
                message.setSubject(DECLINED_STATE_SUBJECT);
                message.setText(String.format(DECLINED_STATE_TEXT, ticketId));
            }
            case CANCELLED -> {
                message.setSubject(CANCELLED_STATE_SUBJECT);
                message.setText(String.format(CANCELLED_STATE_TEXT, ticketId));
            }
            case DONE -> {
                message.setSubject(DONE_STATE_SUBJECT);
                message.setText(String.format(DONE_STATE_TEXT, ticketId));
            }
        }
        emailSender.send(message);
    }

    @Override
    public void sendFeedbackEmail(String to, Long ticketId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(FEEDBACK_SUBJECT);
        String text = String.format(FEEDBACK_TEXT, ticketId);
        message.setText(text);
        emailSender.send(message);
    }
}
