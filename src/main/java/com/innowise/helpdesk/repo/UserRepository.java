package com.innowise.helpdesk.repo;

import com.innowise.helpdesk.entity.User;
import com.innowise.helpdesk.repo.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

}
