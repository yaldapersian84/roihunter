package com.roihunter.facebook.model.response;

import com.roihunter.facebook.model.response.UserProfilePhotoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDataResponse {

	private String id;

	private String name;

	private String gender;

	private UserProfilePhotoResponse picture;
}
