package rajeshsalimath.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import rajeshsalimath.TestComponents.BaseTest;
import rajeshsalimath.TestComponents.RetryScript;
import rajeshsalimath.pageobjects.CartPage;
import rajeshsalimath.pageobjects.ProductCataloguePage;

public class ErrorValidationTestLogin extends BaseTest
{
	@Test(groups = "ErrorHandling")//, retryAnalyzer = RetryScript.class)
	// 'retryAnalyzer = RetryScript.class' this calls RetryScript automatically when test method fails
	//this attribute should be used for flaky(where we know tests fail) tests
	public void loginErrorTest() 
	{
		landingpage.loginApplication("johnwick@yahoo.com", "wicksdog");
		String errorMessages = landingpage.errorMessage();
		Assert.assertTrue(errorMessages.equalsIgnoreCase("Incorrect email password.")); //"Incorrect email or password."
	}
	
	@Test
	public void productErrorTest() throws InterruptedException 
	{
		String productName = "ADIDAS ORIGINAL";

		ProductCataloguePage productcatalogue = landingpage.loginApplication("madmaxx@hotmail.com", "Madmaxx@10");
		CartPage cartpage = productcatalogue.addProductToCart(productName);

		cartpage.navigateToCartPage();
		boolean cartItem = cartpage.verifyCartItem("Adibas");
		Assert.assertFalse(cartItem);
	}
}
