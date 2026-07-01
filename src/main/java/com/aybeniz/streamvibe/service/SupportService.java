package com.aybeniz.streamvibe.service;

import com.aybeniz.streamvibe.dto.request.ContactRequest;
import com.aybeniz.streamvibe.dto.response.ContactResponse;
import com.aybeniz.streamvibe.dto.response.FaqResponse;
import com.aybeniz.streamvibe.entity.ContactMessage;
import com.aybeniz.streamvibe.repository.ContactMessageRepository;
import com.aybeniz.streamvibe.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SupportService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern DIGITS_PATTERN =
            Pattern.compile("^\\d+$");

    private final ContactMessageRepository contactMessageRepository;
    private final FaqRepository faqRepository;

    public ContactResponse createContactMessage(ContactRequest request) {
        validateContactRequest(request);

        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setFirstName(request.getFirstName().trim());
        contactMessage.setLastName(request.getLastName().trim());
        contactMessage.setEmail(request.getEmail().trim().toLowerCase());
        contactMessage.setPhoneCountryCode(request.getPhoneCountryCode().trim());
        contactMessage.setPhoneNumber(request.getPhoneNumber().trim());
        contactMessage.setMessage(request.getMessage().trim());
        contactMessage.setRead(false);

        ContactMessage savedMessage = contactMessageRepository.save(contactMessage);

        return new ContactResponse(
                savedMessage.getId(),
                savedMessage.getCreatedAt()
        );
    }

    private void validateContactRequest(ContactRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is required");
        }

        String firstName = safe(request.getFirstName()).trim();
        String lastName = safe(request.getLastName()).trim();
        String email = safe(request.getEmail()).trim();
        String phoneCountryCode = safe(request.getPhoneCountryCode()).trim();
        String phoneNumber = safe(request.getPhoneNumber()).trim();
        String message = safe(request.getMessage()).trim();

        if (firstName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }

        if (lastName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required");
        }

        if (email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format");
        }

        if (phoneCountryCode.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone country code is required");
        }

        if (phoneNumber.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number is required");
        }

        if (!DIGITS_PATTERN.matcher(phoneNumber).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number must contain only digits");
        }

        if (message.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message is required");
        }

        if (message.length() < 20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message must be at least 20 characters");
        }
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    public List<FaqResponse> getFaqs() {
        return faqRepository.findAllByOrderByOrderNumberAsc()
                .stream()
                .map(faq -> new FaqResponse(
                        faq.getId(),
                        faq.getQuestion(),
                        faq.getAnswer(),
                        faq.getOrderNumber()
                ))
                .toList();
    }}