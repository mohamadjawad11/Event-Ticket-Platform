package com.mhmd_dev.Event_Ticket_Platform.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhmd_dev.Event_Ticket_Platform.domain.Entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
  
}

//JpaRepository is an interface provided by Spring Data JPA. I extend it and Spring automatically generates the implementation at runtime — no SQL, no boilerplate.
//Most important methods are save(), findById(), findAll(), deleteById(), deleteAll() and existsById().
