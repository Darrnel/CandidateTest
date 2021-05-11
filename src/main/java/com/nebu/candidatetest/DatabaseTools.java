package com.nebu.candidatetest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class holding static functions for database management. 
 * @author test
 *
 */
public class DatabaseTools {
	
	
	/**
	 * Method to be called to get a basic connection to the Candidate database with the sa user.
	 * @return the connection to the Candidate database
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
	}
	
}
