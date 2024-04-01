package SeleniumFrameworkDesign.Automation;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFrameworkDesign.Automation.pageobjects.LandingPage;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String product = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("rahulvarma@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = items.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(product))
		.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
	
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		//Animated wait
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(".ng-animating")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> itemsincart = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = itemsincart.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "india").build().perform();
		//driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".btnn"))));
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();
		String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER. "));
		driver.close();
	}

}
