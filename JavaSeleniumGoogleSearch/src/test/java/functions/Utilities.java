package functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

//import pageobjects.BasePage;

/**
 * @author Luke.Evans
 * Utilities helper class
 */


public class Utilities {
	
	
	public static String getRandomString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	//Set the webdriver timeout
    public static void setWebDriverTimeout(WebDriver driver, int timeToWait) {   	
    	driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
    }
    
    //Make Selenium wait for x seconds
    public static void wait(int secondsToWait)
    {
		long t0, t1;
		int waitFor= secondsToWait * 1000;
		 
        t0 =  System.currentTimeMillis();
      	 
        do {
        	//Get the current time in milliseconds
            t1 = System.currentTimeMillis();
        }
        while (t1 - t0 < waitFor);
      	
        //Output to log
        System.out.println("Waited for: " + secondsToWait + " seconds");
        
    }
    
    /**
     * @author Luke.Evans
     * Check the page title is correct
     */
    public static void checkPageTitle(WebDriver driver, String expectedTitle) {
    	//Throw error if page title is incorrect
    	Assert.assertEquals(driver.getTitle().toUpperCase(), expectedTitle.toUpperCase());    	
    }
    
    //Luke Evans 15/02/13
    //Get the page title
    public static String getPageTitle(WebDriver driver) {
    	String retPageTitle= driver.getTitle().toUpperCase();
    	System.out.println(retPageTitle);
		return retPageTitle;    	
    }
    
    //Luke Evans
    //Add a number of days to today
    //Throws: IllegalArgumentException
    public static String setDaysFromToday(int noOfDays) throws IllegalArgumentException
    {
    	Date retDate = null;
    	
    	GregorianCalendar gc = new GregorianCalendar();
		
		//Get current date and store in NewDate
		//Date newDate = gc.getTime();
		
		//Create date formatter (dd/mm/yyyy)
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		//Add a day
		try {
			gc.add(Calendar.DAY_OF_MONTH, noOfDays);
			retDate = gc.getTime();
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}		
		//Return the date from the function call
		return sdf.format(retDate);
    }
    
    //Luke Evans
    //Returns true if element is present
    //boolean l = isElementPresent(By.id("jqDialog_yes"));
    public boolean isElementPresent(WebDriver driver, By by) {
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
	
}