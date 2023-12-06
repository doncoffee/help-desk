package com.innowise.helpdesk.service.jwt.impl;

import com.innowise.helpdesk.repo.UserRepository;
import com.innowise.helpdesk.service.jwt.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.innowise.helpdesk.util.Constants.FAILED_TO_RETRIEVE_USER;
import static com.innowise.helpdesk.util.Constants.INVALID_EMAIL_OR_PASSWORD;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService getUserDetailsService() {
        return email -> userRepository.findUserByEmail(email)
                .map(user -> new User(user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException(FAILED_TO_RETRIEVE_USER + email));
    }

    @Override
    public com.innowise.helpdesk.entity.User getUserFromContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();

        return userRepository.findUserByEmail(loggedInUserEmail)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_EMAIL_OR_PASSWORD));
    }
}
