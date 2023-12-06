package com.innowise.helpdesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.innowise.helpdesk.util.Constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = FEEDBACK_TABLE_NAME)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID)
    private User user;

    private Integer rate;

    @Column(name = DATE_TIME_COLUMN_NAME)
    private LocalDateTime dateTime;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TICKET_ID)
    private Ticket ticket;
}
