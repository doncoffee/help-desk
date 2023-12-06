package com.innowise.helpdesk.service.jwt;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.jwt.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(UserDto userDto);
    JwtAuthenticationResponse signIn(UserDto userDto);}
