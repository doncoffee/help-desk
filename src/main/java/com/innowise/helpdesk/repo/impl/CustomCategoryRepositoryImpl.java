package com.innowise.helpdesk.repo.impl;

import com.innowise.helpdesk.entity.Category;
import com.innowise.helpdesk.repo.custom.CustomCategoryRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

import static com.innowise.helpdesk.entity.QCategory.category;

public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return Optional.ofNullable(new JPAQuery<Category>(entityManager)
                .select(category)
                .from(category)
                .where(category.name.eq(name))
                .fetchOne());
    }
}
