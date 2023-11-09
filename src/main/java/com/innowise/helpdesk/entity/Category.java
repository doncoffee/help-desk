package com.innowise.helpdesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.innowise.helpdesk.util.Constants.CATEGORY;
import static com.innowise.helpdesk.util.Constants.CATEGORY_TABLE_NAME;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = CATEGORY_TABLE_NAME)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = CATEGORY, cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
