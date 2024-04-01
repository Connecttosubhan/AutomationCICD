package SeleniumFrameworkDesign.Automation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.Automation.TestComponents.BaseTest;
import SeleniumFrameworkDesign.Automation.pageobjects.CartPage;
import SeleniumFrameworkDesign.Automation.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.Automation.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.Automation.pageobjects.OrderPage;
import SeleniumFrameworkDesign.Automation.pageobjects.ProductCatalogue;


public class SubmitTest extends BaseTest {
	
	String product = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitTest(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogue productcatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> items = productcatalogue.getproductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkout = cartpage.goToCheckOut();
		checkout.selectCountry("india");
		ConfirmationPage confirmationpage= checkout.submitOrder();
		String confirmmsg = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitTest"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue = landingPage.loginApplication("rahulvarma@gmail.com", "Rahul@123");
		OrderPage orderspage= productcatalogue.goToOrderPage();
		Boolean orders = orderspage.VerifyOrderDisplay(product);
		Assert.assertTrue(orders);
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFrameworkDesign\\Automation\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)} , {data.get(1)} };
		
	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "rahulvarma@gmail.com");
//	map.put("password", "Rahul@123");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");

}
