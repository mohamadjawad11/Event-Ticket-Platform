package com.mhmd_dev.Event_Ticket_Platform.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class User {
  
    @Id
    @Column(name = "id",updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    //cascade = CascadeType.ALL means that when a user is deleted, all their events will also be deleted
    //mappedBy = "organizer" means that the organizer field in the Event class is mapped to this user
    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL)
    private List<Event> oraganizedEvents=new ArrayList<>();

    //joinTable is used to create a many-to-many relationship between User and Event
    //joinColumns is used to specify the column in the join table that references the User table
    //inverseJoinColumns is used to specify the column in the join table that references the Event table
    @ManyToMany
    @JoinTable(
      name="user_attending_events",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    
    private List<Event> attendingEvents=new ArrayList<>();


    @ManyToMany
    @JoinTable(
        name="user_staffing_events",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> staffingEvents=new ArrayList<>();


    @CreatedDate
    @Column(name = "created_at", nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
