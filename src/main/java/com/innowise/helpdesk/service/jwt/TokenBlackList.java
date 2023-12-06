package com.innowise.helpdesk.service.jwt;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenBlackList {

    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
    String extractTokenFromRequest(HttpServletRequest request);
}
