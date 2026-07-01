package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Integer> {
}