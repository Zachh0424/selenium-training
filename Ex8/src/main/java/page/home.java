package page;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import test.Test;

public class home {

	
	//Declare WebDriver
	WebDriver driver;
	//Declare wait
	//WebDriverWait wait = new WebDriverWait(driver, 10);
	
	Test test;
	

	//create constructor
	public home(WebDriver driver){
		
		//switch drivers
		//PageFactory.initElements(driver, this);
		this.driver = driver;
		
	}
	
	
	//create method 
	public void tablesLink(){
		
		WebElement link = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[36]/a"));
		link.click();
	}
	
	
	
	//create method to print the first column in the first table
	public void firstColumn(){
		
		String[] tableIds = {"table1", "table2"};
		
		WebElement tableElement1 = driver.findElement(By.xpath("//*[@id='table1']/tbody/tr[1]/td[1]"));
       // System.out.println("Row1--column1 text " + tableElement1.getText());
		
        //get the number of rows
        List<WebElement> colCount = driver.findElements(By.xpath("//*[@id='table1']/thead/tr/th"));
        int numCols = colCount.size();
      //  System.out.println(numRows);
        
        List<WebElement> rowCount = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
        int numRows = rowCount.size();
     //   System.out.println(numRows);
      
        
		//create a for loop to iterate through the table 
		for(int i = 1; i <= numRows; i++ ){
			try{
				
			WebElement cell = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/ td[1]"));
			
			System.out.println(cell.getText());
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

	
	}
	
	
	//create a method to display everything in the first row
	public void firstRow(){
		
		System.out.println("");
		WebElement firstRow = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]"));
		System.out.println(firstRow.getText());
		
	}
	
	
	
	//create method to find the row that contains certain text
	public void rowSearch(){
		
		Scanner kb = new Scanner(System.in);
		String input;
		
		System.out.println("What text would you like to search?");
		input = kb.nextLine();
		
		
		//if the user types nothing, search the default email
		if(input.equalsIgnoreCase("no")){
			
			input = "jdoe@hotmail.com";
			
		}
		
		System.out.println("Looking for:" + input);
		
	    //get the number of rows
	    List<WebElement> colCount = driver.findElements(By.xpath("//*[@id='table1']/thead/tr/th"));
	    int numCols = colCount.size();
	  //  System.out.println(numRows);
	    
	    List<WebElement> rowCount = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
	    int numRows = rowCount.size();
	 //   System.out.println(numRows);
		
		
		//now loop through each row to find text
		
		for(int j = 1; j<= numCols; j++){
		for(int i = 1; i <= numRows; i++ ){
			try{
				
			WebElement cell = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/ td[" + j + "]"));
			int row = i;
			int col = j;
			
			if(cell.getText().contains(input)){
			
			System.out.println("The text was found on row: " + row);
			
			System.out.println();
			
			System.out.println("Now to delete the row containing the text");
			driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr["+ row +"]/td[6]/a[2]")).click();
			System.out.println("done. Row was deleted. ");
			
			}
			

			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

		}
		
		
		
		
	}
	
	
	
	
}
