package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.repo.UserRepository;
import com.innowise.helpdesk.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.innowise.helpdesk.util.Constants.FAILED_TO_RETRIEVE_USER;

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
}
