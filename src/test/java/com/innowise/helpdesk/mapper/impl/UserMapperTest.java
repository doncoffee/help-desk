package com.innowise.helpdesk.mapper.impl;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testMapToEntity() {
        // Creating a UserDto with sample data
        UserDto userDto = MockUtil.createUserDto();

        // Calling the mapToEntity method
        User user = userMapper.mapToEntity(userDto);

        // Verify that the mapping is correct
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
        assertEquals(userDto.getRole(), user.getRole());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

    @Test
    void testMapToDto() {
        // Creating a User entity with sample data
        User user = MockUtil.createUser();

        // Calling the mapToDto method
        UserDto userDto = userMapper.mapToDto(user);

        // Verify that the mapping is correct
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstname(), userDto.getFirstname());
        assertEquals(user.getLastname(), userDto.getLastname());
        assertEquals(user.getRole(), userDto.getRole());
        assertEquals(user.getEmail(), userDto.getEmail());
    }
}