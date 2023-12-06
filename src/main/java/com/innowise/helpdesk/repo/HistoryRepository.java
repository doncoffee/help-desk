package com.innowise.helpdesk.repo;

import com.innowise.helpdesk.entity.History;
import com.innowise.helpdesk.repo.custom.CustomHistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>, CustomHistoryRepository {
}
