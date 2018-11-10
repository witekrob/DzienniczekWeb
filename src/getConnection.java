

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class getConnection{
public getConnection() {}
	
	public Connection connect() throws SQLException {
	     
		try {

		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("driver loaded");

		} catch (Exception e) {

		    System.out.println(e.toString());

		}
		
		final String driver = "com.mysql.jdbc.Driver";
	        final String dbPath = "jdbc:mysql://localhost:3306/dzienniczek";
	        Connection conn = DriverManager.getConnection(dbPath, "root", "admin");
	        return conn;
	}
	
}