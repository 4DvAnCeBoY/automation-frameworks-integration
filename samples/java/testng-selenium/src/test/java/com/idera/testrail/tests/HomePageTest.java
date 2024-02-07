package com.idera.testrail.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;


public class HomePageTest {

    public static WebDriver driver;
    public static final String USERNAME = System.getenv("LT_USERNAME");
    public static final String ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    public static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

    @BeforeMethod
    void setup() throws MalformedURLException {
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

    @Test
    public void verifyTitleOfHomePage() {
        assertTrue(driver.getTitle().contains("TestRail"));
    }

    @Test
    void verifyPresenceOfDemoLinkOnHomePage() {
        By demoButtonSelector = By.linkText("Get a Demo");
        WebElement demoButton = driver.findElement(demoButtonSelector);
        assertTrue(demoButton.isDisplayed());
    }

    @Test
    void invalidTest() {
        assertTrue(driver.getTitle().contains("WRONG TITLE"));
    }

    @AfterMethod
    void tearDown() {
        // Quit Driver after each test is completed
        driver.quit();
    }
}
