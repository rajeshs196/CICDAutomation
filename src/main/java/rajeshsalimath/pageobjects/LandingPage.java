package rajeshsalimath.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rajeshsalimath.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input#userEmail")
	WebElement usermail;
	
	@FindBy(css="input#userPassword")
	WebElement userpassword;
	
	@FindBy(css="input[value='Login']")
	WebElement userlogin;
	
	//div[aria-label='Incorrect email or password.']
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;
	
	
	By userLocator = By.cssSelector("input#userEmail");
	
	
	
	
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#userEmail")));2));
//	driverx.findElement(By.cssSelector("input#userEmail")).sendKeys(values.get(2));
//	driverx.findElement(By.cssSelector("input#userPassword")).sendKeys(values.get(4));
//	driverx.findElement(By.cssSelector("input[value='Login']")).click();
	
	public ProductCataloguePage loginApplication(String email, String password) 
	{
		waitForElementToAppear(userLocator);
		usermail.sendKeys(email);
		userpassword.sendKeys(password);
		userlogin.click();
		ProductCataloguePage productcata = new ProductCataloguePage(driver);
		return productcata;
	}
	
	public String errorMessage() 
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	public void goToURL() 
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
}





