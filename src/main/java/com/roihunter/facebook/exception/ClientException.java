package com.roihunter.facebook.exception;

import com.roihunter.facebook.model.response.ResultStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientException extends GeneralException {

	private static final long serialVersionUID = 906300249598577203L;

	private String pspErrorCode;

	private String pspErrorMessage;

	public ClientException(String errorMessage) {
		this(ResultStatus.FB_UNKNOWN_ERROR, null, errorMessage, null);
	}

	public ClientException(ResultStatus resultStatus) {
		this(resultStatus, null, null, null);
	}

	public ClientException(ResultStatus resultStatus, String pspErrorCode) {
		this(resultStatus, pspErrorCode, null, null);
	}

	public ClientException(ResultStatus resultStatus, Throwable cause) {
		this(resultStatus, null, cause.getMessage(), cause);
	}

	public ClientException(ResultStatus resultStatus, String pspErrorCode, String pspErrorMessage) {
		this(resultStatus, pspErrorCode, pspErrorMessage, null);
	}

	public ClientException(ResultStatus resultStatus, String pspErrorCode, String pspErrorMessage, Throwable cause) {
		super(resultStatus.toString(), cause);
		this.pspErrorCode = pspErrorCode;
		this.pspErrorMessage = pspErrorMessage;
		this.resultStatus = resultStatus;
	}

	@Override
	public String toString() {
		return String.format("ClientException: %s, %s", pspErrorCode, resultStatus);
	}
}
