package Exercise6;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectElements {

	//Declare WebDriver
	WebDriver driver;
	//Declare WedDriver Wait
	
	
	//Define a proxy for the Dropdown link on the homepage
//	@FindBy(linkText = "Dropdown")
//	private WebElement lnkDropdown;
	
	
	//By Vars
	By dropDownLink = By.xpath("//*[@id=\"content\"]/ul/li[9]/a");
							   
	
	//Define the constructor
	public SelectElements(WebDriver driver){
	
		this.driver = driver;
	//	PageFactory.initElements(driver, this);

		
	}
	
	
	
	
	//create method to click the link
	public void clickLink(){
		
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropDownLink));

			 driver.findElement(By.xpath("//*[@id='content']/ul/li[9]/a")).click();

	}
	
	
	//method to select an option by index
	public void selectByIndex() throws InterruptedException{
		
	
		//try to loop to option
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement drop = driver.findElement(By.id("dropdown"));
		wait.until(ExpectedConditions.visibilityOf(drop));
	//	driver.findElement(By.tagName("select")).click();
		
		
//		Select sel = new Select(driver.findElement(By.id("dropdown")));
//		//sel.selectByIndex(2);
//		//sel.selectByValue("Option 1");
//	     sel.selectByVisibleText("Option 1"); 
//	       
		
		
		/*
		 * Use Selenium's PageFactory to reinitialize the proxy elements to
		 * avoid a StaleElementReferenceException or an ElementNotFoundException
		 */
		PageFactory.initElements(driver, this);
		//Promote the WebElement to a Select element
		Select select = new Select(driver.findElement(By.id("dropdown")));
		//Select an option by its value
		select.selectByValue("1");
		//Sleep to allow visibility
		Thread.sleep(1000);
		//Select an option by its index in the list
		select.selectByIndex(2);
		//Sleep to allow visibility
		Thread.sleep(1000);
		//Select an option by its visible text
		select.selectByVisibleText("Option 1");
		//Sleep to allow visibility
		Thread.sleep(1000);

		
		
	}
	
	

}
