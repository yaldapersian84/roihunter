package com.roihunter.facebook.model.response;

import lombok.Data;


@Data
public class facebookErrorResponse {

	private Result result;

	private String pspResponseCode;

	private String pspResponseDesc;

}
