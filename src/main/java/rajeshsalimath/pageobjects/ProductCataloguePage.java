package rajeshsalimath.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rajeshsalimath.AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// PageFactory.initElements(driver, this); initializes all the @FindBy annotated
		// WebElements in the
		// current class (this) using the given WebDriver (driver)
	}

	//selecting item and adding it to cart
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4.col-md-6")));
//	List<WebElement> allProducts = driverx.findElements(By.cssSelector("div.col-lg-4.col-md-6"));
	
//	WebElement actualProduct = allProducts.stream()
//					.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(itemData.get(0)))
//					.findFirst().orElse(null);
	
//	actualProduct.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
	
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='toast-container']")));
//	wait.until(ExpectedConditions.invisibilityOf(driverx.findElement(By.cssSelector(".ng-animating"))));
	
	@FindBy(css="div.col-lg-4.col-md-6")
	List<WebElement> prodcuts;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector("div.col-lg-4.col-md-6");
	By addToCart = By.cssSelector("button[class='btn w-10 rounded']");
	By toaster = By.cssSelector("div[id='toast-container']");
	By productNameBy = By.cssSelector("b");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return prodcuts;
	}
	
	public WebElement getProductByName(String productName) 
	{
		WebElement prod = getProductList().stream().filter(product -> product.findElement(productNameBy).getText().equalsIgnoreCase(productName))
								.findFirst().orElse(null);
		return prod;
	}
	
	public CartPage addProductToCart(String productName) 
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toaster);
		waitForWebElementToAppear(spinner);
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
}







