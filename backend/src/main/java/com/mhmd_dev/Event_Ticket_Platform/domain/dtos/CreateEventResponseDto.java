package com.mhmd_dev.Event_Ticket_Platform.domain.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.EventStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResponseDto {

  private UUID id;
  private String name;
  private LocalDateTime start;
  private LocalDateTime end;
  private String venue;
  private LocalDateTime salesStart;
  private LocalDateTime salesEnd;
  private EventStatusEnum status;
  private List<TicketTypeResponseDto> ticketTypes;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
}
