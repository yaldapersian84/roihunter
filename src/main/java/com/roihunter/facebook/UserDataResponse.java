package com.roihunter.facebook;

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
