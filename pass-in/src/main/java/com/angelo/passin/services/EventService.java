package com.angelo.passin.services;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;

import com.angelo.passin.domain.attendee.Attendee;
import com.angelo.passin.domain.event.Event;
import com.angelo.passin.domain.event.exceptions.EventNotFoundException;
import com.angelo.passin.dto.event.EventIdDTO;
import com.angelo.passin.dto.event.EventRequestDTO;
import com.angelo.passin.dto.event.EventResponseDTO;
import com.angelo.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId)
                                            .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size()); 
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                         .replaceAll("[^\\w\\s]", "")
                         .replaceAll("\\s+", "-")
                         .toLowerCase();
    }
}