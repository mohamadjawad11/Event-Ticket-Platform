package com.mhmd_dev.Event_Ticket_Platform.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhmd_dev.Event_Ticket_Platform.domain.CreateEventRequest;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.Event;
import com.mhmd_dev.Event_Ticket_Platform.domain.dtos.CreateEventRequestDto;
import com.mhmd_dev.Event_Ticket_Platform.domain.dtos.CreateEventResponseDto;
import com.mhmd_dev.Event_Ticket_Platform.mappers.EventMapper;
import com.mhmd_dev.Event_Ticket_Platform.services.Impl.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor

public class EventController {
  
  private final EventMapper eventMapper;
  private final EventService eventService;

  @PostMapping
  public ResponseEntity<CreateEventResponseDto> createEvent(
    @AuthenticationPrincipal Jwt jwt,
    @Valid @RequestBody CreateEventRequestDto createEventRequestDto
  ){
    //convers createEventRequestDto to CreateEventRequest
    CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
    //extract id from the jwt token
    UUID organizerId = UUID.fromString(jwt.getSubject());
    //call the service to create the event
    Event createdEvent = eventService.createEvent(organizerId, createEventRequest);
    //convert the created event to CreateEventResponseDto
    //this is the response that will be sent back to the client
    CreateEventResponseDto responseDto = eventMapper.toDto(createdEvent);
    //return the response
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
}
