package rajeshsalimath;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndTest01 extends EndToEndTestUserData
{

	public static void main(String[] args) 
	{
		//Chrome driver and chrome browser steup
		WebDriverManager.chromedriver().setup(); // automatically downloads chrome driver version according to chrome
													// webbrowser version
		WebDriver driverx = new ChromeDriver();
		driverx.manage().window().maximize();

		//Explicit, implict and website launch
		driverx.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driverx, Duration.ofSeconds(5));
		driverx.get("https://rahulshettyacademy.com/client/#/auth/login");

		
		//Logging In into the website
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#userEmail")));
		// System.out.println(values.get(2));
		driverx.findElement(By.cssSelector("input#userEmail")).sendKeys(values.get(2));
		driverx.findElement(By.cssSelector("input#userPassword")).sendKeys(values.get(4));
		driverx.findElement(By.cssSelector("input[value='Login']")).click();

		//selecting item and adding it to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4.col-md-6")));
		List<WebElement> allProducts = driverx.findElements(By.cssSelector("div.col-lg-4.col-md-6"));
		WebElement actualProduct = allProducts.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(itemData.get(0)))
				.findFirst().orElse(null);
		actualProduct.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();

		//waiting for item to be added to cart and navigating to Cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='toast-container']")));
		wait.until(ExpectedConditions.invisibilityOf(driverx.findElement(By.cssSelector(".ng-animating"))));
		driverx.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		//Validating items in cart with added cart from home page
		List<WebElement> cartItems = driverx.findElements(By.cssSelector("div.cartSection h3"));
		boolean match = cartItems.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(itemData.get(0)));
		Assert.assertTrue(match);

		//Navigating to checkout page
		driverx.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		//Selecting the Country
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		driverx.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Indi");
		List<WebElement> listOfCountries = driverx.findElements(By.cssSelector("button.ta-item"));
		
		listOfCountries.stream().filter(country -> country.getText().equalsIgnoreCase("india")).findAny().orElse(null).click();
		
		//Placing the order
		driverx.findElement(By.cssSelector("a.action__submit")).click();
		
		//Validating the 'Thank you' line
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.hero-primary")));
		String thankYouOrder = driverx.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(thankYouOrder.equalsIgnoreCase("Thankyou for the order."));
		
		driverx.quit();
		System.out.println("End of script");
	}

}
