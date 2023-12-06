package com.innowise.helpdesk.repo.custom;

import com.innowise.helpdesk.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomCategoryRepository {

    Optional<Category> findCategoryByName(String name);
}
