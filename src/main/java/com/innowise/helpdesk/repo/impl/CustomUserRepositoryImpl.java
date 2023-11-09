package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.entity.QUser;
import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.repo.custom.CustomUserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;


public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findUserByEmail(String email) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QUser qUser = QUser.user;

        return Optional.ofNullable(queryFactory
                .selectFrom(qUser)
                .where(qUser.email.eq(email))
                .fetchFirst());
    }
}
