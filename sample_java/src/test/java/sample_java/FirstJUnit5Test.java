package sample_java;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class FirstJUnit5Test {

	WebDriver driver;
	JavascriptExecutor js;

	@BeforeEach
	public void setUp() throws Exception {

		System.out.println("#### Before");

		// to execute this test launch locally installed chromedriver.exe before test execution
		driver = new RemoteWebDriver(
				new URL("http://localhost:9515"), 
				new ChromeOptions());
		js = (JavascriptExecutor) driver;
	}

	@AfterEach
	public void tearDown() throws Exception {

		System.out.println("#### After");

		driver.quit();
	}

	@Test
	void test() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/hovers");

		highlightElement("figure", 5, "red");
		
		By avatarLocator = By.xpath("//div[@class='figure'][1]");
		By captionLocator = By.xpath("//div[@class='figcaption'][1]");

		WebElement avatar = driver.findElement(avatarLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(avatar).build().perform();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement caption = wait.until(visibilityOfElementLocated(captionLocator));

		highlightElement("figcaption", 2, "blue");
		
		assertTrue(caption.isDisplayed());
	}

	/**
	 * Select a WebElement from its class and highlight for requested time.
	 * 
	 * @param className
	 * @param duration
	 * @param color
	 * @throws InterruptedException
	 */
	private void highlightElement(String className, int duration, String color) throws InterruptedException {

		WebElement element = driver.findElement(By.className(className)); 
		String original_style = element.getAttribute("style");

		js.executeScript(
			"arguments[0].setAttribute(arguments[1], arguments[2])", 
			element, 
			"style",
			"border: 2px solid "+color+"; border-style: dashed;");

		if (duration > 0)
			Thread.sleep(duration * 1000);
		
		js.executeScript(
			"arguments[0].setAttribute(arguments[1], arguments[2])", 
			element, 
			"style", 
			original_style);
	}
}
