package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GoodsUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/shoppingweb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";
	private Connection connection = null;
	
	static{
		try {
			java.lang.Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public  Connection getConnection(){
		if(connection == null){
			try {
				connection=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
		else{
			return connection;
		}
	}
	
}
