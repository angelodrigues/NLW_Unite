package com.angelo.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelo.passin.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String>{   
}