package com.roihunter.facebook.web;


import com.roihunter.facebook.exception.GeneralException;
import com.roihunter.facebook.model.dto.UserPhotoResponseDto;
import com.roihunter.facebook.model.request.UserDataRequest;
import com.roihunter.facebook.model.response.UserInfoResponse;
import com.roihunter.facebook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;


    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveUserInfo(@Valid @RequestBody final UserDataRequest request) {

         userService.saveUserData(request.getAccessToken(), request.getFbId(), request.getSize());

        return ResponseEntity.ok().build();
    }
    @GetMapping( path="/{user_fb_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserInfoResponse> getUserData(@NotBlank @PathVariable String user_fb_id) throws GeneralException {

        return ResponseEntity.ok(userService.getUserInfo(user_fb_id));
    }

    @GetMapping( path="/{user_fb_id}/photos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserPhotoResponseDto>> getUserPhotos(@NotBlank @PathVariable String user_fb_id) throws GeneralException {

        return ResponseEntity.ok().body(userService.getUserPhoto(user_fb_id));
    }
    @DeleteMapping( path="/{user_fb_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteUser(@NotBlank @PathVariable String user_fb_id) throws GeneralException {

        userService.deleteUser(user_fb_id);

        return ResponseEntity.ok().build();
    }
}
