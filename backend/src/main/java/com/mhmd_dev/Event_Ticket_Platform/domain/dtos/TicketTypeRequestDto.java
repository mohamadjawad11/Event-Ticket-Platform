package com.mhmd_dev.Event_Ticket_Platform.domain.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeRequestDto {

  @NotBlank(message="The type name is required")
  private String name;

  @NotNull(message="The price is required")
  @PositiveOrZero(message="The price must be positive")
  private Double price;

  private String description;

  private Integer totalAvailable;

  

}
