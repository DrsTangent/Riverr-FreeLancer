package DataManagment;

import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.OracleResultSet;

public class DataConnection {
	final static String userName = "riverr_db";
	final static String password = "riverr_db";
	final static String SID = "orcl";
	final static String ip = "localhost";
	final static String port = "1521";
	private static Connection myconn = null;
	
	private static void connectToDB()
	{
		try {
			myconn = DriverManager.getConnection(
					"jdbc:oracle:thin:@"
					+ ip + ":" 
					+ port + ":"
					+ SID,
					userName,
					password
					);
		} catch (SQLException e) {
			System.out.println("Not Able to Connect");
			e.printStackTrace();
		}
				
	}
	
	private static void closeConnection()
	{
		try {
			myconn.close();
		} catch (Exception e) {
			System.out.println("Error while closing the connection");
			e.printStackTrace();
		}
	}
	
	public static void updateQuery(String Query)
	{
		connectToDB(); 
		
		
		try {
			Statement smt = myconn.createStatement();
			smt.executeQuery(Query);
			smt.executeQuery("COMMIT");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Error while Fecthing/Writing Data");
			e.printStackTrace();
		}
		closeConnection();//Close Connection
	}
	
	public static ArrayList<ArrayList<Object>> executeQuery(String Query)
	{
		connectToDB(); // Connection to DB
		//Array//
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
		
		/*Fecthing Data from Database*/
		ResultSet rs = null;
		
		try {
			PreparedStatement smt = myconn.prepareStatement(Query);
			rs = smt.executeQuery();

			//Stores properties of a ResultSet object, including column count
			ResultSetMetaData rsmd = rs.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next())
			{
				ArrayList<Object> row = new ArrayList<Object>();
				int i = 1;
				while(i<=columnCount)
				{
					row.add(rs.getObject(i++));
				}
				data.add(row);
			}
			
			smt.executeQuery("COMMIT");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while Fecthing/Writing Data");
			e.printStackTrace();
		}
		/*Stop Fecthing Data from Database*/
		
		
		
		closeConnection();//Close Connection
		
		return data;
	}
	
	
}
