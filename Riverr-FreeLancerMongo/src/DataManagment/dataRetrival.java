package DataManagment;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import relations.User;

public class dataRetrival {
	
	public static void main(String[] args)
	{
		System.out.println(getUniquePackageID());
	}
	
	/*Retriving Client Data*/
	public static ArrayList<ArrayList<Object>> getClientData(String userName, String password)
	{
		String retrivalCommand = "SELECT * FROM  CLIENT natural join USERS "
				+ "WHERE USERNAME = '"+userName+"' and PASSWORD = '"+password+"'";
		System.out.println(retrivalCommand);
		return null;
		
	}
	
	/*Retriving FreeLancer Data*/
	public static ArrayList<ArrayList<Object>> getFreeLancerData(String userName, String password)
	{
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        AggregationOutput resultantCollection = mycollection.aggregate(
        		new BasicDBObject("$match", new BasicDBObject("username", userName).append("password", password))
        		);
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        for(DBObject obj: resultantCollection.results())
        {
        	ArrayList<Object> row = new ArrayList<Object>();
        	row.add(obj.get("username"));
        	row.add(obj.get("freelancerid"));
        	row.add(obj.get("first_name"));
        	row.add(obj.get("last_name"));
        	row.add(obj.get("contacts"));
        	row.add(obj.get("email"));
        	
        	data.add(row);
        }
		return data;
	}
	
	/*RETRIEVING GIGS FOR FREELANCER*/
	public static ArrayList<ArrayList<Object>> getGigs()
	{
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        AggregationOutput resultantCollection = mycollection.aggregate(
        		Arrays.asList(
        				new BasicDBObject("$unwind","$gigs"), 
        				new BasicDBObject("$project", 
        						new BasicDBObject("gigID","$gigs.gigId")
        						.append("gigTitle", "$gigs.title")
        						.append("gigDescription", "$gigs.description")
        						.append("minPrice", new BasicDBObject("$min", "$gigs.packages.price"))))
        		);
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        for(DBObject obj: resultantCollection.results())
        {
        	ArrayList<Object> row = new ArrayList<Object>();
        	row.add(obj.get("gigID"));
        	row.add(obj.get("gigTitle"));
        	row.add(obj.get("gigDescription"));
        	row.add(obj.get("minPrice"));
        	System.out.println(obj.get("minPrice"));
        	data.add(row);
        }
		return data;
	}
	
	/*RETRIVE ONE GIG*/
	public static ArrayList<Object> getGig(int gigID)
	{
		
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        AggregationOutput resultantCollection = mycollection.aggregate(
        		Arrays.asList(
        				new BasicDBObject("$unwind","$gigs"), 
        				new BasicDBObject("$match", new BasicDBObject("gigs.gigId", gigID)),
        				new BasicDBObject("$project", 
        						new BasicDBObject("gigID","$gigs.gigId")
        						.append("gigTitle", "$gigs.title")
        						.append("username", "$username")
        						.append("gigDescription", "$gigs.description")
        						.append("fullName", new BasicDBObject("$concat", Arrays.asList("$first_name", " ", "$last_name")))))
        		);
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        for(DBObject obj: resultantCollection.results())
        {
        	ArrayList<Object> row = new ArrayList<Object>();
        	row.add(obj.get("gigID"));
        	row.add(obj.get("gigTitle"));
        	row.add(obj.get("username"));
        	row.add(obj.get("gigDescription"));
        	row.add(obj.get("fullName"));
        	data.add(row);
        }
		return data.get(0);
	}
	
