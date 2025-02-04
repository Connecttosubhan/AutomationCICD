package SeleniumFrameworkDesign.Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.Automation.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//page Factory
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//button[contains(@class, 'ta-item')][2]")
	WebElement selectcountry;

	
	@FindBy(css=".form-group input")
	WebElement country;
	
	
	public void selectCountry(String countryname)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryname).build().perform();
		selectcountry.click();
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".btnn"))));
		a.moveToElement(submit).build().perform();
	}
	
	public ConfirmationPage submitOrder()
	{
		
		submit.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}

	
	 

}
