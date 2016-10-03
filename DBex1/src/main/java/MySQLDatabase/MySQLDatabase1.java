package MySQLDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class MySQLDatabase1 {
	
	//driver = DbDriver("com.mysql.jdbc.Driver");
	
	String host = "10.238.242.55";
	String port = "3306";
	String dbName = "sakila";
	
	String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
	String driver = "com.mysql.jdbc.Driver";
	String userName = "zach";
	String password = "Orasi01!";
	Connection conn = null;
	String query = "";
	Statement stmt;
	ResultSet result;
	
	
	public static void main(String[] args){
		new MySQLDatabase1();
	}
	
	public MySQLDatabase1(){
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		//change4Distinct();
		
		//showFromDuration4();
		
		//OrderWrite();
		
		//filmActorsLT100();
		
		//MaxMin();
		
		First2Last();
		
	}

	//method to return all of the film id's and descriptions from those with a rental duration of 4
	public void showFromDuration4(){
		//give description of the method
		System.out.println("\nThis will return all of the film id's and desc. of films with a rental duration of 4: \n\n");

		//create a query and statement for execution
		try{
			
			query ="SELECT * FROM film WHERE rental_duration = 4;";
			
			///////////     OR you could do it this way below ////////////////
			
			//String query =  "SELECT description, film_id FROM film WHERE rental_duration = 4;";
			
			stmt = conn.createStatement(); 
			result = stmt.executeQuery(query);	//store in a result variable to (in this case) print out
			
			//loop through all the results (films with duration of 4) and print each one
			while(result.next()){;
			System.out.println(result.getString("film_id") + result.getString("description"));
			}
			
		
		} catch (SQLException e) {
				e.printStackTrace();
			}	
			
		
	}
	
	//create a method to update a Language_id cell to use for Distinct later
	public void change4Distinct(){
		
		//create a try catch in case of Exceptions are thrown
		try{
			//create update query to change lang_id from 1 to 2
		//String updateQuery =	"UPDATE language_id FROM film WHERE title = ALICE FANTASIA SET language_id = 2;";
		String updateQuery = "UPDATE film SET language_id = 2 WHERE title = 'ALICE FANTASIA';";
		//create statement for execution
		Statement updateStatement = conn.createStatement();
		int result;
		result = updateStatement.executeUpdate(updateQuery);
		
		//now print out the updated line
		System.out.println(result);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

//	//now create a method to find distinct or different values
//	public void findDistinct(){
//		
//	}
//	

	
	//create a method to order all fields by length and write it to an excel sheet
	public void OrderWrite(){
		
		HSSFWorkbook dataWB = new HSSFWorkbook();
		HSSFSheet dataSheet = dataWB.createSheet("filmList"); 
		int i = 1;
		
		//surround in try catch
		try{
			//write query to order the db by the column length
			query = "SELECT * FROM film ORDER BY length ASC;";
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			
			//loop to print results
			while(result.next()){
			//print out the result
			System.out.println("Length: " + result.getString("length") + "   Film_ID: " + result.getString("film_id"));
			
			//Now write it all to an Excel sheet
			//File excelFile = new File("C:/Users/zach.hutchinson.ORASI/Desktop/new.xls");
			//FileInputStream fis = new FileInputStream(excelFile);
		
			HSSFRow row = dataSheet.createRow((short) i);
			row.createCell(0, 0).setCellValue(result.getString("length")); 
			
			String excelFile = "C:/Users/zach.hutchinson.ORASI/Desktop/newDB.xls";
			FileOutputStream fos = new FileOutputStream(excelFile);
			dataWB.write(fos);
			
			row.createCell(1, 0).setCellValue(result.getString("film_id"));
			dataWB.write(fos);
			i++;

			dataWB.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	//create a method to join all actors w/ id < 100 with their film desc. and all
	public void filmActorsLT100(){
		
	

		try {
			
			query = "SELECT film.description, film.film_id, film_actor.actor_id "
					+ "FROM film_actor "
					+ "INNER JOIN film "
					+ "ON film_actor.film_id = film.film_id "
					+ "WHERE actor_id<100;";
		
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		
		while(result.next()){
			String filmID = result.getString("film_id");
			String actorID = result.getString("actor_id");
			String desc = result.getString("description");
			System.out.println( filmID + " " + actorID + " " + desc);
			System.out.println();
			
//			if(!result.next()){
//			String query = "SELECT COUNT(*) FROM " + filmID;
//			result = stmt.executeQuery(query);
//			System.out.println(result);
//			}
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//create method to retrieve max and mins of data 
	public void MaxMin(){
		//ask user what columns and tables to get max or min from
		Scanner kb = new Scanner(System.in);
		
		String op = "";
		String table = "";
		String col = "";
		
		System.out.println("What table do you want to search from? ");
		table = kb.nextLine();
		
		System.out.println("What column would you like to search? ");
		col = kb.nextLine();
		
		System.out.println("What do you want to find. 1)Max or 2)Min? (enter number)");
		int num = kb.nextInt();
		
		if(num == 1){
			op = "MAX";
		}
		if(num == 2){
			op = "MIN";
		}
//		else if(num != 1 || num != 2){
//			System.out.println("Did not recieve valid input");
//		}
		
		try{
			
			query = "SELECT " + op + "(" + col + "), payment_id FROM " + table;
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			
			if(result.next()){
				int max = result.getInt(1);
			System.out.println("\n the " + op + " from " + table + " column " + col + " is: " + max + " paymentID: " + result.getString("payment_id"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	//create method that returns first and last values of a selected column
	public void First2Last(){
		
		String col = "";
		String table = "";
		Scanner kb = new Scanner(System.in);
		
		System.out.println("What table would you like to search from? ");
		table = kb.nextLine();
		
		System.out.println("What column do you want to search? ");
		col = kb.nextLine();
		
		try{
			
			query = "SELECT " + col + ", film_id FROM " + table 
					+ " ORDER BY " + col + " ASC "
					+ "LIMIT 1;";
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			result.next();
			System.out.println("the first field in " + col + "from table " + table + " is: film_id:"  + result.getString("film_id") + " " + result.getString(1));
		
			query = "SELECT " + col + ", film_id FROM " + table 
					+ " ORDER BY " + col + " DESC "
					+ "LIMIT 1;";
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			result.next();
			System.out.println("the last field in " + col + "from table " + table + " is: film_id: " + result.getString(1));
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
