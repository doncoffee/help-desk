package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.Role;
import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.repo.UserRepository;
import com.innowise.helpdesk.service.AuthenticationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.innowise.helpdesk.util.MockUtil.createUserDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class CustomUserRepositoryImplTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByEmail() {
        // Creating a user and save it to the database
        UserDto user = createUserDto();
        // Setting other user properties as needed
        authenticationService.signUp(user);

        // Calling the findUserByEmail method
        Optional<User> foundUser = userRepository.findUserByEmail(user.getEmail());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getFirstname(), foundUser.get().getFirstname());
        assertEquals(user.getLastname(), foundUser.get().getLastname());
        assertEquals(Role.EMPLOYEE, foundUser.get().getRole());
    }
}