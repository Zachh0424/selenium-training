package Exercise6;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import Exercise6.SelectElements;



public class SelectElementsTest {

	 
	//Declare WebDriver
	static WebDriver driver;
	String url = "https://the-internet.herokuapp.com/";
	
	SelectElements objMain;
	
	 
	public static void main(String[] args) throws InterruptedException{
		
		//call Constructor
		new SelectElementsTest(driver);
	}
	
	
	//constructor 
	public SelectElementsTest(WebDriver driver) throws InterruptedException{
	
		this.driver = driver;
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		
		
		//now call methods to go to main
		clickLink(driver);
		
		selectByIndex(driver);
		
		
	}

	
	
	
	public void clickLink(WebDriver driver){
		
		//connect/switch drivers
		objMain = new SelectElements(driver);
		
		//call main page method
		objMain.clickLink();
		
	}
	
	
	
	public void selectByIndex(WebDriver driver) throws InterruptedException{
		
		objMain = new SelectElements(driver);
		
		objMain.selectByIndex();
		
	} 
	
	
	
	
}