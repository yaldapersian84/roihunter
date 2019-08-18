package com.roihunter.facebook;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserResource {

	private final UserService userService;


	public UserResource(final UserService userService) {
		this.userService = userService;
	}

//	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<?> saveUserData(@Valid @RequestBody final UserDataRequest request, @RequestHeader HttpHeaders headers) {
//
//		UserDataResponse response = userService.saveUserDate(request.getAccessToken(), request.getFbId());
//
//		System.out.println("***************" + response.getName());
//
//
//		return null;
//	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> saveUserData2(@Valid @RequestBody final UserDataRequest request, @RequestHeader HttpHeaders headers) {

//		UserPhotosResponse response = userService.getUserPhotos(request.getAccessToken(), request.getFbId());

		UserDataResponse response = userService.saveUserDate(request.getAccessToken(), request.getFbId());

		System.out.println("********************" + response.getName());


		return null;
	}
}
