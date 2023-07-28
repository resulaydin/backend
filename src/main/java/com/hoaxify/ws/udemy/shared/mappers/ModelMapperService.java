package com.hoaxify.ws.udemy.shared.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forResponse();
	ModelMapper forReqest();
}
