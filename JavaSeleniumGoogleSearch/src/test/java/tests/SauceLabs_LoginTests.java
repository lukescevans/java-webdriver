package tests;

import java.lang.reflect.Method;
import java.net.URL;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import com.vosamot.webdriver.*;

public class SauceLabs_LoginTests {

    private WebDriver driver;

    @Parameters({"usernames", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
/*    public void setUp(@Optional("obradley") String usernames,
                      @Optional("c1c46004-ced0-4202-990b-0f45caf26501") String key,
                      @Optional("Windows 8") String os,
                      @Optional("Chrome") String browser,
                      @Optional("31") String browserVersion,
                      Method method) throws Exception {*/
    
    public void setUp(@Optional("lukescevans") String usernames,
            @Optional("b88895c5-ea5d-4e37-9ee0-3f2ac4605c0a") String key,
            @Optional("Windows 8") String os,
            @Optional("Chrome") String browser,
            @Optional("31") String browserVersion,
            Method method) throws Exception {

        // Choose the browser, version, and platform to test
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", os);
        capabilities.setCapability("name", method.getName());
        // Create the connection to Sauce Labs to run the tests
        this.driver = new RemoteWebDriver(
                new URL("http://" + usernames + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);

        
    }

	@Test
	public void testLoginAsUserWithSingleVTS() {
		
        // Create a new instance of the search page class
        // and initialise any WebElement fields in it.
      //  LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		driver.get(LoginPage.serverAddress+"/login"); 
		
		LoginPage loginPage = new LoginPage(driver);
		LandingPage landingPage = loginPage.loginAsUserWithSingleVTS("tester1","t");
		AssertJUnit.assertEquals("MOT Modernisation",driver.getTitle());  	
	}

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

}
