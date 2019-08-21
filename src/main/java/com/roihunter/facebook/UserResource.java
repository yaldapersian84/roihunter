package com.roihunter.facebook;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> saveUserData(@Valid @RequestBody final UserDataRequest request) {


        UserDataResponse response = userService.saveUserData(request.getAccessToken(), request.getFbId());

//		System.out.println("********************" + response.getName());


        return ResponseEntity.ok().body(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> saveUserPhoto(@Valid @RequestBody final UserDataRequest request) {

        UserPhotosResponse response = userService.getUserPhotos(request.getAccessToken(), request.getFbId(), request.getSize());


//		System.out.println("********************" + response.getName());


        return ResponseEntity.ok().body(response);
    }
}
