package com.roihunter.facebook.exception;

import com.roihunter.facebook.ResultStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PspClientException extends PspGeneralException {

	private static final long serialVersionUID = 906300249598577203L;

	private String pspErrorCode;

	private String pspErrorMessage;

	public PspClientException(String errorMessage) {
		this(ResultStatus.PSPPROXY_PSP_UNKNOWN_ERROR, null, errorMessage, null);
	}

	public PspClientException(ResultStatus resultStatus) {
		this(resultStatus, null, null, null);
	}

	public PspClientException(ResultStatus resultStatus, String pspErrorCode) {
		this(resultStatus, pspErrorCode, null, null);
	}

	public PspClientException(ResultStatus resultStatus, Throwable cause) {
		this(resultStatus, null, cause.getMessage(), cause);
	}

	public PspClientException(ResultStatus resultStatus, String pspErrorCode, String pspErrorMessage) {
		this(resultStatus, pspErrorCode, pspErrorMessage, null);
	}

	public PspClientException(ResultStatus resultStatus, String pspErrorCode, String pspErrorMessage, Throwable cause) {
		super(resultStatus.toString(), cause);
		this.pspErrorCode = pspErrorCode;
		this.pspErrorMessage = pspErrorMessage;
		this.resultStatus = resultStatus;
	}

	@Override
	public String toString() {
		return String.format("PspClientException: %s, %s", pspErrorCode, resultStatus);
	}
}
