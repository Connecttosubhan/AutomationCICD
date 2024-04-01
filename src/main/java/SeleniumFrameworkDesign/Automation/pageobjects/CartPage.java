package SeleniumFrameworkDesign.Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.Automation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//page Factory
	
	@FindBy(css=".cartSection h3")
	List<WebElement> itemsincart;

	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean VerifyProductDisplay(String productname)
	{
		Boolean match = itemsincart.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkoutEle.click();
		CheckOutPage checkout = new CheckOutPage(driver);
		return checkout;
	}
	
	

	
	 

}
