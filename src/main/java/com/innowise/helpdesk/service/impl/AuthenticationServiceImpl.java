package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.jwt.JwtAuthenticationResponse;
import com.innowise.helpdesk.mapper.impl.UserMapper;
import com.innowise.helpdesk.repo.UserRepository;
import com.innowise.helpdesk.service.AuthenticationService;
import com.innowise.helpdesk.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.innowise.helpdesk.util.Constants.INVALID_EMAIL_OR_PASSWORD;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(UserDto userDto) {
        var user = Optional.of(userDto)
                .map(userMapper::mapToEntity)
                .map(userRepository::save)
                .map(userMapper::mapToDto)
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        var user = userRepository.findUserByEmail(userDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(INVALID_EMAIL_OR_PASSWORD));
        String jwt = jwtService.generateToken(userMapper.mapToDto(user));
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
