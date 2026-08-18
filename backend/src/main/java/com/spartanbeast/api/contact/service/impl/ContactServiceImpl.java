package com.spartanbeast.api.contact.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spartanbeast.api.common.exception.ContactMessageNotFoundException;
import com.spartanbeast.api.contact.dto.ContactRequest;
import com.spartanbeast.api.contact.dto.ContactResponse;
import com.spartanbeast.api.contact.entity.ContactMessage;
import com.spartanbeast.api.contact.entity.ContactStatus;
import com.spartanbeast.api.contact.mapper.ContactMapper;
import com.spartanbeast.api.contact.repository.ContactRepository;
import com.spartanbeast.api.contact.service.ContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;
	private final ContactMapper contactMapper;

	@Override
	public ContactResponse create(ContactRequest request) {

		ContactMessage contact = contactMapper.toEntity(request);

		ContactMessage savedContact = contactRepository.save(contact);

		return contactMapper.toResponse(savedContact);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContactResponse> findAll() {

		return contactRepository.findAllByOrderByCreatedAtDesc().stream().map(contactMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContactResponse> findByStatus(ContactStatus status) {

		return contactRepository.findAllByStatusOrderByCreatedAtDesc(status).stream().map(contactMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public ContactResponse findById(Long id) {

		return contactMapper.toResponse(findEntityById(id));
	}

	@Override
	public ContactResponse updateStatus(Long id, ContactStatus status) {

		ContactMessage contact = findEntityById(id);

		contact.setStatus(status);

		ContactMessage updatedContact = contactRepository.save(contact);

		return contactMapper.toResponse(updatedContact);
	}

	@Override
	public void delete(Long id) {

		ContactMessage contact = findEntityById(id);

		contactRepository.delete(contact);
	}

	private ContactMessage findEntityById(Long id) {

		return contactRepository.findById(id).orElseThrow(() -> new ContactMessageNotFoundException(id));
	}
}