package com.wrap.autosripts.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This Helpers class provides some general methods for locating elements of pages.
 * 
 * @author hellen
 *
 */
public abstract class Helpers {

	public static WebDriver driver;
	private static WebDriverWait driverWait;

	/**
	 * Initialize the webdriver. Must be called before using any helper methods.
	 * *
	 */
	public static void init(WebDriver webDriver, int waittime) {
		driver = webDriver;
		driverWait = new WebDriverWait(webDriver, waittime);
	}

	/**
	 * Set implicit wait in seconds *
	 */
	public static void setWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Wait 30 seconds for locator to find an element *
	 */
	public static WebElement waitForElement(By locator) {
		return driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Return an element by locator *
	 */
	public static WebElement element(By locator) {
		return driver.findElement(locator);
	}

	public static WebElement find_input_placeholder(String placeHolder) {
		return driver.findElement(By.xpath("//input[@placeholder='" + placeHolder + "']"));
	}

	/**
	 * scroll page to specific web element
	 */
	public static void scrollToElement(By webElement) {
		WebElement element = driver.findElement(webElement);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void enterInfo(WebElement element, String content) {
		element.click();
		element.sendKeys(content);
	}

	public static WebDriverWait getDriverWait() {
		return driverWait;
	}

	public static void setDriverWait(WebDriverWait driverWait) {
		Helpers.driverWait = driverWait;
	}
}