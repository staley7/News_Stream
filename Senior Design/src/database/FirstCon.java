package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import manager.Manager;
import manager.IPreferences;

/**
 * @author Charles Litfin
 */
public class FirstCon{
	private static FirstCon con;
	
	static Connection connection = null;

	static String driverName = "com.mysql.jdbc.Driver"; 

	private static String uri; 
	private static String username;
	private static String password;
	
	public FirstCon(){
		IPreferences pref = Manager.getPreferences();
		uri = pref.getDatabaseHost();
		username = pref.getDatabaseUsername();
		password = pref.getDatabasePassword();
	}
	public static boolean doConnection(){
		try {
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			connection = DriverManager.getConnection(uri, username, password);
		       
		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("ClassNotFoundException : "+e.getMessage());
			return false;
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public Connection returnCon(){
		FirstCon.doConnection();
		return connection;
	}
	
	public static void main(String[] args)
	{
		con =new FirstCon();
		con.doConnection();
	}

}			
