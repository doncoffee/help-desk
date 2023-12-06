package com.innowise.helpdesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.innowise.helpdesk.util.Constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TICKET_TABLE_NAME)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = CREATED_ON)
    private LocalDateTime createdOn;

    @Column(name = DESIRED_RESOLUTION_DATE)
    private LocalDate desiredResolutionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ASSIGNEE_ID)
    private User assigneeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = OWNER_ID)
    private User ownerId;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CATEGORY_ID)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = APPROVER_ID)
    private User approverId;

    @Builder.Default
    @OneToMany(mappedBy = TICKET, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = TICKET, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = TICKET, cascade = CascadeType.ALL)
    private List<History> histories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = TICKET, cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<>();
}
