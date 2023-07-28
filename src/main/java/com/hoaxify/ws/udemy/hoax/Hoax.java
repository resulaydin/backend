package com.hoaxify.ws.udemy.hoax;

import java.util.Date;

import com.hoaxify.ws.udemy.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table(name ="hoaxes")
@Data
@Entity
public class Hoax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 1, max = 1000)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)  // Default olarak hem TIME(saniye-dakika-saat) hemde DATE(gün-ay-yıl) tutulacak 	
	private Date timestamp;
	
	@ManyToOne
	private User user;
}
