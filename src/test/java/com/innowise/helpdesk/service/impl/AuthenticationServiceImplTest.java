package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.jwt.JwtAuthenticationResponse;
import com.innowise.helpdesk.repo.UserRepository;
import com.innowise.helpdesk.service.AuthenticationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static com.innowise.helpdesk.util.MockUtil.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSignIn() {
        UserDto userDto = createUserDto();
        // Saving user to the database
        authenticationService.signUp(userDto);

        // Perform the authentication
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(),
                userDto.getPassword()));

        // Calling the signIn method
        JwtAuthenticationResponse response = authenticationService.signIn(userDto);

        // Verify that the response contains a valid token
        assertNotNull(response.getToken());
    }


    @Test
    public void testSignUp() {
        UserDto userDto = createUserDto();

        // Calling the signUp method
        JwtAuthenticationResponse response = authenticationService.signUp(userDto);

        assertNotNull(response.getToken());
        assertTrue(userRepository.findUserByEmail(userDto.getEmail()).isPresent());
    }
}