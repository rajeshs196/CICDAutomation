package rajeshsalimath.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rajeshsalimath.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent
{
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	//Navigating to checkout page
//	driverx.findElement(By.xpath("//button[text()='Checkout']")).click();
//	
//	//Selecting the Country
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
//	driverx.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Indi");
//	List<WebElement> listOfCountries = driverx.findElements(By.cssSelector("button.ta-item"));
//	
//	listOfCountries.stream().filter(country -> country.getText().equalsIgnoreCase("india")).findAny().orElse(null).click();

	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css="button.ta-item")
	List<WebElement> listOfCountries;
	
	public ConfirmationPage selectTheCountry(String countryName) 
	{
		selectCountry.sendKeys(countryName);
		listOfCountries.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findAny().orElse(null).click();
		ConfirmationPage confirmationp = new ConfirmationPage(driver);
		return confirmationp;
	}
	
	public void navigateToCheckOutPage() 
	{
		checkout.click();
	}
	
}


