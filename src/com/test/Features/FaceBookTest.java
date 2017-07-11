package com.test.Features;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.test.common.BaseDriverClass;
import com.test.common.ConfigProperties;
import com.test.webIdentifiers.FaceBookTestIdentifiers;

public class FaceBookTest extends BaseDriverClass {
	private String username;
	private String password;
	private String baseUrl;

	FaceBookTest() {
		super.setup();
		username = ConfigProperties.getProperty("FaceBookTest.username");
		password = ConfigProperties.getProperty("FaceBookTest.password");
		baseUrl = "https://www.facebook.com";
	}

	void teardown() {
		super.tearDown();

	}

	void testLogin() {
		driver.get(baseUrl);
		driver.findElement(By.xpath(FaceBookTestIdentifiers.username)).sendKeys(username);
		driver.findElement(By.xpath(FaceBookTestIdentifiers.password)).sendKeys(password);
		driver.findElement(By.xpath(FaceBookTestIdentifiers.logIn)).click();
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("FaceBook"));
	}

	void postStatus(String message) {
		driver.findElement(By.xpath(FaceBookTestIdentifiers.post)).sendKeys("");
		driver.findElement(By.xpath("//button")).click();

	}

	public static void main(String[] args) {
		FaceBookTest test = new FaceBookTest();
		test.testLogin();
		test.postStatus("");
		test.teardown();
	}
}