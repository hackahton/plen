package com.devs.hackaton.repository;
import com.devs.hackaton.entity.HoursRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoursRepository extends JpaRepository<HoursRepository, UUID> {
}
