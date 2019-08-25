package com.roihunter.facebook;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class FacebookClientIT {

	WireMockServer wireMockServer;

	@BeforeEach
	public void setup() {
		wireMockServer = new WireMockServer(8090);
		wireMockServer.start();
		setupStub();
	}

	@AfterEach
	public void teardown() {
		wireMockServer.stop();
	}

	public void setupStub() {
		wireMockServer.stubFor(get(urlEqualTo("/users/10214751204520609/photos"))
				.willReturn(aResponse().withHeader("Content-Type", "text/plain")
						.withStatus(200)
						.withBodyFile("json/get_user_photos.json")));

		wireMockServer.stubFor(get(urlEqualTo("/users/10214751204520609"))
				.willReturn(aResponse().withHeader("Content-Type", "text/plain")
						.withStatus(200)
						.withBodyFile("json/get_user_data.json")));
	}

	@Test
	public void status_get_user_photos_success() {
		given().
				when().
				get("http://localhost:8090/users/10214751204520609/photos").
				then().
				assertThat().statusCode(200);

	}

	@Test
	public void url_not_found() {
		given().
				when().
				get("http://localhost:8090/users/photos").
				then().
				assertThat().statusCode(404);
	}

	@Test
	public void get_user_data_success() {
		Response response = given().when().get("http://localhost:8090/users/10214751204520609");

		Assert.assertEquals("Yalda Ghasemi", response.jsonPath().get("name"));
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals("female", response.jsonPath().get("gender"));
		Assert.assertEquals("10214751204520609", response.jsonPath().get("fbId"));
		Assert.assertEquals("https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=10214751204520609&height=400&width=400&ext=1569325174&hash=AeTjlS5Q-zLlqIhO", response.jsonPath().get("profilePic"));
	}
}
