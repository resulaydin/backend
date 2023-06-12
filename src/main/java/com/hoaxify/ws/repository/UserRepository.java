package com.hoaxify.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hoaxify.ws.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
//	boolean existsByUsername(String name);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
	boolean existsByUsername(@Param("username") String username);

}
