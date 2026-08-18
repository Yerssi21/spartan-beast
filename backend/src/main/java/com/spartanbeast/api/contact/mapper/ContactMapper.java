package com.spartanbeast.api.contact.mapper;

import org.springframework.stereotype.Component;

import com.spartanbeast.api.contact.dto.ContactRequest;
import com.spartanbeast.api.contact.dto.ContactResponse;
import com.spartanbeast.api.contact.entity.ContactMessage;
import com.spartanbeast.api.contact.entity.ContactStatus;

@Component
public class ContactMapper {

	public ContactMessage toEntity(ContactRequest request) {

		return ContactMessage.builder().name(request.name()).email(request.email()).phone(request.phone())
				.interest(request.interest()).message(request.message()).status(ContactStatus.NEW).build();
	}

	public ContactResponse toResponse(ContactMessage contact) {

		return new ContactResponse(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhone(),
				contact.getInterest(), contact.getMessage(), contact.getStatus(), contact.getCreatedAt(),
				contact.getUpdatedAt());
	}
}