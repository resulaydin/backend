package com.hoaxify.ws.udemy.hoax;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoaxify.ws.udemy.user.User;

@Repository
public interface HoaxRepository extends JpaRepository<Hoax, Long> {

	Page<Hoax> findByUser(User user, Pageable page);
}
