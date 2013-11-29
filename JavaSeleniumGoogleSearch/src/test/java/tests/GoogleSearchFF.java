package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import bsh.util.Util;
import pageobjects.GoogleHomePage;
import functions.*;

public class GoogleSearchFF {
	
	WebDriver driver;
	String searchTerm= "vosa";
		
	@BeforeMethod //@BeforeClass
	public void setup() {
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//driver.navigate().to(Constants.Url.GoogleHome);					
	}
		
	@Test
	public void testDoGoogleSearch() {         
		driver.navigate().to(Constants.Url.GoogleHome);	
		
		GoogleHomePage googlehomepage = new GoogleHomePage(driver);
		googlehomepage.doGoogleSearch(searchTerm);
		
		Utilities.wait(5);
		
		//End test case
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test (enabled=false)
	public void testDoAnotherGoogleSearch() {          
		driver.navigate().to(Constants.Url.GoogleHome);	

		Utilities.checkPageTitle(driver, "Google");
		
		driver.findElement(By.id("gbqfq")).clear();
		driver.findElement(By.id("gbqfq")).sendKeys("vosa");
		
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

