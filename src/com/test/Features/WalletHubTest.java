package com.test.Features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.common.BaseDriverClass;
import com.test.webIdentifiers.WalletHubIdentifiers;

public class WalletHubTest extends BaseDriverClass {
	private String baseUrl;
	private String username;
	private String password;

	WalletHubTest() {
		super.setup();
		baseUrl = "http://wallethub.com/profile/test_insurance_company/";
		username = "";
		password = "";
	}

	void teardown() {
		super.tearDown();

	}

	void testLogin() {
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(WalletHubIdentifiers.loginLink))));
		driver.findElement(By.xpath(WalletHubIdentifiers.loginLink)).click();
		driver.findElement(By.xpath(WalletHubIdentifiers.username)).sendKeys(username);
		driver.findElement(By.xpath(WalletHubIdentifiers.password)).sendKeys(password);
		driver.findElement(By.xpath(WalletHubIdentifiers.logIn)).click();
		System.out.println(driver.getTitle().toString());
		Assert.assertTrue(driver.getTitle().contains("Test Insurance Company Reviews"));
	}

	void testRating() {
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(WalletHubIdentifiers.loginLink))));

		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Rating:')]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebElement star1 = driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[1]"));
		// wait.until(ExpectedConditions.visibilityOf(star1));
		action.moveToElement(star1).build().perform();
		Assert.assertTrue("hover".equalsIgnoreCase(star1.getAttribute("className")));

		WebElement star2 = driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[2]"));
		action.moveToElement(star2).build().perform();
		Assert.assertTrue("hover".equalsIgnoreCase(star2.getAttribute("className")));

		WebElement star3 = driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[3]"));
		action.moveToElement(star3).build().perform();
		Assert.assertTrue("hover".equalsIgnoreCase(star3.getAttribute("className")));

		WebElement star4 = driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[4]"));
		action.moveToElement(star4).build().perform();
		Assert.assertTrue("hover".equalsIgnoreCase(star4.getAttribute("className")));

		WebElement star5 = driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[5]"));
		action.moveToElement(star5).build().perform();
		Assert.assertTrue("hover".equalsIgnoreCase(star5.getAttribute("className")));
		action.moveToElement(star5).click().build().perform();
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Reviews | Write Review"));
		System.out.println("Done!!!");
	}

	void testPostReview() {

		driver.findElement(By.xpath("//div[contains(@class,'dropdown-list-new')]")).click();
		driver.findElement(By.xpath("//ul[contains(@class,'drop-el')]//A[contains(text(),'Health')]")).click();
		driver.findElement(By.xpath("//textarea[contains(@name,'review')]")).sendKeys(
				"We have identified more than 294 mortgage offers from banks and mortgage brokers across the country that may meet your needs.In order to track relevant mortgage rates and find the right home loan for your particular situation, we recommend using the filters below. This will allow you to search based on important criteria such as mortgage type, loan amount and loan duration. Once you find the mortgage loan that best meets your needs, we will redirect you to the issuing institution's Web page so that you can submit your mortgage application.");
		driver.findElement(By.xpath("//input[contains(@value,'Submit')]")).click();
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Test Insurance"));

	}

	void testProfileFeed() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//nav/a[@class='user']"))));

		WebElement element = driver.findElement(By.xpath("//nav/a[@class='user']"));
		Actions action = new Actions(driver);
		action.moveToElement(element).click(driver.findElement(By.xpath("//nav[@id='m-user']//a[text()='Profile']")))
				.build().perform();
		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(),'My Profile')]"))));

	}

	public static void main(String[] args) {
		WalletHubTest wtest = new WalletHubTest();
		// wtest.testLogin();
		wtest.testRating();
		wtest.testPostReview();
		// wtest.testProfileFeed();
	}

}
