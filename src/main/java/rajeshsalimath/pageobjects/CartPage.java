package rajeshsalimath.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rajeshsalimath.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	driverx.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
//
//	//Validating items in cart with added cart from home page
//	List<WebElement> cartItems = driverx.findElements(By.cssSelector("div.cartSection h3"));
//	boolean match = cartItems.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(itemData.get(0)));
//	Assert.assertTrue(match);
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement goCartPage;
	
	@FindBy(css="div.cartSection h3")
	List<WebElement> cartItems;
	
	By carts = By.cssSelector("div.cartSection h3");
	
	public CheckoutPage navigateToCartPage() throws InterruptedException 
	{
		Thread.sleep(2000);
		goCartPage.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
	
	public boolean verifyCartItem(String productName) 
	{
		waitForElementToAppear(carts);
		boolean matchItem = cartItems.stream().anyMatch(carts -> carts.getText().equalsIgnoreCase(productName));
		return matchItem;
	}
	
	
	

	
	
	
}