	/*Get Available Packages of a certain gig*/
	public static ArrayList<ArrayList<Object>> getPackages(int gigID)
	{
		/*
		 * db.FreeLancers.aggregate([{$unwind: "$gigs"}, 
		 * {"$unwind": "$gigs.packages"}, 
		 * {$match: {"gigs.gigId":1}}, 
		 * {$project: {"packageID":"$gigs.packages.packageID", 
		 * "packageType":"$gigs.packages.type", 
		 * "price":"$gigs.packages.price", 
		 * "detailedDescription":"$gigs.packages.detailedDescription"}} ]);
		 */
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        AggregationOutput resultantCollection = mycollection.aggregate(
        		Arrays.asList(
        				new BasicDBObject("$unwind","$gigs"), 
        				new BasicDBObject("$match", new BasicDBObject("gigs.gigId", gigID)),
        				new BasicDBObject("$unwind", "$gigs.packages"),
        				new BasicDBObject("$project", 
        						new BasicDBObject("packageID","$gigs.packages.packageID")
        						.append("packageType", "$gigs.packages.type")
        						.append("price", "$gigs.packages.price")
        						.append("detailedDescription", "$gigs.packages.detailedDescription")
        						))
        		);
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        for(DBObject obj: resultantCollection.results())
        {
        	ArrayList<Object> row = new ArrayList<Object>();
        	row.add(obj.get("packageID"));
        	row.add(obj.get("packageType"));
        	row.add(obj.get("price"));
        	row.add(obj.get("detailedDescription"));
        	data.add(row);
        }
		return data;
	}
	
	public static ArrayList<ArrayList<Object>> getPendingOrders()
	{
		String retrivePendingOrders = "SELECT PACKAGES_PACKAGES_ID,TITLE AS GIGTITLE, PACKAGETYPES_PACKAGETYPE AS PACKAGETYPE, "
										+ "orders.client_username AS CustomerName, "
										+ "ORDERDESCRIP "
										+ "FROM ORDERS, PACKAGES, GIGS "
										+ "WHERE ORDERS.PACKAGES_PACKAGES_ID = PACKAGES.PACKAGES_ID and PACKAGES.gigs_id = gigs.id  "
										+ "and  gigs.free_lancer_freelancerid = "+User.getID();
		return null;
	}
	
	public static ArrayList<ArrayList<Object>> getCompletedOrders()
	{
		String retrivePendingOrders = "SELECT PACKAGES_PACKAGES_ID,TITLE AS GIGTITLE, PACKAGETYPES_PACKAGETYPE AS PACKAGETYPE, \r\n"
				+ "        COMPLETEORDERS.client_username AS CustomerName, \r\n"
				+ "        ORDERDESCRIP, WORKLINK\r\n"
				+ "        FROM COMPLETEORDERS, PACKAGES, GIGS \r\n"
				+ "        WHERE COMPLETEORDERS.PACKAGES_PACKAGES_ID = PACKAGES.PACKAGES_ID and PACKAGES.gigs_id = gigs.id  \r\n"
				+ "        and  gigs.free_lancer_freelancerid = " + User.getID();
		return null;
	}
	
	public static int getFreeLancerID()
	{
		int i = 0;
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        
       AggregationOutput resultantCollection = mycollection.aggregate(
    		   Arrays.asList(
    				   new BasicDBObject("$project", new BasicDBObject("freelancerid",1))
    				   ,new BasicDBObject("$sort", new BasicDBObject("freelancerid",1))
    				   ));
       for (DBObject obj : resultantCollection.results()) {
           i = (int) obj.get("freelancerid");
       }
       return ++i;
	}
	
	public static int getUniqueGigID()
	{
		int i = 0;
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        
       AggregationOutput resultantCollection = mycollection.aggregate(
    		   Arrays.asList(
    				   new BasicDBObject("$unwind", "$gigs"),
    				   new BasicDBObject("$project", new BasicDBObject("gigID", "$gigs.gigId"))
    				   ,new BasicDBObject("$sort", new BasicDBObject("gigID",1))
    				   ));
       for (DBObject obj : resultantCollection.results()) {
           i =  (int) (double) obj.get("gigID");
       }
       return ++i;
	}
	
	public static int getUniquePackageID()
	{
		int i = 0;
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        
       AggregationOutput resultantCollection = mycollection.aggregate(
    		   Arrays.asList(
    				   new BasicDBObject("$unwind", "$gigs"),
    				   new BasicDBObject("$unwind", "$gigs.packages")
    				   ,new BasicDBObject("$project", new BasicDBObject("packageID", "$gigs.packages.packageID"))
    				   ,new BasicDBObject("$sort", new BasicDBObject("packageID",1))
    				   ));
       
       for (DBObject obj : resultantCollection.results()) {
           i =  (int) (double) obj.get("packageID");
       }
       return ++i;
	}
	
}
