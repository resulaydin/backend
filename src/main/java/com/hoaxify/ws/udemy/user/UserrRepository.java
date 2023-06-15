package com.hoaxify.ws.udemy.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserrRepository extends JpaRepository<Userr, Long> {
	// Spring Data JPA  kullanarak:
//	boolean existsByUsername(String name); 
	
	// Query Annotation kullanarak:
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Userr u WHERE u.username = :username")
	boolean existsByUsername(@Param("username") String username);
	
	@Query("select u from Userr u where u.username = :username")
	Userr findByUsername(@Param("username") String name);
}
