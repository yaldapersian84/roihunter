package com.roihunter.facebook.exception;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.roihunter.facebook.model.response.Result;
import com.roihunter.facebook.model.response.ResultStatus;

public class ResponseService extends BaseService {
	private static final long serialVersionUID = 2466742670208814125L;
	private Result result;

	public ResponseService() {
	}

	@JsonProperty
	public void setResult(Result result) {
		this.result = result;
	}

	public void setResult(ResultStatus resultStatus) {
		if (resultStatus != null) {
			Result result = new Result();
			result.setTitle(resultStatus);
			result.setMessage(resultStatus.getDescription());
			result.setStatus(resultStatus.getStatus());
			result.setLevel(result.getResultLevel(resultStatus));
			this.result = result;
		}
	}

	public void setResult(ResultStatus resultStatus, String message) {
		Result result = new Result();
		result.setTitle(resultStatus);
		result.setMessage(message);
		result.setStatus(resultStatus.getStatus());
		result.setLevel(result.getResultLevel(resultStatus));
		this.result = result;
	}

	public void setResult(ResultStatus resultStatus, boolean isExternal) {
		if (resultStatus != null) {
			Result result = new Result();
			result.setMessage(resultStatus.getDescription());
			result.setStatus(resultStatus.getStatus());
			if (!isExternal) {
				result.setTitle(resultStatus);
			}

			result.setLevel(result.getResultLevel(resultStatus));
			this.result = result;
		}
	}

	public Result getResult() {
		return this.result;
	}

	public String toString() {
		return "ResponseService(super=" + super.toString() + ", result=" + this.getResult() + ")";
	}
}
