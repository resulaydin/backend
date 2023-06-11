package com.hoaxify.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoaxify.ws.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
