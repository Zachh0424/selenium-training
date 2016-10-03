package tests;


import java.sql.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import pages.home;

public class GameTest {

	WebDriver driver;
	String url = "https://www.gamestop.com";
	
	home objHome;

	

	public GameTest(Connection conn){
		
		
		
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		
		//call first operation method
		mouseOverDept();
		
		//call to click the all link in the dept menu
		clickAllLink();
		
		//call method to search games from database
		gameHunt(conn);
		
		viewCart();
		
		remove(conn);
		
		tradeInValue();
		
		searchOffers(conn);
		
//		int count = GameTest.args.length;
//		System.out.println(count);
		
	}
	
	
	@BeforeTest
	public void mouseOverDept(){
		//connect/switch drivers
		objHome = new home(driver);
		
		//call function to perform operation
		objHome.mouseOver();
	}
	
	
	public void clickAllLink(){
		
		//connect/switch drivers
		objHome = new home(driver);
		
		//call function to perform operation
		objHome.clickAllLink();
	}
	
	
/*
 * for DB overview here we can track the num. of iterations, parameters,
 * and checkpoint results if any are added.
 */
	
	//method to call function to search games in the database
	public void gameHunt(Connection conn){
		//connect/switch drivers
		objHome = new home(driver);
		
		//call function to perform operation
		objHome.gameSearch(conn);
		
	}
	
	
	// call the method to click and view what's in the cart
		public void viewCart() {

			objHome = new home(driver);

			objHome.viewCart();
		}

		
		
		
		/*
		 * for DB overview here we can track the num. of iterations, parameters,
		 * and checkpoint results if any are added.
		 */
		
		@AfterTest
		// call the method to remove everything in the cart
		public void remove(Connection conn) {

			objHome = new home(driver);

			objHome.Remove(conn);
		}
		
		
		//method to go to the trade in value page
		public void tradeInValue(){
			
			objHome = new home(driver);
			
			objHome.tradeInValue();
			
		}
		
		
		/*
		 * for DB overview here we can track the num. of iterations, parameters,
		 * and checkpoint results if any are added.
		 */
		
		
		//call method to search through offers from trade or sell
		public void searchOffers(Connection conn) {
			
			objHome = new home(driver);
			
			objHome.searchOffers(conn);
			
		}
		
		
}
