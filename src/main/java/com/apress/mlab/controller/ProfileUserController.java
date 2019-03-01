package com.apress.mlab.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apress.mlab.domain.ProfileUser;
import com.apress.mlab.repository.ProfileUserRepository;
import com.apress.mlab.validation.ToDoValidationError;
import com.apress.mlab.validation.ToDoValidationErrorBuilder;

@RestController
@RequestMapping("/auth")
public class ProfileUserController {

	private ProfileUserRepository profileUserRepository;

	@Autowired
	public ProfileUserController(ProfileUserRepository ProfileUserRepository) {
		this.profileUserRepository = ProfileUserRepository;
	}

	@GetMapping("/user")
	public ResponseEntity<Iterable<ProfileUser>> getToDos() {
		return ResponseEntity.ok(profileUserRepository.findAll());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<ProfileUser> getToDoById(@PathVariable String id) {
		Optional<ProfileUser> profileUser = profileUserRepository.findById(id);
		if (profileUser.isPresent())
			return ResponseEntity.ok(profileUser.get());
		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/user/{id}")
	public ResponseEntity<ProfileUser> setCompleted(@PathVariable String id) {
		Optional<ProfileUser> profileUser = profileUserRepository.findById(id);
		if (!profileUser.isPresent())
			return ResponseEntity.notFound().build();
		ProfileUser result = profileUser.get();		
		profileUserRepository.save(result);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri();
		return ResponseEntity.ok().header("Location", location.toString()).build();
	}

	@RequestMapping(value = "/user", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> createToDo(@Valid @RequestBody ProfileUser profileUser, Errors errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
		}
		ProfileUser result = profileUserRepository.save(profileUser);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ToDoValidationError handleException(Exception exception) {
		return new ToDoValidationError(exception.getMessage());
	}

}
