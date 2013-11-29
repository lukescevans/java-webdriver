package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.*;

import bsh.util.Util;
import pageobjects.GoogleHomePage;
import functions.*;

public class GoogleSearchCR {
	
	WebDriver driver;
	String searchTerm= "vosa";
		
	@BeforeMethod //@BeforeClass
	public void setup() {
		File file = new File(Constants.CRDriverServer.CR_DRIVER_SERVER);
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
		
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

