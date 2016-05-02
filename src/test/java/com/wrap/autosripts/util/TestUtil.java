package com.wrap.autosripts.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestUtil {
	private final static Logger LOGGER = Logger.getLogger(TestUtil.class);
	static {
		try {
			Properties prop = new Properties();
			InputStream inputStream = TestUtil.class.getClassLoader().getResourceAsStream("configuration.properties");
			prop.load(inputStream);
			WEB_DRIVER_HOME_PATH = prop.getProperty("chrome.driver.home.path");
			BASE_URL = prop.getProperty("wrap.base.url");
		} catch (Exception ex) {
			LOGGER.info(ex);
		}
	}
	public static String BASE_URL;

	public static final String CHROME_WEB_DRIVER_NAME = "webdriver.chrome.driver";

	public static String WEB_DRIVER_HOME_PATH;

	public static WebDriver getChromeWebDriver(String webDriverHomePath, String baseUrl) {
		// TODO:add comments for setProperty
		System.setProperty(CHROME_WEB_DRIVER_NAME, webDriverHomePath);
		WebDriver driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		return driver;
	}

	// get any other kind of web driver

	public static String generateEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890"; // numbers

		String email = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		email = temp.substring(0, temp.length() - 9) + "@wrap.org";
		return email;
	}

	public static String generateUserName() {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890"; // numbers
		return RandomStringUtils.random(8, allowedChars);
	}
}
