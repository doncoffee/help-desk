package com.innowise.helpdesk.repo;

import com.innowise.helpdesk.entity.Attachment;
import com.innowise.helpdesk.repo.custom.CustomAttachmentRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long>, CustomAttachmentRepo {
}
