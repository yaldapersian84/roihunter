package com.roihunter.facebook.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roihunter.facebook.ResultStatus;
import com.roihunter.facebook.exception.PspClientException;
import com.roihunter.facebook.facebookErrorResponse;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class fbClientErrorDecoder extends ErrorDecoder.Default {
	private final ObjectMapper objectMapper;

	public fbClientErrorDecoder() {
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Exception decode(String methodKey, Response response) {
		facebookErrorResponse responseService = read(response);
		if (responseService == null) {
			return new PspClientException(ResultStatus.FAILURE);
		}
		return new PspClientException(responseService.getResult().getTitle(), responseService.getPspResponseCode(), responseService.getPspResponseDesc());
	}

	private facebookErrorResponse read(Response response) {
		try {
			return objectMapper.readValue(Util.toString(response.body().asReader()), facebookErrorResponse.class);
		} catch (Exception e) {
//			logger.error("couldn't parse general response: {}", response);
			return null;
		}
	}

}
