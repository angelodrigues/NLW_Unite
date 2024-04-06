package com.angelo.passin.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angelo.passin.domain.attendee.Attendee;
import com.angelo.passin.domain.checkin.Checkin;
import com.angelo.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import com.angelo.passin.repositories.CheckinRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckinRepository checkInRepository;

    public void registerCheckIn(Attendee attendee){
        this.verifyCheckInExists(attendee.getId());

        Checkin newCheckin = new Checkin();
        newCheckin.setAttendee(attendee);
        newCheckin.setCreatedAt(LocalDateTime.now());
        this.checkInRepository.save(newCheckin);
    }

    public Optional<Checkin> getCheckIn(String attendeeId){
        return this.checkInRepository.findByAttendeeId(attendeeId);
    }

    private void verifyCheckInExists(String attendeeId){
        Optional<Checkin> isCheckedIn = this.getCheckIn(attendeeId);
        if(isCheckedIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee already checked in.");
    }
}