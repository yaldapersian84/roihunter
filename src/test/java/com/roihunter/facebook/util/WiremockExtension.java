package com.roihunter.facebook.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.Json;
import com.github.tomakehurst.wiremock.common.SafeNames;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.recording.SnapshotRecordResult;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class WiremockExtension implements BeforeAllCallback, AfterAllCallback {

	private boolean recording;

	private WireMockServer server;

	private String classPath;

	public WiremockExtension() {
		this.recording = false;
		this.classPath = null;
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		if (this.server == null || !this.server.isRunning()) {
			return;
		}

		if (this.recording) {
			SnapshotRecordResult recordResult = this.server.stopRecording();

			for (StubMapping mapping : recordResult.getStubMappings()) {
				String fileName = SafeNames.makeSafeFileName(mapping);
				String content = Json.write(mapping);

				BufferedWriter writer = new BufferedWriter(new FileWriter(this.classPath + "/mappings/" + fileName));
				writer.write(content);
				writer.close();
			}
		}

		server.stop();
	}

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		TestProperties properties = getProperties(context);

		boolean enabled = properties.get("fb.test.wiremock.enabled", true);
		if (!enabled) {
			return;
		}

		int port = properties.get("fb.test.wiremock.port", 8080);
		String url = properties.get("fb.test.wiremock.url", "localhost");
		String record = properties.get("fb.test.wiremock.record", null);

		this.classPath = properties.get("fb.test.wiremock.classpath", "src/test/resources");
		this.server = new WireMockServer(WireMockConfiguration.options()
				.usingFilesUnderClasspath(this.classPath)
				.extensions(new ResponseTemplateTransformer(true))
				.bindAddress(url).port(port));

		this.server.start();

		if (record != null && !record.isEmpty()) {
			this.server.startRecording(record);
			this.recording = true;
		}
	}

	private TestProperties getProperties(ExtensionContext context) throws IOException {
		String name = context.getRequiredTestClass().getName();
		TestProperties tp = (TestProperties) context.getStore(ExtensionContext.Namespace.create(name)).get("testProperties");

		if (tp == null) {
			tp = new TestProperties();
			tp.add(context.getRequiredTestClass());
			context.getStore(ExtensionContext.Namespace.create("wiremockExtension")).put("testProperties", tp);
		}

		return tp;
	}
}
