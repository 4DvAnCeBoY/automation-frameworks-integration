package demo;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {
	private WebDriver driver = null;
	private WebDriverWait wait;
	public static final String USERNAME = System.getenv("LT_USERNAME");
    public static final String ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    public static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";


	@Before
	public void initWebDriverSetup() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--start-maximized");
		options.addArguments("--no-proxy-server");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-web-security");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
	    driver = new RemoteWebDriver(new URL(GRID_URL), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Given("^I navigate to the testrail website$")
	public void i_navigate_to_the_testrail_website() {
		driver.navigate().to("https://www.testrail.com");
	}

	@When("^I validate the page is loaded$")
	public void i_validate_the_page_is_loaded() {
		Assert.assertTrue(driver.getTitle().contains("TestRail"));
	}

	@Then("^the testrail request demo button is present$")
	public void the_testrail_request_demo_button_is_present() throws InterruptedException {
		By demoButtonSelector = By.linkText("Try TestRail");
		wait.until(ExpectedConditions.elementToBeClickable(demoButtonSelector));
		WebElement demoButton = driver.findElement(demoButtonSelector);
		Assert.assertTrue(demoButton.isDisplayed());
	}

	@After
	public void terminateWebDriver() {
		driver.quit();
	}
}
