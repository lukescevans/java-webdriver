package pageobjects;

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

import functions.Constants;
import functions.Utilities;

public class GoogleHomePage {
	
	WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
    	this.driver = driver;
    	    	
    	//Check on the correct page
    	Utilities.checkPageTitle(driver, Constants.PageTitles.HOME_PAGE);
    	
    	//Initialise page objects
    	PageFactory.initElements(driver, this);
	}
        
	//***PAGE ELEMENTS//

    @FindBy(how = How.ID, using = "gbqfq") //Google search box
    private WebElement SearchBox;
    
    //***PAGE METHODS//
    
    /**
     * @param searchTerm
     * @return instance of GoogleHomePage
     */
    public GoogleHomePage doGoogleSearch(String searchTerm) {
    	SearchBox.clear();
    	SearchBox.sendKeys(searchTerm);
    	return new GoogleHomePage(this.driver);
    }
    
    /**
     * @param None
     * @return
     */
    public void printChartSuggestions() {
		List<WebElement> allSuggestions = driver.findElements(By.xpath("/html/body/div[2]/div"));//*[@id="Autocomplete_59ac8"]
		
		System.out.println("Printing chart suggestions from search list");
        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());          
        }
	}
}



