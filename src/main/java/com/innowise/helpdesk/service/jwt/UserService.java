package com.innowise.helpdesk.service.jwt;

import com.innowise.helpdesk.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService getUserDetailsService();
    User getUserFromContextHolder();
}
