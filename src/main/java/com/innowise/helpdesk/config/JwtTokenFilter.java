package com.innowise.helpdesk.config;

import com.innowise.helpdesk.service.jwt.TokenBlackList;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final TokenBlackList tokenBlacklist;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = tokenBlacklist.extractTokenFromRequest(request);

        if (token != null && !tokenBlacklist.isBlacklisted(token)) {
            filterChain.doFilter(request, response);
        } else {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
        }
    }
}
