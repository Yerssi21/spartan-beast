package com.spartanbeast.api.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spartanbeast.api.contact.entity.ContactMessage;
import com.spartanbeast.api.contact.entity.ContactStatus;

public interface ContactRepository extends JpaRepository<ContactMessage, Long> {

	List<ContactMessage> findAllByOrderByCreatedAtDesc();

	List<ContactMessage> findAllByStatusOrderByCreatedAtDesc(ContactStatus status);
}