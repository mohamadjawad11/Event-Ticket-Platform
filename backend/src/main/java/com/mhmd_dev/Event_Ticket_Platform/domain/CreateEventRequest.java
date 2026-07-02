package com.mhmd_dev.Event_Ticket_Platform.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateEventRequest {
  //no need for the id,attendee,staff in the request
  // I need name,start,end,venue,salesStart,salesEnd,status
  private String name;
  private LocalDateTime start;
  private LocalDateTime end;
  private String venue;
  private LocalDateTime salesStart;
  private LocalDateTime salesEnd;
  private EventStatusEnum status;
  //I want to add the ticketType but I in the creating event request but I don't want to use all
  //the fields of the ticketType so I will use the ticketTypeRequest
  private List<TicketTypeRequest> ticketTypeRequest=new ArrayList<>();
}

//The CreateEventRequest will be used to create an event,it contains all the fields of the event to be created

//each event has a list of ticketTypes
