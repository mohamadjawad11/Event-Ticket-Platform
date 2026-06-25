package com.mhmd_dev.Event_Ticket_Platform.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketType {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false,updatable = false)
  private UUID id;

  @Column(name = "name",nullable = false)
  private String name;

  @Column(name = "price",nullable = false)
  private Double price;

  @Column(name = "total_available",nullable = true)
  private Integer totalAvailable;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="event_id")
  private Event event;

  @OneToMany(mappedBy = "type",cascade = CascadeType.ALL)
  private List<Ticket> tickets=new ArrayList<>();

  @CreatedDate
    @Column(name = "created_at", nullable = false,updatable = false)
    private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
