package com.test.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class BaseDriverClass {
	protected static WebDriver driver;

	public void setup() {
		try {
			ConfigProperties.loadConfig();
			driver = BrowserFactory.getDriver(ConfigProperties.getBrowser(), driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (ConfigException e) {
			e.printStackTrace();
			tearDown();
		}
	}

	public void tearDown() {
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
		try {
			Process proc = Runtime.getRuntime().exec("pkill firefox");
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: Killing firefox processes");
		}

	}

}
