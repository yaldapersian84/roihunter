package com.roihunter.facebook.impl;

//import com.digipay.bankproxy.core.exception.BankClientException;
//import com.digipay.bankproxy.core.util.ClientExceptionUtils;
//import com.digipay.bankproxy.saman.exception.KookClientException;
//import com.digipay.common.response.ResultStatus;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Yalda.
 */
@Slf4j
@Aspect
@Component
public class SamanKookAspect {

//	@Before(value = "within(com.digipay.bankproxy.saman.client.SamanKookClient+) & execution(*)")
//	public void init(JoinPoint joinPoint) {
//		logger.info("invoke {} - {}", joinPoint.getSignature().getName(), joinPoint.getArgs()[0]);
//	}
//
//	@AfterReturning(pointcut = "within(com.digipay.bankproxy.saman.client.SamanKookClient+) & execution(*)", returning = "result")
//	public void verify(Object result) {
//		logger.info("received result {}", result);
//	}
//
//	@AfterThrowing(pointcut = "within(com.digipay.bankproxy.saman.client.SamanKookClient+) & execution(*)", throwing = "ex")
//	public void handle(KookClientException ex) throws BankClientException {
//		ResultStatus status;
//		int httpStatus = ex.getHttpStatus() == null ? -1 : ex.getHttpStatus();
//
//		switch (httpStatus) {
//			case 400:
//				status = ResultStatus.BANKPROXY_MANDATORY_FIELD_MISSING;
//				break;
//			case 401:
//			case 403:
//				status = ResultStatus.BANKPROXY_ACCESS_ERROR;
//				break;
//			case 408:
//			case 409:
//			case 500:
//				status = ResultStatus.BANKPROXY_SERVICE_NOT_AVAILABLE;
//				break;
//			default:
//				status = ResultStatus.BANKPROXY_BANK_UNKNOWN_ERROR;
//				break;
//		}
//
////		int errorCode;
////		try {
////			errorCode = Integer.valueOf(ex.getErrorCode());
////		} catch (Exception e) {
////			errorCode = -1;
////		}
//
//		throw new BankClientException(status, ex.getErrorCode(), ex.getMessage(), ex);
//	}
//
//	@AfterThrowing(pointcut = "within(com.digipay.bankproxy.saman.client.SamanKookClient+) & execution(*)", throwing = "ex")
//	public void handle(FeignException ex) throws BankClientException {
//		throw ClientExceptionUtils.deliver(ex);
//	}
}
