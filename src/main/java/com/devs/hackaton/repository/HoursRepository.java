package com.devs.hackaton.repository;
import com.devs.hackaton.entity.HourRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HoursRepository extends JpaRepository<HourRegistry, UUID> {
}
