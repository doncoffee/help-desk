package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User mapToEntity(UserDto object) {
        User user = User.builder()
                .id(object.getId())
                .firstname(object.getFirstname())
                .lastname(object.getLastname())
                .role(object.getRole())
                .email(object.getEmail())
                .build();

        Optional.ofNullable(object.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        return user;
    }

    @Override
    public UserDto mapToDto(User object) {
        return UserDto.builder()
                .id(object.getId())
                .firstname(object.getFirstname())
                .lastname(object.getLastname())
                .role(object.getRole())
                .email(object.getEmail())
                .build();
    }
}
