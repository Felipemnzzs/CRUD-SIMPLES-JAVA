package Modelos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
		
	private static final String URL = "jdbc:mysql://localhost:3306/crud?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	 private static final String PASS = "B7139.58426d";
	
	 public static Connection getConnection() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASS);
	        } catch (ClassNotFoundException | SQLException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
	 
}
