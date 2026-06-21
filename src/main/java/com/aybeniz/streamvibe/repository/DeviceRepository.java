package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}