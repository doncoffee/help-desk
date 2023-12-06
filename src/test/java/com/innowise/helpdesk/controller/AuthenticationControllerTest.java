package com.innowise.helpdesk.controller;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.jwt.JwtAuthenticationResponse;
import com.innowise.helpdesk.service.jwt.AuthenticationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.innowise.helpdesk.util.MockUtil.createUserDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void signUpReturnsStatusOk() throws Exception {
        UserDto userDto = createUserDto();

        // Mock the response from the AuthenticationService
        when(authenticationService.signUp(any(UserDto.class)))
                .thenReturn(new JwtAuthenticationResponse("test-token"));

        // Perform the POST request
        mockMvc.perform(post("/api/v1/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstname\": \"name\", \"lastname\": \"name\", " +
                                "\"email\": \"test@yopmail.com\", \"password\": \"P@ssword1\", \"role\": \"EMPLOYEE\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify the response
        verify(authenticationService, times(1)).signUp(userDto);
        verifyNoMoreInteractions(authenticationService);
    }

    @Test
    void signUpReturnsClientError() throws Exception {
        // Mock the response from the ResponseErrorValidationService to return a Bad Request response
        when(authenticationService.signUp(any(UserDto.class)))
                .thenReturn(new JwtAuthenticationResponse("test-token"));

        // Perform the POST request
        mockMvc.perform(post("/api/v1/auth/signup")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("{ \"firstname\": \"name\", \"lastname\": \"name\", " +
                                "\"email\": \"\", \"password\": \"P@ssword1\", \"role\": \"EMPLOYEE\" }"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void signInReturnsStatusOk() throws Exception {
        // Mock the response from the AuthenticationService
        when(authenticationService.signIn(any(UserDto.class)))
                .thenReturn(new JwtAuthenticationResponse("test-token"));

        // Perform the POST request
        mockMvc.perform(post("/api/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstname\": \"name\", \"lastname\": \"name\", " +
                                "\"email\": \"test@yopmail.com\", \"password\": \"P@ssword1\", \"role\": \"EMPLOYEE\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-token"));
    }


    @Test
    void signInReturnsUnauthorized() throws Exception {

        // Mock the exception from the AuthenticationService
        when(authenticationService.signIn(any(UserDto.class)))
                .thenThrow(new IllegalArgumentException("Invalid email or password."));

        // Perform the POST request
        mockMvc.perform(post("/api/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstname\": \"name\", \"lastname\": \"name\", " +
                                "\"email\": \"test@yopmail.com\", \"password\": \"wrong-password\", \"role\": \"EMPLOYEE\" }"))
                .andExpect(status().is4xxClientError())
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid email or password."));
    }
}