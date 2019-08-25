package com.roihunter.facebook.model.response;

import com.roihunter.facebook.model.dto.UserProfilePhotoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserProfilePhotoResponse {

	private UserProfilePhotoDto data;
}
