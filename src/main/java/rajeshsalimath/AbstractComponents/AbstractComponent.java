package rajeshsalimath.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rajeshsalimath.pageobjects.OrderPage;

public class AbstractComponent 
{
	WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	public OrderPage GoToOrderPage() 
	{
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
	public void waitForElementToAppear(By findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findEle) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(findEle));
	}
	
	public void waitForElementToDisappear(By findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	
}


//PageFactory.initElements(driver, this);
//This line comes from Selenium WebDriver when using the Page Object Model (POM). 
//The PageFactory class helps initialize web elements defined in a page class using annotations like @FindBy.
//
//🧩 Explanation of PageFactory.initElements(driver, this);
//- PageFactory
//A helper class in Selenium that supports the Page Object pattern. It automates the initialization of WebElement fields in your class.
//- initElements(WebDriver driver, Object page)
//This method takes:
//- driver: The active WebDriver instance (e.g., ChromeDriver, FirefoxDriver).
//- page: The current page object (usually this refers to the current class instance).
//- What it does
//It scans the class (this) for fields annotated with @FindBy (or other locator annotations) 
//and automatically initializes them as WebElement objects using the provided driver.
//
//Why it’s useful
//- Saves you from writing driver.findElement(...) repeatedly.
//- Makes code cleaner and more readable.
//- Encourages the Page Object Model, which improves maintainability and reusability of test scripts.
//
//👉 In short:
//PageFactory.initElements(driver, this); initializes all the @FindBy annotated WebElements in the current class (this) using the given WebDriver (driver).
