package SeleniumFrameworkDesign.Automation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;



import SeleniumFrameworkDesign.Automation.TestComponents.BaseTest;
import SeleniumFrameworkDesign.Automation.TestComponents.Retry;
import SeleniumFrameworkDesign.Automation.pageobjects.CartPage;
import SeleniumFrameworkDesign.Automation.pageobjects.ProductCatalogue;


public class ErrorValidations extends BaseTest {
	@Test(groups = {"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String product = "ZARA COAT 3";
		landingPage.loginApplication("rahulvarma12@gmail.com", "Rahul@1235");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMSG());
	}
	
	@Test
	public void ProdErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String product = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingPage.loginApplication("rahulvarma@gmail.com", "Rahul@123");
		//  List<WebElement> items = productcatalogue.getproductList();
		productcatalogue.addProductToCart(product);
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}


}
