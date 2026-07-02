package com.mhmd_dev.Event_Ticket_Platform.services.IService;

import java.util.UUID;

import com.mhmd_dev.Event_Ticket_Platform.domain.CreateEventRequest;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.Event;

public interface IEventService {

  public Event createEvent(UUID organizerId, CreateEventRequest event);  
} 
