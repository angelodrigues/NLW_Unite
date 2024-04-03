package com.angelo.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelo.passin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String>{   
}