package SeleniumFrameworkDesign.Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.Automation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public boolean VerifyOrderDisplay(String productname)
	{
		Boolean match = productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		return match;
	}

}
