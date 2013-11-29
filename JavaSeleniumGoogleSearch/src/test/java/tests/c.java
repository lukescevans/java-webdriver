package functions;

//import pageobjects.BasePage;

public class Constants {
	
    //If true, kill the browser at the end of each test
    public static boolean bKillBrowser = true;

	public static class Database {
		public final static String dbConnectionString= "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=polo-scan.mfltest.co.uk)(PORT=1560))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=polomdp.mfltest.co.uk)))";
		//"jdbc:oracle:thin:@//<host>:<port>/<service_name> ";
		public final static String dbUsername= "luke";
		public final static String dbPassword= "password1";
	}
	
    public static class BrowserToLaunch {
        //public static String BrowserChoice = "IE";
        public final static String BrowserChoice = "FF";
    }
    
    public static class Url {
        //public static String BrowserChoice = "FF";
        public final static String PID = "http://pid-epod/";
    }
    
    public static class PageTitles {
    	public final static String INDEX= "Index";
    	public final static String UNITS_OF_SALE= "Units of Sale";
    }
    
    public static class IEDriverServer {
    	public final static String IE_DRIVER_SERVER= "C:\\Documents and Settings\\evansl\\My Documents\\Selenium_Files\\IEDriverServer_Win32_2.29.1\\IEDriverServer.exe";
    }
}

package functions;

import java.sql.*;
import java.util.Properties;

public class Database {
    
    //Connection conn;
    
/*    public Database() throws SQLException {
        System.out.println(Constants.Database.dbConnectionString);
        Connection conn = DriverManager.getConnection(Constants.Database.dbConnectionString, Constants.Database.dbUsername,Constants.Database.dbPassword);    
    }*/
    
    public static String runSql(String strSQL) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        //intitialise the variable
        String intRetVal = null;
        
        Connection conn = DriverManager.getConnection(Constants.Database.dbConnectionString, Constants.Database.dbUsername,Constants.Database.dbPassword);
        
        Statement stmt = conn.createStatement();
        
        ResultSet rset =
             stmt.executeQuery(strSQL.toString());
        while (rset.next()) {
            intRetVal= rset.getString("DEALER_CODE");
            
             System.out.println (rset.getString("DEALER_CODE"));
        }
        
        System.out.println("********" + intRetVal + "****");
        stmt.close();
        conn.close();
        System.out.println ("Ok.");
        
        return intRetVal.toString();
    }
    public static int luke() {
        return 0;
    }
}





ECLIPSE SETUP

 
 
 
SAMPLE TEST
package tests;

//import static org.junit.Assert.*;
/*import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;*/

import java.util.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.Select;

import functions.Constants.PageTitles;
import functions.Constants;
import functions.Utilities;

import pageobjects.*;

public class test_AddNewProduct {
	
	private boolean killBrowser= false;
	private WebDriver driver;
	private String today;
	private String chartID = "960";
	private String edDate = "01/11/2013";
	
		
	@BeforeClass
	public void setup() {
		//File file = new File(Constants.IEDriverServer.IE_DRIVER_SERVER);
		//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.navigate().to("http://pid-test/");					
	}
	
	@Test
	public void test_AddNewProduct() {            
		
		boolean firstRun = false;
		
		//MainMenu mainmenu = new MainMenu(driver);
		MainMenu mainmenu = PageFactory.initElements(driver, MainMenu.class);
		
		mainmenu.clickMenu("MANAGE ANNOUNCEMENTS");
		mainmenu.clickMenu("UNITS OF SALE");
		mainmenu.clickMenu("PRODUCTS");
		mainmenu.clickMenu("MANAGE ANNOUNCEMENTS");


		driver.findElement(By.id("EditionIdentifier")).clear();
		driver.findElement(By.id("EditionIdentifier")).sendKeys(chartID);
		Utilities.wait(5);

		ManageAnnouncements manageannouncements = PageFactory.initElements(driver, ManageAnnouncements.class);
		
		manageannouncements.printChartSuggestions();
		
		String m= driver.findElement(By.xpath("/html/body/div[2]/div/div/div")).getAttribute("title");
		System.out.println("Selected item: " + m);
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div")).click();

		driver.findElement(By.id("PublicationDate")).clear();
		driver.findElement(By.id("PublicationDate")).sendKeys(Keys.TAB);
		Utilities.wait(2);
		driver.findElement(By.id("PublicationDate")).clear();
		driver.findElement(By.id("PublicationDate")).sendKeys(edDate);
		Utilities.wait(1);
		driver.findElement(By.id("PublicationDate")).sendKeys(Keys.ESCAPE);
		
		boolean l = isElementPresent(By.id("jqDialog_yes"));
		
		
			
		driver.findElement(By.id("jqDialog_yes")).click();
		
		try
		{
			Utilities.wait(5);
		    Assert.assertEquals("New Edition Announcement Added: " + chartID.toString(), driver.findElement(By.id("resultMessage")).getText());
		}
		catch (Exception e)
		{
		    
		}		
/*		
		//Assert.assertEquals(true, false);
		
		//Utilities.checkPageTitle(driver, "GOOGLE");
		
		//Get tomorrows date
		String edDate= Utilities.setDaysFromToday(1);

		//Create array to store new product
		ArrayList<String> a = new ArrayList<>();
			
		//Enter chart ID's to announce
		a.add("2");	
		
		for (int j = 0; j < a.size(); j++) {
			
			Utilities.wait(1);
							
			
	        
			//PageFactory.initElements(driver, mainmenu);
			
			Utilities.wait(1);
									    
			try {
				mainmenu.clickMenu("MANAGE ANNOUNCEMENTS");
			} catch (Exception e) {		
				PageFactory.initElements(driver, mainmenu);
				mainmenu.clickMenu("MANAGE ANNOUNCEMENTS");
			}
		        
		    Utilities.wait(1);
		    
		    String e= Utilities.getPageTitle(driver);
		    
		    System.out.println("PAGE TITLE " + "**************** " + e + " ****************");
		    
		    //Wait 10 secs for link to be displayed
		    WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add New Announcement")));
		    
		    driver.findElement(By.linkText("Add New Announcement")).click();	    
		    
		    Utilities.wait(1);
		    		  
		    System.out.println(Utilities.getPageTitle(driver));

		    //Add new product link
		    //Wait 10 secs for link to be displayed
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("NewProductLink")));
		    
		    driver.findElement(By.id("NewProductLink")).click();
		    
		    //Initialise manage announcements page object
		    ManageAnnouncements manageannouncements = PageFactory.initElements(driver, ManageAnnouncements.class);
	    	
		    //Create a new product in the PID database		   
		    manageannouncements.addNewProduct("Chart", "123", "123", "test title", Utilities.setDaysFromToday(1), "");
		    
		    try {
		    	driver.switchTo().alert();
		        driver.switchTo().alert().accept();
			} catch (NoAlertPresentException e2) {
				//assertTrue("INFO: OK/ Cancel Dialogue not displayed " + e2.toString(), true);
			}
	        
	        Utilities.wait(2);
	        
	        //Check if there is an existing announcement

	        boolean yep= driver.findElement(By.cssSelector("input.submitButton")).isDisplayed();
	        
	        if (yep) {
	        	driver.findElement(By.id("SelectedEdition_EditionNumber")).clear();
	        	driver.findElement(By.id("SelectedEdition_EditionNumber")).sendKeys("1");
	        	driver.findElement(By.id("SelectedEdition_EditionDate")).clear();
	        	driver.findElement(By.id("SelectedEdition_EditionDate")).sendKeys(edDate);

	        	driver.findElement(By.cssSelector("input.submitButton")).click();
			}*/
	        
		    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("css=input.submitButton")));
		}

	/**
	 * @param foo
	 */
	private boolean isElementPresent(By by) {
		boolean retVal;
	
		try {
			driver.findElement(by);
			retVal= true;
		} catch (Exception e) {
			// TODO: handle exception
			retVal= false;
		}
		return retVal;				
	}


