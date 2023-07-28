package com.hoaxify.ws.udemy.hoax.responses;

import java.util.Date;

import com.hoaxify.ws.udemy.user.vm.UserVM;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllHoaxesResponse {

	
	private long id;
	@Size(min = 1, max = 1000)
	@Column(length = 1000)
	private String content;
	@Temporal(TemporalType.TIMESTAMP) // Default olarak hem TIME(saniye-dakika-saat) hemde DATE(gün-ay-yıl) tutulacak
	private Date timespan;
	
	private UserVM user;

}
