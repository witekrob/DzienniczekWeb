package util;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ConnectionProvider {
private static DataSource dataS;

public static Connection getConnection()throws SQLException {
	return getDSInstance().getConnection();
}
public static DataSource getDSInstance() {
	if (dataS ==null) {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context) initContext.lookup("java:comp/env");
			dataS = (DataSource)envContext.lookup("jdbc/dzienniczek"); 		
		}
		catch (NamingException n ) {
			n.printStackTrace();
		}
}
	return dataS;
}
}
