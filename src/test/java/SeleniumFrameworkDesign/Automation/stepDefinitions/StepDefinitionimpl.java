package SeleniumFrameworkDesign.Automation.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.Automation.TestComponents.BaseTest;
import SeleniumFrameworkDesign.Automation.pageobjects.CartPage;
import SeleniumFrameworkDesign.Automation.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.Automation.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.Automation.pageobjects.LandingPage;
import SeleniumFrameworkDesign.Automation.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionimpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Given_Logged_in_with_username_and_password(String username, String password)
	{
		productcatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String product) throws InterruptedException
	{
		List<WebElement> items = productcatalogue.getproductList();
		productcatalogue.addProductToCart(product);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String product)
	{
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(product);
		Assert.assertTrue(match);
		CheckOutPage checkout = cartpage.goToCheckOut();
		checkout.selectCountry("india");
		confirmationpage= checkout.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String confirmmsg = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));
		driver.close();
	}

}
