package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.repo.UserRepository;
import com.innowise.helpdesk.service.UserService;
import com.innowise.helpdesk.util.MockUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void getUserDetailsService() {
        User testUser = MockUtil.createUser();

        userRepository.save(testUser);

        // Calling the getUserDetailsService method with the test user's email
        UserDetailsService userDetailsService = userService.getUserDetailsService();
        UserDetails userDetails = userDetailsService.loadUserByUsername(testUser.getEmail());

        // Assert that the returned UserDetails is not null and contains the expected user information
        assertNotNull(userDetails);
        assertEquals(testUser.getEmail(), userDetails.getUsername());
        assertEquals(testUser.getPassword(), userDetails.getPassword());
    }
}