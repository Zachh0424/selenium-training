package pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class home {
	
	WebDriver driver;

	
	//Declare WebElement variables
	By ps4Deptmenu = By.xpath("//*[@id=\"navline3\"]/div[1]/div/ul/li[2]");
	By allLinkPath = By.xpath("//*[@id=\"pf_ps4\"]/div[2]/ul/li[1]/a");
	By searchBar = By.id("searchtext");
	By firstProduct = By.xpath("//*[@id=\"mainContentPlaceHolder_dynamicContent_ctl00_RepeaterResultFoundTemplate_ResultFoundPlaceHolder_1_ctl00_1_ctl00_1_StandardPlaceHolderTop_2_ctl00_2_CartridgeCatalogPanel_2\"]/div[3]/div[2]");
	By linkTag = By.className("ats-product-title-lnk");
	By addBtn = By.name("lnkAddToCart");
	By cartBtn = By.className("ats-cartbtn");
	By tradeValBtn = By.xpath("//*[@id=\"navline3\"]/div[1]/div/ul/li[13]/a");
	
	
	//Declare DB variables
	String query = "";
	Statement stmt;
	ResultSet result;
	
	public home(WebDriver driver){
		
		this.driver = driver;
	}
	
	
	//method to perform the mouse over function on the deptment menu
	public void mouseOver(){
		
		driver.findElement(ps4Deptmenu); // locate the element
		WebElement ps4Dept = driver.findElement(ps4Deptmenu);

		// make action for mouseOver
		Actions action = new Actions(driver);
		action.moveToElement(ps4Dept).build().perform();

		// now create loop to keep ps4Dept menu open
		boolean active = false;

		do {
			action.moveToElement(ps4Dept).build().perform();
			try {
				active = ps4Dept.getAttribute("class").contains("dropdown active");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!active);

	}
	
	
	
	// method to pull up all the ps4 games
	public void clickAllLink() {

		// use a wait to check the visibility of the All link
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(allLinkPath));
		wait.until(ExpectedConditions.presenceOfElementLocated(allLinkPath));

		driver.findElement(allLinkPath).click();

	}

	
	/*
	 * for DB overview here we can track the num. of iterations, parameters,
	 * and checkpoint results if any are added.
	 */
	
	//method to search games in the DB gamestop table
	public void gameSearch(Connection conn){
		
		
	try{
		//query to setup the view table of the db
		query = "CREATE VIEW GameStop_Details (Number_of_Iterations, Removal_Iterations) AS ("
				+ "SELECT COUNT(games) FROM zach.gamestop"
				+ ");"; 
		
		stmt = conn.createStatement();
		stmt.executeUpdate(query);	//executeQuery(query);
		
	}catch(Exception e){
		e.printStackTrace();
	}
		
		try{
			//get the count of rows in games for for loop to use for iterating searches
			String count = "SELECT COUNT(games) FROM gamestop;";
			stmt = conn.createStatement();
			ResultSet rowCount = stmt.executeQuery(count);
			rowCount.next();
			int numRows  = rowCount.getInt(1);
			System.out.println("there are " + numRows + " games in the DB to search.");
				
					query = "SELECT games FROM gamestop;";
					stmt = conn.createStatement();
					result = stmt.executeQuery(query);
			

					while(result.next()){
						
						System.out.println(result.getString("games"));
						
						
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.presenceOfElementLocated(searchBar));
						WebElement gameSearch = driver.findElement(searchBar);
						
						gameSearch.sendKeys(result.getString("games"));
						WebElement searchButton = driver.findElement(By.id("searchbutton"));
						searchButton.click();
						
	//					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mainContentPlaceHolder_dynamicContent_ctl00_RepeaterRightColumnLayouts_RightColumnPlaceHolder_0_ctl00_0_LayoutStandardPanel_0\"]/div[5]/div[2]/div[2]/div[2]/h3")));
				
					addToCart(conn, result);
					
					}
						
						
					
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
			}
		
	
	
	//method to add the price of the current item to the database
	public void savePrice(Connection conn, ResultSet result){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement priceCol = driver.findElement(By.className("col2"));
		//WebElement priceDiv = driver.findElement(By.className("ats-prodBuy-price"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mainContentPlaceHolder_dynamicContent_ctl00_RepeaterRightColumnLayouts_RightColumnPlaceHolder_0_ctl00_0_LayoutStandardPanel_0\"]/div[5]/div[2]/div[2]/div[2]/h3")));
		WebElement priceDiv = driver.findElement(By.xpath("//*[@id=\"mainContentPlaceHolder_dynamicContent_ctl00_RepeaterRightColumnLayouts_RightColumnPlaceHolder_0_ctl00_0_LayoutStandardPanel_0\"]/div[5]/div[2]/div[2]/div[2]/h3"));
		WebElement price = priceDiv.findElement(By.tagName("span"));
		String gamePrice = price.getText();
		System.out.println(gamePrice);
		
		try {
		//query to update the price in the database
			query = "UPDATE zach.gamestop "
				    + "SET price = ('" + gamePrice + "')"
				    + " WHERE gamestop.games = '" + result.getString("games") + "';";
		
			
			//System.out.println(result.getNString("games"));
			
		
		stmt = conn.createStatement();
			stmt.executeUpdate(query);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//method to add the games in the database to the cart
	public void addToCart(Connection conn, ResultSet result){
		
		// grab the first item in the search results list
		WebElement firstInList = driver.findElement(firstProduct);
		try {
			WebElement prodLink = firstInList.findElement(linkTag);
			prodLink.click();
		} catch (Exception e) {
			WebElement prodLink = firstInList.findElement(By.xpath(
					"//*[@id=\"mainContentPlaceHolder_dynamicContent_ctl00_RepeaterResultFoundTemplate_ResultFoundPlaceHolder_1_ctl00_1_ctl00_1_StandardPlaceHolderTop_2_ctl00_2_rptResults_2_res_0_hypTitle_0\"]"));
			prodLink.click();
		}

		//add item price to the database
		savePrice(conn, result);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(addBtn));
		// click the Add to cart button
		WebElement add = driver.findElement(addBtn);
		add.click();

		try {
			// close out of pop-up window if it shows
			
			// wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fancybox-skin")));
			if (driver.findElement(By.className("fancybox-skin")).isDisplayed()) {
				// if(driver.findElement(By.className("fancybox-skin")).getSize()
				// != null){
//				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/a")));
				WebElement close = driver.findElement(By.xpath("/html/body/div[3]/div/a"));
				close.click();
			}

		} catch (Exception e) {

		}

	}
	
	
	
	

	// method to click on the cart button to view the cart status
	public void viewCart() {

		WebElement cartbtn = driver.findElement(cartBtn);
		cartbtn.click();

	}
	
	
	
	//method to remove all items from the cart
	public void Remove(Connection conn){

			// gameStop puts cart items in a table so use webtable
			WebElement table = driver.findElement(By.tagName("table")); // grab  table element
			List<WebElement> rows = table.findElements(By.tagName("tr")); // define the list of elements "rows"

			int numRows = rows.size();
			int actNumRows = (int) Math.floor(numRows / 2);
			System.out.println(actNumRows + " items in the cart");

			// loop to remove items from cart
			for (int i = 1; i <= actNumRows; i++) {
				// print to check what i is at each iteration
				System.out.println(i + "/" + actNumRows + " items being removed");
				// find and click the remove element
				
				try {
					// WebElement remove =
					// table.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/table[1]/tbody/tr[2]/td[5]/a"));
					WebElement remove = driver.findElement(By.className("ats-remove"));
					// WebElement remove = table.findElement(By.linkText("trash"));
					remove.click();
					
					
					
					driver.navigate().refresh();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			try{
			query =   "ALTER VIEW GameStop_Details(Removal_Iterations) "
					+ "AS ("
					+ "SELECT COUNT(games) "
					+ "FROM zach.gamestop);";
					
					

			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
	}
	
	
	
	
		//method to go to the Trade Offers and Values page
			public void tradeInValue(){
				
				WebElement tradeBtn = driver.findElement(tradeValBtn);
				tradeBtn.click();
				
			}
	
	
		
			//method to search the games from the excel list again
			//(re-doing the list for practice purposes)
			public void searchOffers(Connection conn) {
				
				
				//enter the Games in the search bar 
				WebElement gamesBtn = driver.findElement(By.xpath("//*[@id=\"tradeSearchMenu\"]/li[1]/i"));
				gamesBtn.click();
				
				try{
					query = "SELECT games FROM gamestop;";
					stmt = conn.createStatement();
					result = stmt.executeQuery(query);

					
					while(result.next()){
						WebElement tradeSearchBox = driver.findElement(By.id("gamesSearchbox"));
						tradeSearchBox.sendKeys(result.getString("games"));
						tradeSearchBox.submit();
						
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tradeOffer\"]/div/div[1]/div/div[2]/div[1]/div[4]/span")));
					WebElement tradeVal = driver.findElement(By.xpath("//*[@id=\"tradeOffer\"]/div/div[1]/div/div[2]/div[1]/div[4]/span"));
					String value = tradeVal.getText();
					
					//add trade in value to database
					query = "UPDATE zach.gamestop "
							+ "Set tradeValue = ('" + value + "')"
									+ "WHERE gamestop.games = '" + result.getString("games") + "';";
				
					stmt = conn.createStatement();
					stmt.executeUpdate(query);
					
					driver.navigate().back();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

					}
		
	
			
		
	
	
	
	
	
	
	}
	
	
	
	
	


