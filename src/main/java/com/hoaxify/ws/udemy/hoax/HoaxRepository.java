package com.hoaxify.ws.udemy.hoax;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoaxify.ws.udemy.user.User;

@Repository
public interface HoaxRepository extends JpaRepository<Hoax, Long> {

	Page<Hoax> findByUser(User user, Pageable page);
	Page<Hoax> findByIdLessThan(long id,Pageable page);
	Page<Hoax> findByIdLessThanAndUser(long id, User user, Pageable page);
	Page<Hoax> findByIdGreaterThan(long id,Pageable page);
	Page<Hoax> findByIdGreaterThanAndUser(long id, User user, Pageable page);
	long countByIdGreaterThanAndUser(long id, User user);
	long countByIdGreaterThan(long id);
}
