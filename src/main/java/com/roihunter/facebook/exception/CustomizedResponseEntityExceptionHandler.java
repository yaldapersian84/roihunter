package com.roihunter.facebook.exception;

import com.roihunter.facebook.model.response.Result;
import com.roihunter.facebook.model.response.ResultStatus;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.SizeLimitExceededException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.InvalidParameterException;


@Slf4j
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private Environment env;

	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
																  HttpStatus status, WebRequest request) {
		logger.error("validation exception {}", ex.getBindingResult().toString());
		return new ResponseEntity<>(new GeneralResponse(createResult(getCustomMessage(ex))), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ResponseService> handleBusinessException(BusinessException ex) {
		logger.error(ex.getResultStatus().getDescription(), ex);
		return new ResponseEntity<>(new GeneralResponse(ex.getResultStatus()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(InvalidParameterException.class)
	public final ResponseEntity<ResponseService> handleInvalidParameterException(InvalidParameterException ex) {
		logger.error(ResultStatus.INVALID_PARAMETER.getDescription(), ex);
		return new ResponseEntity<>(new GeneralResponse(ResultStatus.INVALID_PARAMETER), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public final ResponseEntity<ResponseService> handleDuplicateKeyException(DuplicateKeyException ex) {
		logger.error(ResultStatus.DATABASE_EXCEPTION.getDescription(), ex);
		return new ResponseEntity<>(new GeneralResponse(ResultStatus.DATABASE_EXCEPTION), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(UnsupportedOperationException.class)
	public final ResponseEntity<ResponseService> handleUnsupportedOperationException(UnsupportedOperationException ex) {
		logger.error(ResultStatus.FORBIDDEN_REQUEST.getDescription(), ex);
		return new ResponseEntity<>
				(new GeneralResponse(ResultStatus.FORBIDDEN_REQUEST), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({UnknownHostException.class, ConnectException.class, SocketTimeoutException.class})
	public final ResponseEntity<ResponseService> handleConnectionException(IOException ex) {
		logger.error(ResultStatus.END_POINT_TIMEOUT.getDescription(), ex);
		return new ResponseEntity<>(new GeneralResponse(ResultStatus.END_POINT_TIMEOUT), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(RetryableException.class)
	public final ResponseEntity<ResponseService> handleRetryableException(RetryableException ex) {
		logger.error(ResultStatus.END_POINT_TIMEOUT.getDescription(), ex);
		return new ResponseEntity<>(new GeneralResponse(ResultStatus.END_POINT_TIMEOUT), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(Throwable.class)
	public final ResponseEntity<ResponseService> handleGeneralException(Throwable throwable) {
		logger.error(ResultStatus.FAILURE.getDescription(), throwable);
		logger.error("general exception {}", throwable);
		return new ResponseEntity<>(new GeneralResponse(ResultStatus.FAILURE), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	private String getCustomMessage(MethodArgumentNotValidException ex) {
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			if (!StringUtils.isEmpty(error.getDefaultMessage())) {
				String customMessage = env.getRequiredProperty(error.getDefaultMessage());
				if (!StringUtils.isEmpty(customMessage))
					return customMessage;
			}
		}
		return null;
	}

	private Result createResult(String customMessage) {
		Result result = new Result();
		if (customMessage != null)
			result.setMessage(customMessage);
		else result.setMessage(ResultStatus.INVALID_PARAMETER.getDescription());
		result.setStatus(ResultStatus.INVALID_PARAMETER.getStatus());
		result.setTitle(ResultStatus.INVALID_PARAMETER);
		return result;
	}
}