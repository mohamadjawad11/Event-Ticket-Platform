package com.mhmd_dev.Event_Ticket_Platform.mappers;

import org.mapstruct.Mapper;
import com.mhmd_dev.Event_Ticket_Platform.domain.CreateEventRequest;
import com.mhmd_dev.Event_Ticket_Platform.domain.TicketTypeRequest;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.Event;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.TicketType;
import com.mhmd_dev.Event_Ticket_Platform.domain.dtos.CreateEventRequestDto;
import com.mhmd_dev.Event_Ticket_Platform.domain.dtos.CreateEventResponseDto;
import com.mhmd_dev.Event_Ticket_Platform.domain.dtos.TicketTypeResponseDto;
import com.mhmd_dev.Event_Ticket_Platform.domain.dtos.TicketTypeRequestDto;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface EventMapper {

  //Convert TicketTypeRequestDto into TicketTypeRequest
  TicketTypeRequest fromDto(TicketTypeRequestDto dto);
  
  //Means Convert CreateEventRequestDto into CreateEventRequest
  CreateEventRequest fromDto(CreateEventRequestDto dto);

  //Convert TicketType entity into TicketTypeResponseDto
  TicketTypeResponseDto toDto(TicketType ticketType);

  //Convert Event entity into CreateEventResponseDto
  CreateEventResponseDto toDto(Event createdEvent);
  
}


//The Event Mapper is like a translator
// My app has 2 kinds of objects DTO Objects(CreateEventRequestDto, TicketTypeRequestDto) and Domain Objects(Event, TicketType)
// The Event Mapper is used to convert between the two

// Example Flow:
// 1. The client sends a request to create an event with a list of ticket types
// 2. The controller receives the request and converts it to a CreateEventRequestDto object
// 3. The Event Mapper converts the CreateEventRequestDto object to a CreateEventRequest object
// 4. The Event Service uses the CreateEventRequest object to create the event

// Frontend sends JSON
//         ↓
// CreateEventRequestDto
//         ↓      EventMapper converts it
// CreateEventRequest
//         ↓
// EventService uses it

/**
 * MapStruct mapper for Event-related DTO <-> domain object conversions.
 *
 * MapStruct generates the implementation at compile time based on matching
 * field names between source and target types — no manual mapping logic needed.
 * componentModel = "spring" registers the generated impl as a Spring bean.
 * unmappedTargetPolicy = IGNORE skips compiler warnings for unmapped fields.
 *
 * Nested mapping: fromDto(CreateEventRequestDto) automatically reuses
 * fromDto(TicketTypeRequestDto) for any nested ticket type list/field.
 */
