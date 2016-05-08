package com.wrap.autosripts;

import static com.wrap.autosripts.util.Helpers.element;
import static com.wrap.autosripts.util.Helpers.enterInfo;
import static com.wrap.autosripts.util.Helpers.find_input_placeholder;
import static com.wrap.autosripts.util.Helpers.init;
import static com.wrap.autosripts.util.Helpers.scrollToElement;
import static com.wrap.autosripts.util.Helpers.setWait;
import static com.wrap.autosripts.util.Helpers.waitForElement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wrap.autosripts.page.CreateWrapPage;
import com.wrap.autosripts.page.HomePage;
import com.wrap.autosripts.page.RegisterPersonalAccountPage;
import com.wrap.autosripts.util.TestUtil;

/**
 * Unit test for Register User Account, create a new wrap and publish the wrap.
 * You can follow the link of documentation about the api:
 * http://selenium-python.readthedocs.io/locating-elements.html
 * 
 * @author hellen
 *
 */
public class WrapAutoTestCasesByHellen {
	private final static Logger LOGGER = Logger.getLogger(WrapAutoTestCasesByHellen.class);
	
	WebDriver driver;

	private final String USER_FIRST_NAME = "Hellen";
	private final String USER_LAST_NAME = "Gao";
	private final String USER_COMPANY = "Wrap";
	private final String DEFAULT_PASSWORD = "password23";

	@Before
	public void launchapp() {
		driver = TestUtil.getChromeWebDriver(TestUtil.WEB_DRIVER_HOME_PATH, TestUtil.BASE_URL);
		init(driver, 30);
		setWait(8);
	}

	@Test
	public void testRegisterPersonalAccount() throws Exception {
		
		signUp();
		createWrap();
		publish();
	}

	private void signUp() throws Exception {
		LOGGER.info("Prepare to set up a new user via Email --->");
		waitForElement(By.xpath(HomePage.LOG_IN)).click();
		Thread.sleep(3000);
		
		//TODO add asserts for some basic verification....
		assertEquals("LOG IN", element(By.xpath(RegisterPersonalAccountPage.X_PATH_SIGN_UP_TITLE_TEXT)).getText());
		assertEquals("Forgot your Password?", element(By.xpath(RegisterPersonalAccountPage.X_PATH_SIGN_UP_CONTENT_LINK_TEXT)).getText());
		assertEquals("Don't have an account? Sign Up", element(By.xpath(RegisterPersonalAccountPage.X_PATH_SIGN_UP_FOOTER_TEXT)).getText());
		
		waitForElement(By.xpath(RegisterPersonalAccountPage.X_PATH_SIGN_UP_WITH_NEW_ACCOUNT)).click();

		// sign up with email
		scrollToElement(By.xpath(HomePage.X_PATH_TRY_SMALL_BUSINESS_FOR_FREE_LABEL));
		element(By.xpath(HomePage.X_PATH_SIGN_UP_PERSONAL_FREE)).click();

		enterInfo(find_input_placeholder(RegisterPersonalAccountPage.USER_EMAIL_PLACEHOLDER),
				TestUtil.generateEmail(25));
		LOGGER.info("Email : " + TestUtil.generateEmail(25));
		element(By.xpath(RegisterPersonalAccountPage.X_PATH_SIGN_UP_WITH_EMAIL)).click();

		// create userName and password
		enterInfo(find_input_placeholder(RegisterPersonalAccountPage.CREATE_USER_PLACEHOLDER),
				TestUtil.generateUserName());
		enterInfo(find_input_placeholder(RegisterPersonalAccountPage.PASSWORD_PLACEHOLDER), DEFAULT_PASSWORD);
		Thread.sleep(1000);
		LOGGER.info("GenerateUserName : " + TestUtil.generateUserName() +" ; "+ "Password : " + DEFAULT_PASSWORD);
		
		// set wait here for fraud check
		waitForElement(By.xpath(RegisterPersonalAccountPage.X_PATH_CREATE_ACCOUNT)).click();

		// Personal Info
		enterInfo(find_input_placeholder(RegisterPersonalAccountPage.FIRST_NAME_PLACEHOLDER), USER_FIRST_NAME);
		enterInfo(find_input_placeholder(RegisterPersonalAccountPage.LAST_NAME_PLACEHOLDER), USER_LAST_NAME);
		enterInfo(find_input_placeholder(RegisterPersonalAccountPage.COMPONAY_PLACEHOLDER), USER_COMPANY);
		LOGGER.info("UserName : " + USER_FIRST_NAME +" "+ USER_LAST_NAME +" ; "+ "Company : "+ USER_COMPANY);
		
		element(By.xpath(RegisterPersonalAccountPage.X_PATH_CREATE_ACCOUNT_WITH_COMPLETE_INFO)).click();
		Thread.sleep(2000);
	}
	
	private void createWrap() throws InterruptedException {
		LOGGER.info("Prepare to create a new Wrap --->");
		
		//TODO add asserts for some basic verification....
				
		waitForElement(By.xpath(CreateWrapPage.X_PATH_CREATE_NEW_WRAP_LINK)).click();
		Thread.sleep(5000);
		element(By.xpath(CreateWrapPage.X_PATH_WRAP_USE_BUTTON)).click();
		LOGGER.info("Create a successful Wrap!");
	}

	private void publish() throws InterruptedException {
		LOGGER.info("Prepare to publish a Wrap --->");
		
		//TODO add asserts for some basic verification....
		
		waitForElement(By.xpath(CreateWrapPage.X_PATH_PUBLISH_WRAP_BUTTON)).click();
		waitForElement(By.xpath(CreateWrapPage.X_PATH_CONGRATULATION_PAGE_CLOSE)).click();
		waitForElement(By.xpath(CreateWrapPage.X_PATH_PUBLISH_SUCESSFULLY_CLOSE)).click();
		Thread.sleep(1000);
		waitForElement(By.xpath(CreateWrapPage.X_PATH_CREATE_WRAP_CLOSE)).click();
		Thread.sleep(3000);
		LOGGER.info("A new wrap was published !");
	}
	
	@After
	public void quit() {
		driver.quit();
	}
}
