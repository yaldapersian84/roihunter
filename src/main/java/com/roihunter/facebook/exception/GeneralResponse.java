package com.roihunter.facebook.exception;


import com.roihunter.facebook.Result;
import com.roihunter.facebook.ResultStatus;

public class GeneralResponse extends ResponseService {
	private static final long serialVersionUID = -9046751394123883723L;

	public GeneralResponse() {
		this.setResult(ResultStatus.SUCCESS);
	}

	public GeneralResponse(ResultStatus resultStatus) {
		this.setResult(resultStatus);
	}

	public GeneralResponse(Result result) {
		this.setResult(result);
	}

	public String toString() {
		return "GeneralResponse(super=" + super.toString() + ")";
	}
}
