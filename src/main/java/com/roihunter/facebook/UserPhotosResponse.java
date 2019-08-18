package com.roihunter.facebook;

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
