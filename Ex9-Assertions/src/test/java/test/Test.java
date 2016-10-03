package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import page.home;

public class Test {

	//Declare WebDriver
	static WebDriver driver;
	
	//import and declare a home object to work with home
	home objHome;
	
	//create a main method to call the constructor
	public static void main(String[] args){
		
		//call the constructor
		new Test(driver);
		
	}
	
	//create a constructor to switch the drivers if necessary and call method to home
	public Test(WebDriver driver){
		
		String url = "https://the-internet.herokuapp.com/";
		
		//Launch the browser and set the driver
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		
		//set the dimensions of the window
		Point location = new Point(0, 0);
		Dimension size = new Dimension(1100, 640);
				
		driver.manage().window().setPosition(location);
		driver.manage().window().setSize(size);
		
		
		//switch the drivers from home to test
		this.driver = driver;
		
		//call methods to home
		clickLink();
		
		enterCreds();
		
		credsFail();
		
	}
	
	
	
	//method to call home method for clicking link
	public void clickLink(){
		
		//switch the drivers
		objHome = new home(driver);
		
		//call the home method
		objHome.clickLink();

	}
	
	
	public void enterCreds(){
		
		//switch the drivers
		objHome  =  new home(driver);
		
		//call the home method
		objHome.enterCreds();
		
	}
	
	
	//method to go back and enter wrong creds to assert fail
	public void credsFail(){
		
		//switch the driver
		objHome = new home(driver);
		
		//call the home method
		objHome.credsFail();
		
	}
	
	
}
