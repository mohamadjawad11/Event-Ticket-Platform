package com.mhmd_dev.Event_Ticket_Platform.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhmd_dev.Event_Ticket_Platform.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
   

}
