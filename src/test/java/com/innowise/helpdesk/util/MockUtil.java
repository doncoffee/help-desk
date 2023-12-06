package com.innowise.helpdesk.util;

import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockUtil {

    private MockUtil() {
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .firstname("name")
                .lastname("name")
                .email("test@yopmail.com")
                .role(Role.EMPLOYEE)
                .password("P@ssword1")
                .build();
    }

    public static User createUser() {
        return User.builder()
                .firstname("name")
                .lastname("name")
                .email("test@yopmail.com")
                .role(Role.EMPLOYEE)
                .password("P@ssword1")
                .build();
    }

    public static Ticket createTicketWithCriticalUrgency() {
        return Ticket.builder()
                .name("ticket_01")
                .description("for fun")
                .createdOn(LocalDateTime.now())
                .desiredResolutionDate(LocalDate.now())
                .assigneeId(null)
                .ownerId(null)
                .approverId(null)
                .state(State.IN_PROGRESS)
                .urgency(Urgency.CRITICAL)
                .build();
    }

    public static Ticket createTicketWithLowUrgency() {
        return Ticket.builder()
                .name("ticket_01")
                .description("for fun")
                .createdOn(LocalDateTime.now())
                .desiredResolutionDate(LocalDate.now())
                .assigneeId(null)
                .ownerId(null)
                .approverId(null)
                .state(State.IN_PROGRESS)
                .urgency(Urgency.LOW)
                .build();
    }

    public static TicketReadDto createTicketReadDto() {
        return TicketReadDto.builder()
                .name("test ticket")
                .desiredResolutionDate(LocalDate.now())
                .urgency(Urgency.CRITICAL)
                .state(State.NEW)
                .build();
    }
}
