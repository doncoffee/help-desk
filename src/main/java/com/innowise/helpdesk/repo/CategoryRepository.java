package com.innowise.helpdesk.repo;

import com.innowise.helpdesk.entity.Category;
import com.innowise.helpdesk.repo.custom.CustomCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CustomCategoryRepository {
}
