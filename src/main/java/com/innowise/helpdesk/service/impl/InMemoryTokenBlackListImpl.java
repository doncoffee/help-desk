package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.service.TokenBlackList;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

import static com.innowise.helpdesk.util.Constants.AUTHORIZATION_HEADER;
import static com.innowise.helpdesk.util.Constants.BEARER_HEADER;

@Service
public class InMemoryTokenBlackListImpl implements TokenBlackList {
    private Set<String> blacklist = new HashSet<>();

    @Override
    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }

    @Override
    public String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_HEADER)) {
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
