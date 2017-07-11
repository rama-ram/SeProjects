package com.test.common;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {

	protected static WebDriver getDriver(String browser, WebDriver driver) throws ConfigException {
		if ("firefox".equalsIgnoreCase(browser)) {
			FirefoxProfile profile = new FirefoxProfile();
			if (ConfigProperties.getFireFoxBinaryPath() != null) {
				FirefoxBinary fbin = new FirefoxBinary(new File(ConfigProperties.getFireFoxBinaryPath()));
				driver = new FirefoxDriver(fbin, profile);
			} else
				driver = new FirefoxDriver();

		} else if ("chrome".equalsIgnoreCase(browser)) {
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setJavascriptEnabled(true);
			System.setProperty("webdriver.chrome.driver", ConfigProperties.getChromeDriverPath());

			driver = new ChromeDriver(caps);
		} else if ("ie".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.ie.driver", ConfigProperties.getIEDriverPath());

			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			driver = new InternetExplorerDriver(ieCapabilities);

		} else {

			System.out.println("please provide a browser from one of the following: firefox/chrome/ie");
			return null;
		}
		return driver;
	}

}
