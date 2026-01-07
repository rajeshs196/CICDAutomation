package rajeshsalimath.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rajeshsalimath.TestComponents.BaseTest;
import rajeshsalimath.pageobjects.CartPage;
import rajeshsalimath.pageobjects.CheckoutPage;
import rajeshsalimath.pageobjects.ConfirmationPage;
import rajeshsalimath.pageobjects.OrderPage;
//import rajeshsalimath.pageobjects.LandingPage;
import rajeshsalimath.pageobjects.ProductCataloguePage;

public class ShopSubmitOrderTest extends BaseTest
{
	String productName = "ADIDAS ORIGINAL";
	//testing for jenking jobs
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void SubmitOrder(HashMap<String, String> mapy) throws InterruptedException, IOException 
	{
		
//		LandingPage landingpage = lauchApplication();
//		landingpage.goToURL();
		
		ProductCataloguePage productcatalogue = landingpage.loginApplication(mapy.get("email"), mapy.get("password"));
		//can access hashmap/map values by accessing the key 'mapy.get("email")' here 'email' is the key
		//which returns 'value' = 'madmaxx@hotmail.com'
		
		//ProductCataloguePage productcatalogue = new ProductCataloguePage(driverp);
		CartPage cartpage = productcatalogue.addProductToCart(mapy.get("product"));
		
		//CartPage cartpage = new CartPage(driverp);
		CheckoutPage checkout = cartpage.navigateToCartPage();
		boolean cartItem = cartpage.verifyCartItem(mapy.get("product"));
		Assert.assertTrue(cartItem);
		
		//CheckoutPage checkout = new CheckoutPage(driverp);
		checkout.navigateToCheckOutPage();
		ConfirmationPage confirmationp = checkout.selectTheCountry("india");
		
		//ConfirmationPage confirmationp = new ConfirmationPage(driverp);
		confirmationp.navigateToConfirmationPage();
		String confirmationText = confirmationp.getConfirmationMessage();
		Assert.assertTrue(confirmationText.equalsIgnoreCase("Thankyou for the order."));
		
		//driverp.quit();
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest() 
	{
		ProductCataloguePage productcatalogue = landingpage.loginApplication("madmaxx@hotmail.com", "Madmaxx@10");
		OrderPage orderpage = productcatalogue.GoToOrderPage();
		boolean checkMatch = orderpage.verifyOrderDisplay(productName);
		Assert.assertTrue(checkMatch);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "madmaxx@hotmail.com"); //key - value pairs
//		map.put("password", "Madmaxx@10");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "madmaxx@hotmail.com");
//		map1.put("password", "Madmaxx@10");
//		map1.put("product", "iphone 13 pro");
		
		List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rajeshsalimath\\data\\PurchaseOrderData.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
	/*
	 * @DataProvider public Object[][] getData() { Object[][] object = new
	 * Object[2][3]; 
	 * //Object is used cos, its the parent of all data types 
	 * //and its generic, which can hold all type of datatypes
	 * 
	 * object[0][0] = "madmaxx@hotmail.com"; object[0][1] = "Madmaxx@10";
	 * object[0][2] = "ADIDAS ORIGINAL";
	 * 
	 * object[1][0] = "madmaxx@hotmail.com"; object[1][1] = "Madmaxx@10";
	 * object[1][2] = "iphone 13 pro";
	 * 
	 * return object; }
	 */
}






