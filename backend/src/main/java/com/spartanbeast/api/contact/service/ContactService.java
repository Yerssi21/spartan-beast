package com.spartanbeast.api.contact.service;

import java.util.List;

import com.spartanbeast.api.contact.dto.ContactRequest;
import com.spartanbeast.api.contact.dto.ContactResponse;
import com.spartanbeast.api.contact.entity.ContactStatus;

public interface ContactService {

	ContactResponse create(ContactRequest request);

	List<ContactResponse> findAll();

	List<ContactResponse> findByStatus(ContactStatus status);

	ContactResponse findById(Long id);

	ContactResponse updateStatus(Long id, ContactStatus status);

	void delete(Long id);
}