package com.idera.testrail.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;


class HomePageTest {

    public static WebDriver driver;
	public static final String USERNAME = System.getenv("LT_USERNAME");
    public static final String ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    public static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        options.addArguments("--no-proxy-server");
        options.addArguments("disable-infobars"); // Disabling infobars
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-extensions"); // Disabling extensions
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new RemoteWebDriver(new URL(GRID_URL), options);
        // Navigation: Open a website
        driver.navigate().to("https://www.gurock.com/testrail");
    }

    @AfterEach
    public void tearDown() {
        // Quit Driver after each test is completed
        driver.quit();
    }

    @Test
    void verifyTitleOfHomePage() {
        // Assertion: Check its title is correct
        assertTrue(driver.getTitle().contains("TestRail"));
    }

    @Test
    void verifyPresenceOfDemoLinkOnHomePage() {
        // Assertion: Check the presence of demo link
        By demoButtonSelector = By.linkText("Get a Demo");
        WebElement demoButton = driver.findElement(demoButtonSelector);
        assertTrue(demoButton.isDisplayed());
    }

    @Test
    void invalidTest() {
        assertTrue(driver.getTitle().contains("WRONG TITLE"));
    }
}
