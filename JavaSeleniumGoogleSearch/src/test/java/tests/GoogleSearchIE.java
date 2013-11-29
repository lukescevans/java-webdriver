package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.pagefactory.*;

import functions.*;

public class GoogleSearchIE {
	
	static WebDriver driver;
		
	@BeforeMethod //@BeforeClass
	public void setup() {
		//Setup IE Driver server parameters
		File file = new File(Constants.IEDriverServer.IE_DRIVER_SERVER);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		
		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();		

		//driver.navigate().to(Constants.Url.GoogleHome);					
	}
		
	@Test
	public void testAddNewProduct() {         
		driver.navigate().to(Constants.Url.GoogleHome);			
		Utilities.wait(5);
		//Return page title
		String t = driver.getTitle();
		//Assert page title
		Assert.assertEquals(t, Constants.PageTitles.HOME_PAGE);
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testDoGoogleSearch() {          
		driver.navigate().to(Constants.Url.GoogleHome);	
		Utilities.wait(5);
		//WebDriver driver = new FirefoxDriver();
		//driver.get("http://www.google.com/");
		String t = driver.getTitle();
		Assert.assertEquals(t, "Google");
		
		driver.findElement(By.id("gbqfq")).clear();
		driver.findElement(By.id("gbqfq")).sendKeys("vosa");
		
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

