package page;


import org.openqa.selenium.*;
import org.testng.Assert;

import test.Test;

public class home {

	
	//Declare the WebDriver
	WebDriver driver;
	
	//create the Test object to work with Test page
	Test objTest;
	

	//create constructor to switch the drivers
	public home(WebDriver driver){
		
		this.driver = driver;
		
	}
	
	
	//method to click the link 
	public void clickLink(){
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[18]/a")).click();
		
	}
	
	
	
	//method to enter data and assert success
	public void enterCreds(){
		
		WebElement un = driver.findElement(By.id("username"));
		WebElement pw = driver.findElement(By.id("password")); 
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
		
		un.sendKeys("tomsmith");
		pw.sendKeys("SuperSecretPassword!");
		btn.click();
		
		WebElement actual = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
		
		//assert that a success message displays
		Assert.assertEquals(true, actual.isDisplayed());
		
		
	}
	
	
	
	//method to back/enter wrong creds, and assert fail
	public void credsFail(){
		
		//go back
		driver.navigate().back();
		
		//enter the wrong password
		WebElement pw = driver.findElement(By.id("password"));
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
		
		pw.sendKeys("kjhsagfdlkjfs");
		btn.click();
		
		WebElement actual = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
		
		//assert that a success message displays
		Assert.assertEquals(true, actual.isDisplayed());
		
		
	}
	
	
}
