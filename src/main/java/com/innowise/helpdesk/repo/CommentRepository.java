package com.innowise.helpdesk.repo;

import com.innowise.helpdesk.entity.Comment;
import com.innowise.helpdesk.repo.custom.CustomCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
}
