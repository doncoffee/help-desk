package com.innowise.helpdesk.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE, MANAGER, ENGINEER;

    @Override
    public String getAuthority() {
        return name();
    }
}
