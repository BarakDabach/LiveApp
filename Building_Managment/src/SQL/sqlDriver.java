package SQL;
import java.sql.*;

import javax.swing.JOptionPane;

import org.omg.CORBA.Environment;

public class sqlDriver {
	static String databaseURL 
	= "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7363722?" + "user=sql7363722&password=LkdXCLqNGX" ; 
	static Connection con;
	static ResultSet result;
	static Statement statment;
	public static PreparedStatement preStatment;
	
	
	//function that Connects to the dataBase
	public static Connection connect() {

		try {
			
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(databaseURL);
			
		} 

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//			JOptionPane.showMessageDialog(null,"DataBase Error,To Many onn Please Try Again","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			
		} 
		return con;	
		
	}
	
	
	
	//Function that create a query and returning the result set for manipulation.
	public static ResultSet sendQuery(String query) {
		try {
			
			connect();
			statment = con.createStatement();
			result = statment.executeQuery(query);
			return result;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Could Not Create Statment","Error",JOptionPane.ERROR_MESSAGE);
		
		}
		
		return result;
		
	}
	
	
	
	
	
	
}
	
	
