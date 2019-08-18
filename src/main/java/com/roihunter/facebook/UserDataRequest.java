package com.roihunter.facebook;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class UserDataRequest {

	private static final long serialVersionUID = -2986422609422451469L;

	String accessToken;
	String fbId;
}
