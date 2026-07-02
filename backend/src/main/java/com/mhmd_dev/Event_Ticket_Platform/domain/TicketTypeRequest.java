package com.mhmd_dev.Event_Ticket_Platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TicketTypeRequest {
  private String name;
  private Double price;
  private String description;
  private Integer totalAvailable;
}

