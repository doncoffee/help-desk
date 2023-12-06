package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.dto.UserDto;
import com.innowise.helpdesk.service.jwt.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.innowise.helpdesk.util.MockUtil.createUserDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class JwtServiceImplTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void testGenerateAndValidateToken() {
        // Creating a UserDto
        UserDto userDto = createUserDto();

        // Generating a token
        String token = jwtService.generateToken(userDto);

        String username = jwtService.extractUserName(token);

        assertEquals(userDto.getEmail(), username);
    }

    @Test
    void testExtractUserName() {
        String token = generateTestToken("user123");

        String username = jwtService.extractUserName(token);

        assertEquals("user123", username);
    }

    private String generateTestToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        String jwtSigningKey = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}