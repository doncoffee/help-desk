package com.innowise.helpdesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.innowise.helpdesk.util.Constants.ATTACHMENT_TABLE_NAME;
import static com.innowise.helpdesk.util.Constants.TICKET_ID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ATTACHMENT_TABLE_NAME)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] blob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TICKET_ID)
    private Ticket ticket;

    private String name;
}
