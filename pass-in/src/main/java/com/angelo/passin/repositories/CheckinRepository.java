package com.angelo.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angelo.passin.domain.checkin.Checkin;

public interface CheckinRepository extends JpaRepository<Checkin, Integer>{   
}