package com.angelo.passin.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeDetails(String id, 
                                String name, 
                                String mail, 
                                LocalDateTime createdAt, 
                                LocalDateTime checkedInAt) {
}