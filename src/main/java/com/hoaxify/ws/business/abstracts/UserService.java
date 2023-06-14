package com.hoaxify.ws.business.abstracts;

import com.hoaxify.ws.business.requests.CreateUserRequest;
import com.hoaxify.ws.business.responses.CreateUserResponse;

public interface UserService {
//	List<GetAllBrandsResponse> getAll();
	void add(CreateUserRequest createUserRequest);
	CreateUserResponse findByUsername(String username);
//	void update(UpdateBrandRequest updateBrandRequest);
//	void deleteById(int id);
//	void delete(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse getById(ProcessByIdBrandRequest processByIdBrandRequest);
//	GetDefaultBrandResponse  getByName(String name);

}
