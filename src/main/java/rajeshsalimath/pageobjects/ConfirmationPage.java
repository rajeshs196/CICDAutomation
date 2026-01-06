package rajeshsalimath.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rajeshsalimath.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent
{
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
//	//Placing the order
//	driverx.findElement(By.cssSelector("a.action__submit")).click();
//	
//	//Validating the 'Thank you' line
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.hero-primary")));
//	String thankYouOrder = driverx.findElement(By.cssSelector("h1.hero-primary")).getText();
//	Assert.assertTrue(thankYouOrder.equalsIgnoreCase("Thankyou for the order."));
	
	
	@FindBy(css="a.action__submit")
	WebElement placeOrderButton;
	
	@FindBy(css="h1.hero-primary")
	WebElement thankyouMsg;
	
	By thankyouLocator = By.cssSelector("h1.hero-primary");
	
	public void navigateToConfirmationPage() 
	{
		placeOrderButton.click();
	}
	
	public String getConfirmationMessage() 
	{
		waitForElementToAppear(thankyouLocator);
		return thankyouMsg.getText();
	}
}






