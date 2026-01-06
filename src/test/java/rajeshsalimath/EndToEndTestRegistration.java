package rajeshsalimath;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndTestRegistration extends EndToEndTestUserData
{

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
//		ArrayList<String> values = new ArrayList<String>();
//		values.add("Madd");
//		values.add("Maxx");
//		values.add("madmaxx@hotmail.com");
//		values.add("7895645679");
//		values.add("Madmaxx@10");
		
		driver.findElement(By.cssSelector("p.login-wrapper-footer-text")).click();
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(values.get(0));
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(values.get(1));
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(values.get(2));
		driver.findElement(By.cssSelector("input[id='userMobile']")).sendKeys(values.get(3));
		
		WebElement occupa = driver.findElement(By.cssSelector("select[formcontrolname='occupation']"));
		Select occupation = new Select(occupa);
		occupation.selectByVisibleText("Engineer");
		
		driver.findElement(By.cssSelector("input[value='Male']")).click();
		
		driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys(values.get(4));
		driver.findElement(By.cssSelector("input[id='confirmPassword']")).sendKeys(values.get(4));
		
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		
		driver.findElement(By.cssSelector("input[value='Register']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.login-wrapper h1")));
		String successActual = driver.findElement(By.cssSelector("div.login-wrapper h1")).getText();
		String successExpected = "Account Created Successfully";
		
		Assert.assertTrue(successActual.equalsIgnoreCase(successExpected));
	}
}





