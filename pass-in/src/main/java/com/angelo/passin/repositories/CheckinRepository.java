package com.angelo.passin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelo.passin.domain.checkin.Checkin;

public interface CheckinRepository extends JpaRepository<Checkin, Integer>{   

    Optional<Checkin> findByAttendeeId(String attendeeId);
}