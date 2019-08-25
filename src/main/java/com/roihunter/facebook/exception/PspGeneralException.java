package com.roihunter.facebook.exception;

import com.roihunter.facebook.model.response.ResultStatus;

public class PspGeneralException extends BusinessException {

	private static final long serialVersionUID = -2273769869370991999L;

	protected ResultStatus resultStatus = ResultStatus.PSPPROXY_GENERIC_ERROR;
	
	public PspGeneralException(String message) {
		super(message);
	}

	public PspGeneralException(Throwable cause) {
		super(cause);
	}

	public PspGeneralException(String message, Throwable cause) {
		super(message, cause);
	}


	public PspGeneralException(ResultStatus resultStatus, String errorMessage) {
		super(errorMessage, null);
		this.resultStatus = resultStatus;
	}

	@Override
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

}
