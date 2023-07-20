package com.hoaxify.ws.udemy.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	// Spring Data JPA  kullanarak:
//	boolean existsByUsername(String name); 
	
//	// Query Annotation kullanarak:
//	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
//	boolean existsByUsername(@Param("username") String username);
//	@Query("select u from User u where u.username = :username")
//	User findByUsername(@Param("username") String name);
	
	

//	@Query(value ="Select u from User u")
//	Page<UserProjection> getAllUsersProjection(Pageable page);
	
	User findByUsername( String username);
	boolean existsByUsername( String username);
	
	Page<User> findByUsernameNot(String username,Pageable page);
}
