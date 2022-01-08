package DataManagment;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import relations.User;

public class dataRetrival {
	
	/*Retriving Client Data*/
	public static ArrayList<ArrayList<Object>> getClientData(String userName, String password)
	{
		String retrivalCommand = "SELECT * FROM  CLIENT natural join USERS "
				+ "WHERE USERNAME = '"+userName+"' and PASSWORD = '"+password+"'";
		System.out.println(retrivalCommand);
		return DataConnection.executeQuery(retrivalCommand);
	}
	
	/*Retriving FreeLancer Data*/
	public static ArrayList<ArrayList<Object>> getFreeLancerData(String userName, String password)
	{
		String retrivalCommand = "SELECT * FROM  FREE_LANCER natural join USERS "
				+ "WHERE USERNAME = '"+userName+"' and PASSWORD = '"+password+"'";
		return DataConnection.executeQuery(retrivalCommand);
	}
	
	/*RETRIEVING GIGS FOR FREELANCER*/
	public static ArrayList<ArrayList<Object>> getGigs()
	{
		String retriveGigs = "SELECT GIGS.ID, MIN(gigs.title),MIN(gigs.description), MIN(packages.price) "
				+ "FROM GIGS, PACKAGES "
				+ "WHERE gigs.id = packages.gigs_id and gigs.free_lancer_freelancerid = '"+User.getID()+"' "
				+ "GROUP BY GIGS.ID";
		return DataConnection.executeQuery(retriveGigs);
	}
	
	/*RETRIVE ONE GIG*/
	public static ArrayList<Object> getGig(int gigID)
	{
		String retriveGig = "SELECT * FROM DETAILEDGIG WHERE DETAILEDGIG.ID = "+gigID;
		return DataConnection.executeQuery(retriveGig).get(0);
	}
	
	/*Get Available Packages of a certain gig*/
	public static ArrayList<ArrayList<Object>> getPackages(int gigID)
	{
		String retrivePackages = "SELECT PACKAGES_ID, PACKAGETYPES_PACKAGETYPE, PRICE, DETAILED_DESCRIPTION FROM PACKAGES WHERE GIGS_ID = "+gigID;
		return DataConnection.executeQuery(retrivePackages);
	}
	
	public static ArrayList<ArrayList<Object>> getPendingOrders()
	{
		String retrivePendingOrders = "SELECT PACKAGES_PACKAGES_ID,TITLE AS GIGTITLE, PACKAGETYPES_PACKAGETYPE AS PACKAGETYPE, "
										+ "orders.client_username AS CustomerName, "
										+ "ORDERDESCRIP "
										+ "FROM ORDERS, PACKAGES, GIGS "
										+ "WHERE ORDERS.PACKAGES_PACKAGES_ID = PACKAGES.PACKAGES_ID and PACKAGES.gigs_id = gigs.id  "
										+ "and  gigs.free_lancer_freelancerid = "+User.getID();
		return DataConnection.executeQuery(retrivePendingOrders);
	}
	
	public static ArrayList<ArrayList<Object>> getCompletedOrders()
	{
		String retrivePendingOrders = "SELECT PACKAGES_PACKAGES_ID,TITLE AS GIGTITLE, PACKAGETYPES_PACKAGETYPE AS PACKAGETYPE, \r\n"
				+ "        COMPLETEORDERS.client_username AS CustomerName, \r\n"
				+ "        ORDERDESCRIP, WORKLINK\r\n"
				+ "        FROM COMPLETEORDERS, PACKAGES, GIGS \r\n"
				+ "        WHERE COMPLETEORDERS.PACKAGES_PACKAGES_ID = PACKAGES.PACKAGES_ID and PACKAGES.gigs_id = gigs.id  \r\n"
				+ "        and  gigs.free_lancer_freelancerid = " + User.getID();
		return DataConnection.executeQuery(retrivePendingOrders);
	}
	
	public static int getUniqueGigID()
	{
		String getGigID = "SELECT GIG_SEQ.nextval FROM DUAL";
		return ((BigDecimal) DataConnection.executeQuery(getGigID).get(0).get(0)).intValue();
	}
	
	public static int getUniquePackageID()
	{
		String getPkgID = "SELECT PACKAGE_SEQ.nextval FROM DUAL";
		return ((BigDecimal) DataConnection.executeQuery(getPkgID).get(0).get(0)).intValue();
	}
}
