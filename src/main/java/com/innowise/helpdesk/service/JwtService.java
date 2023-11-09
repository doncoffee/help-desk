package com.innowise.helpdesk.service;

import com.innowise.helpdesk.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(UserDto userDto);

    boolean isTokenValid(String token, UserDetails userDetails);
}
