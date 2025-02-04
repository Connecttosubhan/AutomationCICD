package SeleniumFrameworkDesign.Automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFrameworkDesign.Automation.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationmessage;
	
	public String getConfirmationMessage()
	{
		String confirmmsg = confirmationmessage.getText();
		return confirmmsg;
	}

}
