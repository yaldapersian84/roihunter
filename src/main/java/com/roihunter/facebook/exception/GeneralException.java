package com.roihunter.facebook.exception;

import com.roihunter.facebook.model.response.ResultStatus;

public class GeneralException extends BusinessException {

	private static final long serialVersionUID = -2273769869370991999L;

	protected ResultStatus resultStatus = ResultStatus.FB_GENERIC_ERROR;
	
	public GeneralException(String message) {
		super(message);
	}

	public GeneralException(Throwable cause) {
		super(cause);
	}

	public GeneralException(String message, Throwable cause) {
		super(message, cause);
	}


	public GeneralException(ResultStatus resultStatus, String errorMessage) {
		super(errorMessage, null);
		this.resultStatus = resultStatus;
	}

	@Override
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

}
