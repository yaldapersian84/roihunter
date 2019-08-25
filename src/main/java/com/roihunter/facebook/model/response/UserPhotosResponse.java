package com.roihunter.facebook.model.response;

import com.roihunter.facebook.model.dto.UserPhotoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class UserPhotosResponse {

	List<UserPhotoDto> data;
}
