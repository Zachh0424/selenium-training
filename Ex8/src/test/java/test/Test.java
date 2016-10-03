package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import page.home;

public class Test {

	
	//declare the WebDriver
	static WebDriver driver;
	home objHome; 
	
	String url = "https://the-internet.herokuapp.com/";
	
	Point location = new Point(0, 0);
	Dimension size = new Dimension(1100, 650);
	
	public static void main(String[] args){
		
		new Test(driver);
		
	}
	
	public Test(WebDriver driver){
		
		//pull up the site and prepare the driver
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		
		//set the window where and how i want
		driver.manage().window().setSize(size);
		driver.manage().window().setPosition(location);
		
		
		//for switching drivers
		PageFactory.initElements(driver, this);
		this.driver = driver;
		
		
		//call the methods in proper order
		tablesLink();
		
		 firstColumn();
		 
		 firstRow();
		 
		 rowSearch();
		 
		 
	}
	
	
	
	//create method to call page for opening the link
	public void tablesLink(){
		
		//switch the drivers
		objHome = new home(driver);
		
		//call home's method
		objHome.tablesLink();
		
	}
	
	
	//create a method to call main for printing first column in table
	public void firstColumn(){
		
		//switch the drivers
		objHome = new home(driver);
		
		//call home's method
		objHome.firstColumn();
		
	}
	
	
	//create method to call main to print first row in table
	public void firstRow(){
		
		//switch the drivers
		objHome = new home(driver);
		
		//call the home method
		objHome.firstRow();
		
	}
	
	
	//create a method to call main to search each row to fins specific text
	public void rowSearch(){
		
		//switch the drivers
		objHome = new home(driver);
		
		//call the home method
		objHome.rowSearch();
		
	}
	
	
}
