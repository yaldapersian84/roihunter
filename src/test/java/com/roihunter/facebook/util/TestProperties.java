package com.roihunter.facebook.util;

import org.springframework.test.context.TestPropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Masoud.
 */
public class TestProperties {
	private final String DEFAULT_SPRING_FILE = "application.properties";

	private final Properties properties;

	public TestProperties() {
		this.properties = new Properties();
		load(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_SPRING_FILE));
	}

	public boolean get(String key, boolean defaultValue) {
		return Boolean.parseBoolean(this.properties.getProperty(key, String.valueOf(defaultValue)));
	}

	public String get(String key, String defaultValue) {
		return this.properties.getProperty(key, defaultValue);
	}

	public int get(String key, int defaultValue) {
		return Integer.parseInt(this.properties.getProperty(key, String.valueOf(defaultValue)));
	}

	public void add(Class<?> clazz) throws IOException {
		if (clazz == null || clazz.getAnnotation(TestPropertySource.class) == null) {
			return;
		}

		String[] list = getLocations(clazz.getAnnotation(TestPropertySource.class));
		for (String propertiesFile : list) {
			InputStream is;
			String file = propertiesFile.toLowerCase().trim();

			if (file.startsWith("classpath:")) {
				is = this.getClass().getClassLoader().getResourceAsStream(file.replace("classpath:", ""));
			} else {
				is = new FileInputStream(file);
			}

			load(is);
		}
	}

	private String[] getLocations(TestPropertySource source) {
		Set<String> set = new HashSet<>();

		for (String str : source.value()) {
			set.add(str);
		}

		for (String str : source.locations()) {
			set.add(str);
		}

		return set.toArray(new String[]{});
	}

	private void load(InputStream is) {
		if (is != null) {
			try {
				this.properties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
