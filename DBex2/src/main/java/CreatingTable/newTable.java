package CreatingTable;

import java.sql.*;
import java.util.Scanner;

import tests.GameTest;



public class newTable {

	//declare variables for database connection
	String host = "10.238.242.55";
	String port = "3306";
	String dbName = "zach";
	String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
	String userName = "zach";
	String password = "Orasi01!";
	Connection conn = null;
	String driver = "com.mysql.jdbc.Driver";
	
	
	//Declare variables for DB use
	String query = "";
	Statement stmt;
	
	
	public static void main(String[] args){
		//call the constructor to set up the JDBC
		new newTable();
	
	}
	
	
	public newTable(){
		
		//try to set up the JDBC
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, userName, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		//now call the method to create a new table
//		createTable();
		
		//call method to populate the games col with game titles
//		populateGames();
		
		//call Test class\
		new GameTest(conn);
		
		//call method to close the connection
//		close();
	
	
	}
	
	
	
	
	public void createTable(){
		
		// Try query to create new table in the zach DB
		System.out.println("creating the new table");
		
		try{
		 query = "CREATE TABLE GameStop("
				+ "games varchar(255),"
				+ "price varchar(255),"
				+ "tradeValue varchar(255),"
				+ "PRIMARY KEY (games)"
				+ ");"; 
		
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate(query);//executeQuery(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	//method to insert data into the gamestop table
	public void populateGames(){
		
		//ask the user what games to put in the table
		Scanner kb = new Scanner(System.in);
		String input = "";
		
	
		
		//try to insert games into the database
		while(!input.equalsIgnoreCase("no")){
			
				//get game(s) to populate table from user
				System.out.println("Please enter a game or enter no if done: ");
				input = kb.nextLine();
				
			if(!input.equalsIgnoreCase("no")){
				//Insert input games into the games column
				try{
					query = "INSERT IGNORE INTO gamestop(games)"
					+ "VALUES ('" + input + "');";
					stmt = conn.createStatement();
					stmt.executeUpdate(query);
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
			}
		}
		//if input = no tell user
		if(input.equalsIgnoreCase("no")){
			System.out.println("you have sent 'no' so insert is complete");
		}
		
	}
	
	
	
	
	//method to close the connection to the DB
	public void close(){
		
		
		//Drop the new gamestop table
		try {
			query = "DROP TABLE gamestop;";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//close the view
		try {
			query = "DROP VIEW gamestop_details;";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//try to close the connection if Statement stmt !null
		if(stmt != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
