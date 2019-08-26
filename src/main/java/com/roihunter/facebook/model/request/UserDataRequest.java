package com.roihunter.facebook.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@ToString
public class UserDataRequest {

	private static final long serialVersionUID = -2986422609422451469L;

	String accessToken;
	String fbId;

	// size of user pictures
	@NotNull
	Integer size;
}
