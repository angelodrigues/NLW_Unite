package com.angelo.passin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angelo.passin.domain.attendee.Attendee;
import com.angelo.passin.domain.checkin.Checkin;
import com.angelo.passin.dto.attendee.AttendeeDetails;
import com.angelo.passin.dto.attendee.AttendeesListResponseDTO;
import com.angelo.passin.repositories.AttendeeRepository;
import com.angelo.passin.repositories.CheckinRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkin = this.checkInRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkin.isPresent() ? checkin.get().getCreatedAt() : null;

            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }
}