package sample_java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstJUnit5Test {

	WebDriver driver;
	JavascriptExecutor js;

	@BeforeEach
	public void setUp() throws Exception {

		System.out.println("#### Before");

		System.setProperty("webdriver.chrome.driver", "<FILL_WITH_CORRECT_PATH>\\chromedriver.exe");

		driver = new ChromeDriver();
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

		WebElement avatar = driver.findElement(By.className("figure"));
		highlightElement(avatar, 5, "red");
		
		Actions builder = new Actions(driver);
		builder.moveToElement(avatar).build().perform();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("figcaption")));

		WebElement caption = driver.findElement(By.className("figcaption")); 
		highlightElement(avatar, 2, "blue");
		
		assertTrue(caption.isDisplayed());
	}

	/**
	 * This routine highlight WebElement for requested time.
	 * 
	 * @param element
	 * @param duration
	 * @param color
	 * @throws InterruptedException
	 */
	private void highlightElement(WebElement element, int duration, String color) throws InterruptedException {
		String original_style = element.getAttribute("style");

		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid "+color+"; border-style: dashed;");

		if (duration > 0)
			Thread.sleep(duration * 1000);
		
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", original_style);
	}

}
