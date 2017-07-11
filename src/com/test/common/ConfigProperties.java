package com.test.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

	public static final String CHROME_DRIVER_PATH = "CHROME_DRIVER_PATH";
	public static final String FF_BINARY_PATH = "FF_BINARY_PATH";
	public static final String IE_DRIVER_PATH = "IE_DRIVER_PATH";
	public static final String UIAUTOMATION_BASE_DIR = "user.dir";
	public static final String UIAUTOMATION_BROWSER = "UIAUTOMATION_BROWSER";
	public static final String CONFIG_FILE_PATH = System.getProperty(UIAUTOMATION_BASE_DIR);
	private static String browser;
	private static Properties prop = new Properties();
	public static final String FILE_SEP = File.separator;

	public static void loadConfig() {
		try {

			String fileName = "config.properties";

			if (CONFIG_FILE_PATH != null) {
				fileName = CONFIG_FILE_PATH + FILE_SEP + "resources" + FILE_SEP + fileName;
			}
			prop.load(new FileReader(fileName));
			browser = prop.getProperty(UIAUTOMATION_BROWSER);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getChromeDriverPath() throws ConfigException {
		String path = prop.getProperty(CHROME_DRIVER_PATH);
		if (path != null)
			return path;
		else {
			System.out.println(CHROME_DRIVER_PATH + "is null");
			throw new ConfigException(CHROME_DRIVER_PATH);
		}
	}

	public static String getIEDriverPath() throws ConfigException {
		String path = prop.getProperty(IE_DRIVER_PATH);
		if (path != null)
			return path;
		else {
			System.out.println(IE_DRIVER_PATH + "is null");
			throw new ConfigException(IE_DRIVER_PATH);
		}
	}

	public static String getBrowser() {
		return browser;
	}

	public static String getFireFoxBinaryPath() throws ConfigException {
		String path = prop.getProperty(FF_BINARY_PATH);
		if (path != null)
			return path;
		else {
			System.out.println(FF_BINARY_PATH + "is null");
			return null;
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
