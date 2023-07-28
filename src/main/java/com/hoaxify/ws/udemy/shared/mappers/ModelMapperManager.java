package com.hoaxify.ws.udemy.shared.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService {
	/*
	 * Yukarıda yazılan Annotations ile birlikte
	 * Her seferinde bundan oluşmayacak ve bu IOC' ye yerleşecektir.
	 * */ 
	private ModelMapper modelMapper; 

	@Override
	public ModelMapper forResponse() {
		/*
		 	Bu kod ile birlikte veritabanına istediğimiz alanları map edebilmekteyiz.
		 * */
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		return this.modelMapper;
	}

	@Override
	public ModelMapper forReqest() {
		/*
	 	Bu kod ile birlikte veritabanında bulunan tablo ile birebir map olmasını isteriz.
	 * */
		
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
	}

}
