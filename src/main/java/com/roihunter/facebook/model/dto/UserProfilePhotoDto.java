package com.roihunter.facebook.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserProfilePhotoDto {

	private long height;

	private long width;

	private boolean is_silhouette;

	private String url;
}
