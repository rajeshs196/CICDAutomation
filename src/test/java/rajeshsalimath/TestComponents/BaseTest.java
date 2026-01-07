package rajeshsalimath.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rajeshsalimath.pageobjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializer() throws IOException 
	{
//		WebDriverManager.chromedriver().setup();
//		WebDriver driverp = new ChromeDriver();
//		driverp.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driverp.manage().window().maximize();
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\rajeshsalimath\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//this gets browser value from command prompt, i.e. when we pass browser value from cmd. mvn test -Dbrowser=firefox (D = maven parameters)
		//System.getProperty("browser"); 
		
		//String browserName = prop.getProperty("browser"); //this gets browser value from properties file

		//get browser value from cmd or properties file
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		
//		if (browserName.equalsIgnoreCase("chromeheadless")) 
//		{
//			ChromeOptions options = new ChromeOptions(); //setup for getting headless chrome browser option
//			WebDriverManager.chromedriver().setup();
//			options.addArguments("--headless=new"); //new flag for Chrome 109+
//			//options.addArguments("--window-size=1920,1080"); //Ensure full-size
//			driver = new ChromeDriver(options);
//			driver.manage().window().maximize();
//			//driver.manage().window().setSize(new Dimension(1440, 900)); //full screen if maximize() doesnt work
//		} 
//		else
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		else if (browserName.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	//this is made available in Base Test cos we can call this method without creating any object,
	//if there is seperate class for this getJsonDataToMap() method then we have to create object to call this method
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException 
	{
		//json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		//to make this method generic 'filepath' is made as an argument,
		//so that user can pass the filepath from where this method is called
		
		
		//String to List of Hashmap - using Jackson Binding (add dependency in pom.xml)
		ObjectMapper objectMap = new ObjectMapper();
		 List<HashMap<String, String>> data = objectMap.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		 
		return data;
	}
	
	//screenshot 
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}
	
	
	
	
	@BeforeMethod(alwaysRun = true) //this is useful while running tests in groups,
	//if we dont give 'alwaysRun = true' then when we run group tests these methods will not run, thinking these test methods doesnt belong to group
	//but if we mention these methods under groups then these will not run for other test methods
	//so we use 'alwaysRun = true' attribute
	public LandingPage lauchApplication() throws IOException 
	{
		driver = initializer();
		landingpage = new LandingPage(driver);
		landingpage.goToURL();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() 
	{
		driver.quit();
	}
}
