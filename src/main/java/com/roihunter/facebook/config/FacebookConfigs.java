package com.roihunter.facebook.config;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FacebookConfigs {
	private final Environment environment;

	public FacebookConfigs(final Environment environment) {
		this.environment = environment;
	}

	public String getFbAccessToken() {
		return environment.getRequiredProperty("facebook.test.access.token");
	}

	public String getFbId() {
		return environment.getRequiredProperty("facebook.test.user.fb.id");
	}

}