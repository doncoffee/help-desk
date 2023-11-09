package com.innowise.helpdesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.innowise.helpdesk.util.Constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = USER_TABLE_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String password;

    @Builder.Default
    @OneToMany(mappedBy = USER, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = USER, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = USER, cascade = CascadeType.ALL)
    private List<History> histories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = MAPPED_BY_ASSIGNEE_ID, cascade = CascadeType.ALL)
    private List<Ticket> assigneeTickets = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = MAPPED_BY_OWNER_ID, cascade = CascadeType.ALL)
    private List<Ticket> ownerTickets = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = MAPPED_BY_APPROVER_ID, cascade = CascadeType.ALL)
    private List<Ticket> approverTickets = new ArrayList<>();
}
