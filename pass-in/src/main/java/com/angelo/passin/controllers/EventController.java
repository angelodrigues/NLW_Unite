package com.angelo.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.angelo.passin.dto.attendee.AttendeesListResponseDTO;
import com.angelo.passin.dto.event.EventIdDTO;
import com.angelo.passin.dto.event.EventRequestDTO;
import com.angelo.passin.dto.event.EventResponseDTO;
import com.angelo.passin.services.AttendeeService;
import com.angelo.passin.services.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {   

    private final EventService eventService;

    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventid()).toUri();
        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
        AttendeesListResponseDTO attendeesListResponseDTO = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListResponseDTO);
    }

}