package com.mhmd_dev.Event_Ticket_Platform.domain.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import com.mhmd_dev.Event_Ticket_Platform.domain.TicketTypeRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="events")
@Getter //generate getFieldName() for every field in the class
@Setter //generate setFieldName() for every field in the class
@NoArgsConstructor //generate a constructor with no arguments
@AllArgsConstructor //generate a constructor with arguments for every field in the class
@Builder // is the builder pattern for example without builder I build object like this:
         //      User user = new User("Jawad", "jawad@email.com", 25);
         //while using builder:     User user = User.builder()
        //                                      .name("Jawad")
        //                                      .email("jawad@email.com")
        //                                      .age(25)
        //                                      .build();

public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id",updatable = false,nullable = false)
  private UUID id;

  @Column(name = "name",nullable = false)
  private String name;

  @Column(name = "starts_at",nullable = true)
  private LocalDateTime start;

  @Column(name = "ends_at",nullable = true)
  private LocalDateTime end;

  @Column(name="venue",nullable=false)
  private String venue;

  @Column(name="sales_start",nullable=true)
  private LocalDateTime salesStart;

  @Column(name="sales_end",nullable=true)
  private LocalDateTime salesEnd;

  @Column(name="status",nullable=false)
  @Enumerated(EnumType.STRING)
  private EventStatusEnum status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "organizer_id", nullable = false)
  private User organizer;

  @ManyToMany(mappedBy = "attendingEvents")
  private List<User> attendees=new ArrayList<>();

  @ManyToMany(mappedBy = "staffingEvents")
  private List<User> staff=new ArrayList<>();

  @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
  private List<TicketType> ticketTypes=new ArrayList<>();

  @CreatedDate
  @Column(name = "created_at", nullable = false,updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

    
}
