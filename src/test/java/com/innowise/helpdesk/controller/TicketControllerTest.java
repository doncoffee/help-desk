package com.innowise.helpdesk.controller;

import com.innowise.helpdesk.dto.TicketReadDto;
import com.innowise.helpdesk.entity.Role;
import com.innowise.helpdesk.service.TicketService;
import com.innowise.helpdesk.util.MockUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@WithMockUser(username = "user", password = "pass", authorities = {"EMPLOYEE", "MANAGER", "ENGINEER"})
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    void findAllTickets() throws Exception {
        TicketReadDto ticketReadDto = MockUtil.createTicketReadDto();
        List<TicketReadDto> mockTicketList = Collections.singletonList(ticketReadDto);
        when(ticketService.getTicketsByUser()).thenReturn(mockTicketList);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets")
                        .with(user("user").authorities(Role.MANAGER)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));

        // Verify that the service method was called
        verify(ticketService, times(1)).getTicketsByUser();
        verifyNoMoreInteractions(ticketService);
    }

    @Test
    void findMyTickets() throws Exception {
        TicketReadDto ticketReadDto = MockUtil.createTicketReadDto();
        List<TicketReadDto> mockTicketList = Collections.singletonList(ticketReadDto);
        when(ticketService.getMyTickets()).thenReturn(mockTicketList);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets/personal")
                        .with(user("user").authorities(Role.ENGINEER)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));

        // Verify that the service method was called
        verify(ticketService, times(1)).getMyTickets();
        verifyNoMoreInteractions(ticketService);
    }
}