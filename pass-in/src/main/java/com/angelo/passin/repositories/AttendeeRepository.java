package com.angelo.passin.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelo.passin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String>{   
    List<Attendee> findByEventId(String eventId);

    Optional<Attendee> findByEventIdAndEmail(String eventId, String email);
}