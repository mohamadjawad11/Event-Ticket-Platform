package com.mhmd_dev.Event_Ticket_Platform.domain.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.EventStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {

  @NotBlank(message = "Name is required")
  private String name;

  private LocalDateTime start;

  private LocalDateTime end;

  @NotBlank(message = "Venue is required")
  private String venue;

  private LocalDateTime salesStart;

  private LocalDateTime salesEnd;

  @NotNull(message = "Status is required")
  private EventStatusEnum status;

  @NotEmpty(message = "Ticket types are required")
  private List<TicketTypeRequestDto> ticketTypeRequest = new ArrayList<>();
}

// CreateEventRequest.java is used in the services layer to create a new event
// while CreateEventRequestDto.java is used in the controller layer to create a
// new event.