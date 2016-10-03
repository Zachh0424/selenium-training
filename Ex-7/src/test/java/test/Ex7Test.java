package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import page.Ex7Main;

public class Ex7Test {

	
	
	
	//Declare webdriver
	static WebDriver driver;
	WebDriverWait wait;
	
	Ex7Main main;
	
	String url = "http://www.w3schools.com/html/html_forms.asp";
	
	//create main method to call constructor
	public static void main(String[] args){
		
		//call constructor
		new Ex7Test(driver);
		
	}

	//create constructor
	public Ex7Test(WebDriver driver){
		
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
	//	driver = new FirefoxDriver();
		driver.get("http://www.w3schools.com/html/html_forms.asp");
		
		 Point location = new Point(0, 0);
		 Dimension size = new Dimension(1100,650);
		
		
		driver.manage().window().setPosition(location);
		driver.manage().window().setSize(size);
		
		//begin calling methods to main
		radio(driver);
		
	}
	
	
	public void radio(WebDriver driver){
		
		main = new Ex7Main(driver);
		
		main.radio();
		
	}
	
	
}
