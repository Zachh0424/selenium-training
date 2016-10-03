package page;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import test.Ex7Test;

public class Ex7Main {

	//Declare the WebDriver
	 WebDriver driver;
	
	Ex7Test test;
	
	
	
//	public static void main(String[] args){
//		
//		//constructor
//		new Ex7Main(driver);
//		
//	}
//	
	
	
	public Ex7Main(WebDriver driver){
		
		this.driver = driver;
		
	}
	
	
	
	
	public void radio(){
		

//		//JavascriptExecutor jse = (JavascriptExecutor)driver;
//		//jse.executeScript("scroll(0,550);");
//		
		WebDriverWait wait = new WebDriverWait(driver, (long)10.0);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("gender"))));
		
		try {
			
		List<WebElement> eleRadiosButtons = driver.findElements(By.name("gender"));
		//Iterate through each element in the List and click on it
		for(WebElement element : eleRadiosButtons){
			element.click();
				Thread.sleep(1000);		
			}
		
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	
	
}
