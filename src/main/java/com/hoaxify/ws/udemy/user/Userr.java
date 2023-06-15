package com.hoaxify.ws.udemy.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "userrs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Userr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String username;
	@Column(name = "displayName")
	private String displayName;
//	@Column(name = "email")
//	private String email;
	@Column(name = "password")
	private String password;
	
	private String image;
	
}