//        //Select chart from list
//        new Select(driver.findElement(By.id("Products"))).selectByVisibleText("3005");
//              
//        Utilities.wait(1);
//        
//        driver.findElement(By.id("PublicationDate")).clear();
//        //driver.findElement(By.id("PublicationDate")).sendKeys(today);
//        driver.findElement(By.id("PublicationDate")).sendKeys("14/03/2013");
//        Utilities.wait(1);    
//        
//        driver.findElement(By.id("CreateAnnouncement")).click();
//        Utilities.wait(1);
//
//        driver.switchTo().alert();
//        driver.switchTo().alert().accept();     
		//}
//}
	
	@AfterClass
	public void tearDown()
	{
		System.out.print("!!!COMPLETED!!!");
		driver.quit();
	}
}

PAGE OBJECT
package tests;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.Exception;

import org.apache.bcel.verifier.structurals.ExceptionHandlers;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageAnnouncements extends BasePage {
	
    public ManageAnnouncements(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
        
	//***PAGE ELEMENTS//
    @FindBy(how = How.ID, using = "EditionIdentifier")
    private WebElement EditionIdentifier;
    
    @FindBy(how = How.ID, using = "EditionNumber")
    private WebElement EditionNumber;
    
    @FindBy(how = How.ID, using = "Title")
    private WebElement Title;
   
    @FindBy(how = How.ID, using = "PublicationDate")
    private WebElement PublicationDate;
    
    @FindBy(how = How.ID, using = "WithdrawalDate")
    private WebElement WithdrawalDate;
    
    @FindBy(how = How.ID, using = "SelectedProductTypeId")
    private WebElement Producttype;
    
    @FindBy(how = How.ID, using = "CreateAnnouncement")
    private WebElement CreateAnnouncement;
    
    public void addNewProduct(String productType, String identifier, String editionNumber, String title, String edDate, String withdrawalDate) {
    	new Select(Producttype).selectByVisibleText(productType); //Chart or ARCS
    	EditionIdentifier.clear();
    	EditionIdentifier.sendKeys(identifier);
    	EditionNumber.clear();
    	EditionNumber.sendKeys(editionNumber);
    	Title.clear();
    	Title.sendKeys(title);
    	PublicationDate.clear();
    	PublicationDate.sendKeys(edDate.toString());
    	WithdrawalDate.clear();
    	WithdrawalDate.sendKeys(withdrawalDate.toString());
    	
    	//Click Create announcement button to add the record to the database
    	CreateAnnouncement.click();
    }
    
    public void editAnnouncement(String edNumber, String edDate) {
    	boolean yep= _driver.findElement(By.cssSelector("input.submitButton")).isDisplayed();
        
        if (yep) {
        	_driver.findElement(By.id("SelectedEdition_EditionNumber")).clear();
        	_driver.findElement(By.id("SelectedEdition_EditionNumber")).sendKeys(edNumber);
        	_driver.findElement(By.id("SelectedEdition_EditionDate")).clear();
        	_driver.findElement(By.id("SelectedEdition_EditionDate")).sendKeys(edDate);

        	_driver.findElement(By.cssSelector("input.submitButton")).click();
		}
    }
    
    public void printChartSuggestions() {
		List<WebElement> allSuggestions = _driver.findElements(By.xpath("/html/body/div[2]/div"));//*[@id="Autocomplete_59ac8"]
		
		System.out.println("Printing chart suggestions from search list");
        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());          
        }
	}
}



