package com.hoaxify.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hoaxify.ws.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// Spring Data JPA  kullanarak:
//	boolean existsByUsername(String name); 
	
	// Query Annotation kullanarak:
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
	boolean existsByUsername(@Param("username") String username);
	
	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username") String name);
}
