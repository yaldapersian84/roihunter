package com.roihunter.facebook.impl;

//import com.digipay.bankproxy.core.exception.BankClientException;
//import com.digipay.bankproxy.saman.exception.KookClientException;
//import com.digipay.common.response.ResultStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by Yalda.
 */
@Slf4j
public class SamanKookErrorDecoder extends ErrorDecoder.Default {
	private ObjectMapper objectMapper;

	public SamanKookErrorDecoder() {
		this.objectMapper = new ObjectMapper();
	}

//	@Override
//	public Exception decode(String methodKey, Response response) {
//		KookClientException clientException = read(response);
//		if (clientException == null || clientException.getErrorCode() == null) {
//			return new BankClientException(ResultStatus.FAILURE);
//		}
//		switch (clientException.getErrorCode()) {
//			case "341":
//				return new BankClientException(ResultStatus.BANKPROXY_VALIDATION_EXCEPTION);
//		}
//		return super.decode(methodKey, response);
//	}
//
//	private KookClientException read(Response response) {
//		try {
//			return objectMapper.readValue(Util.toString(response.body().asReader()), KookClientException.class);
//		} catch (IOException e) {
//			logger.error("couldn't parse general response: {}", response);
//			return null;
//		}
//	}
}
