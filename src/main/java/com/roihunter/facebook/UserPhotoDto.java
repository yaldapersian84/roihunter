package com.roihunter.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserPhotoDto {

	private String id;

	private String name;

	@JsonProperty("created_time")
	private String createdTime;
}
