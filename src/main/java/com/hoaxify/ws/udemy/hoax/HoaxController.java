package com.hoaxify.ws.udemy.hoax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.udemy.hoax.responses.GetAllHoaxesResponse;
import com.hoaxify.ws.udemy.hoax.vm.HoaxVM;
import com.hoaxify.ws.udemy.shared.CurrentUser;
import com.hoaxify.ws.udemy.shared.GenericResponse;
import com.hoaxify.ws.udemy.shared.mappers.ModelMapperService;
import com.hoaxify.ws.udemy.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/")
public class HoaxController {

	private HoaxService hoaxService;
	private ModelMapperService modelMapperService;

	public HoaxController(HoaxService hoaxService, ModelMapperService modelMapperService) {
		super();
		this.hoaxService = hoaxService;
		this.modelMapperService = modelMapperService;
	}

	@PostMapping("hoaxes")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse createHoax(@Valid @RequestBody Hoax hoax, @CurrentUser User user) {
		hoaxService.add(user, hoax);
		return new GenericResponse("hoax is created");
	}

	@GetMapping("hoaxes/engo")
	@ResponseStatus(HttpStatus.OK)
	public Page<GetAllHoaxesResponse> getAllEngo(
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
		Page<Hoax> hoaxes = hoaxService.getHoaxes(page);
		List<GetAllHoaxesResponse> getAllHoaxesResponses = hoaxes.stream()
				.map(hoax -> modelMapperService.forResponse().map(hoax, GetAllHoaxesResponse.class))
				.collect(Collectors.toList());
		return new PageImpl<>(getAllHoaxesResponses, page, hoaxes.getTotalElements());
	}

	@GetMapping("hoaxes")
	public Page<HoaxVM> getHoaxes(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
		return hoaxService.getHoaxes(page).map(HoaxVM::new);
	}

	@GetMapping("hoaxes/{id:[0-9]+}")
	public ResponseEntity<?> getHoaxesRelative(@PathVariable long id,
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page,
			@RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
			@RequestParam(name = "direction", defaultValue = "before") String direction ) {
		if (count) {
			long newHoaxesCount = hoaxService.getNewHoaxesCount(id);
			Map<String, Long> response = new HashMap<>();
			response.put("count", newHoaxesCount);
			return ResponseEntity.ok(response);
		}
		if (direction.equals("after")) {
			Page<Hoax> newHoaxes = hoaxService.getNewHoaxes(id,page);
			Page<HoaxVM> hoaxVMList = newHoaxes.map(HoaxVM::new);
			return ResponseEntity.ok(hoaxVMList);
		}
		return ResponseEntity.ok(hoaxService.getHoaxesRelative(id, page).map(HoaxVM::new));
	}

	@GetMapping("users/{username}/hoaxes")
	@ResponseStatus(HttpStatus.OK)
	public Page<HoaxVM> getUsersHoaxes(@PathVariable String username,
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page) {
		return hoaxService.getHoaxesOfUser(username, page).map(HoaxVM::new);
	}

	@GetMapping("users/{username}/hoaxes/{id:[0-9]+}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getUsersHoaxesRelative(@PathVariable long id, @PathVariable String username,
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable page,
			@RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
			@RequestParam(name="direction", defaultValue = "before") String direction 
			) {
		
		if (count) {
			long newHoaxesCount = hoaxService.getNewHoaxesOfUserRelative(id,username);
			Map<String, Long> response = new HashMap<>();
			response.put("count", newHoaxesCount);
			return ResponseEntity.ok(response);
		}
		
		if (direction.equals("after")) {
			Page<Hoax> newHoaxes = hoaxService.getNewHoaxesOfUser(id,username,page);
			Page<HoaxVM> hoaxVMList = newHoaxes.map(HoaxVM::new);
			return ResponseEntity.ok(hoaxVMList);
		}

		
		return ResponseEntity.ok(hoaxService.getHoaxesOfUserRelative(id, username, page).map(HoaxVM::new)); 
	}

}
