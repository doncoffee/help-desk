package com.innowise.helpdesk.repo.custom;

import com.innowise.helpdesk.entity.User;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findUserByEmail(String email);
}
