package com.mhmd_dev.Event_Ticket_Platform.domain.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeResponseDto {
  
  private UUID id;
  private String name;
  private Double price;
  private String description;
  private Integer totalAvailable;

  
}
