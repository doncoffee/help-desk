package com.innowise.helpdesk.util;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.Role;
import com.innowise.helpdesk.entity.User;

public class MockUtil {

    private MockUtil() {
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .firstname("name")
                .lastname("name")
                .email("test@yopmail.com")
                .role(Role.EMPLOYEE)
                .password("P@ssword1")
                .build();
    }

    public static UserDto createUserDtoWithInvalidPassword() {
        return UserDto.builder()
                .firstname("name")
                .lastname("name")
                .email("test@yopmail.com")
                .role(Role.EMPLOYEE)
                .password("wrong-password")
                .build();
    }

    public static User createUser() {
        return User.builder()
                .firstname("name")
                .lastname("name")
                .email("test@yopmail.com")
                .role(Role.EMPLOYEE)
                .password("P@ssword1")
                .build();
    }
}
