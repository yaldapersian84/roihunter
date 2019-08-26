package com.roihunter.facebook.model.response;

import java.io.IOException;
import java.util.Properties;

public enum ResultStatus {
	SUCCESS(0, "success"),
	UNKNOWN(1, "unknown.error"),
	FAILURE(1000, "failure"),
	BAD_REQUEST(1001, "bad.request"),
	USER_NOT_FOUND(1002, "user.not.found"),
	DATABASE_EXCEPTION(1003, "database.error"),
	END_POINT_TIMEOUT(1004, "endpoint.connection.failure"),
	INVALID_PARAMETER(1005, "core.invalid.parameter.exception"),
	FB_UNKNOWN_ERROR(1006, "facebook.generic.error"),
	FB_GENERIC_ERROR(1007, "facebook.generic.error"),
	FORBIDDEN_REQUEST(1008, "forbidden.request");

	private final String description;
	private final Integer status;
	private Properties errorMessageProperties;

	private ResultStatus(Integer status, String description) {
		this.populateConfigProperty();
		this.status = status;
		String errorMsg = this.errorMessageProperties.getProperty(description);
		if (errorMsg != null) {
			this.description = errorMsg;
		} else {
			this.description = description;
		}

	}

	private void populateConfigProperty() {
		try {
			this.errorMessageProperties = new Properties();
			this.errorMessageProperties.load(this.getClass().getResourceAsStream("/errormsg.properties"));
		} catch (IOException var2) {
			var2.printStackTrace();
		}

	}

	public String getDescription() {
		return this.description;
	}

	public Integer getStatus() {
		return this.status;
	}
}
