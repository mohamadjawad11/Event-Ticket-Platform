package com.mhmd_dev.Event_Ticket_Platform.services.Impl;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.mhmd_dev.Event_Ticket_Platform.domain.CreateEventRequest;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.Event;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.TicketType;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.User;
import com.mhmd_dev.Event_Ticket_Platform.exceptions.UserNotFoundException;
import com.mhmd_dev.Event_Ticket_Platform.repository.EventRepository;
import com.mhmd_dev.Event_Ticket_Platform.repository.UserRepository;
import com.mhmd_dev.Event_Ticket_Platform.services.IService.IEventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

  private final UserRepository userRepository;
  private final EventRepository eventRepository;

  public Event createEvent(UUID organizerId, CreateEventRequest event) {
    // check if the organizer exists
    User organizer = userRepository.findById(organizerId).orElseThrow(
        () -> new UserNotFoundException(
            String.format("User with id %s not found", organizerId)));

    
    List<TicketType> ticketTypes = event.getTicketTypeRequest().stream().map(
        ticketType -> {
          TicketType ticketTypeToCreate = new TicketType();
          ticketTypeToCreate.setName(ticketType.getName());
          ticketTypeToCreate.setPrice(ticketType.getPrice());
          ticketTypeToCreate.setDescription(ticketType.getDescription());
          ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
          return ticketTypeToCreate;
        }).collect(Collectors.toList());

    // create the event
    

    Event eventToCreate = new Event();
    eventToCreate.setName(event.getName());
    eventToCreate.setStart(event.getStart());
    eventToCreate.setEnd(event.getEnd());
    eventToCreate.setVenue(event.getVenue());
    eventToCreate.setSalesStart(event.getSalesStart());
    eventToCreate.setSalesEnd(event.getSalesEnd());
    eventToCreate.setStatus(event.getStatus());
    eventToCreate.setOrganizer(organizer);
    eventToCreate.setTicketTypes(ticketTypes);

    return eventRepository.save(eventToCreate);
  }

}

// according to my business logic and dev descision I decided that the createEvent function should
//take 2 parameters, the first one is the organizerId and the second one is the event object
//the organizerId is used to find the organizer and the event object is used to create the event
//the event object contains all the information about the event and the ticket types
//Here I can create inside the Event Entity another field TicketTypeRequest list for the ticketvTypes of the event next to the normal ticketTypes list,but this is
//bad descision because TicketTypeRequest is a DTO (Data Transfer Object) — it belongs to the API/request layer. TicketType is an Entity — it belongs to the persistence/database layer.
//So what I made is to create a TicketTypeRequest list and then convert it to TicketType list and then set it to the event object

