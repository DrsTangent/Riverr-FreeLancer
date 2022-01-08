package DataManagment;

import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class dataEntry {

	public static void main(String args[])
	{
		addFreeLancer("Escanor", 
				"The One", "atThePinacleOfAll", "03185556339",
				"escanorTheOne@gmail.com", "Talagang234");
	}
	
	/* Adding Client */
	public static  void addClient( String firstName, 
			String lastName, String userName, String contact,
			String email, String password)
	{
		 MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
         DB mydb = mongoclient.getDB("RiverrDB");
         DBCollection mycollection = mydb.getCollection("Clients");
         
	}
	
	/*Adding Free Lancer*/
	public static  void addFreeLancer(String firstName, 
			String lastName, String userName, String contact,
			String email, String password)
	{
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        List<Object> gigs = Arrays.asList();
        List<Object> degrees = Arrays.asList();
        List<Object> skills = Arrays.asList();
        
        DBObject freeLancer = new BasicDBObject()
        		.append("freelancerid", dataRetrival.getFreeLancerID())
        		.append("first_name", firstName)
        		.append("last_name", lastName)
        		.append("username", userName)
        		.append("contacts", contact)
        		.append("email", email)
        		.append("password", password)
        		.append("skills", skills)
        		.append("degrees", degrees)
        		.append("gigs", gigs);
        
       mycollection.insert(freeLancer);
	}
	
	/*Adding User*/
	private static void addUser(String firstName, 
			String lastName, String userName, String contact,
			String email, String password, String status)
	{
		//Inserting new user
		String insertUserQuery = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, CONTACTS, EMAIL, PASSWORD, STATUS)"
				+ "VALUES ('"+firstName+"', '"+lastName+"', '"+userName+"',"
				+ " '"+contact+"', '"+email+"', '"+password+"', '"+status+"')";
		//DataConnection.updateQuery(insertUserQuery);
	}
	
	/*Types of Packages*/
	public static void basicPackage(int packageID, int gigID, String Description,double price)
	{
		addPackage(packageID, gigID, "Basic", Description, price);
	}
	public static void standardPackage(int packageID, int gigID, String Description,double price)
	{
		addPackage(packageID, gigID, "Standard", Description, price);
	}
	public static void premiumPackage(int packageID, int gigID, String Description,double price)
	{
		addPackage(packageID, gigID, "Premium", Description, price);
	}
	
	/*Adding Package*/
	private static void addPackage(int packageID, int gigID, String packageType, String Description,double price)
	{
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        mycollection.update(new BasicDBObject("freelancerid",freeLancerID), 
        		new BasicDBObject("$push", new BasicDBObject("gigs", 
        				new BasicDBObject("gigId", gigID)
        				.append("title", title)
        				.append("description", description)
        				.append("packages", Arrays.asList()))));
		//DataConnection.updateQuery(insertPackageQuery);
	}
	
	/*Adding Gigs*/
	public static void addGig(int gigID, String title, String description, int freeLancerID, String status)
	{
		/*
		 * db.FreeLancers.updateOne({"freelancerid":2}, 
		 * {$push:{"gigs":{"gigId":2, "title": "Article Writing", 
		 * "description":"I will make an article for you", "packages":[]}}});
		 */
		//Inserting Gig//
		MongoClient mongoclient = new MongoClient( new MongoClientURI("mongodb://localhost:27017"));
        DB mydb = mongoclient.getDB("RiverrDB");
        DBCollection mycollection = mydb.getCollection("FreeLancers");
        mycollection.update(new BasicDBObject("freelancerid",freeLancerID), 
        		new BasicDBObject("$push", new BasicDBObject("gigs", 
        				new BasicDBObject("gigId", gigID)
        				.append("title", title)
        				.append("description", description)
        				.append("packages", Arrays.asList()))));
	}
	
	/*Adding Order*/
	public static void addOrder(String clientUserName, int packageID, String orderDescrip)
	{
		String insertOrder = "INSERT INTO \"RIVERR_DB\".\"ORDERS\" (CLIENT_USERNAME, PACKAGES_PACKAGES_ID, ORDERDESCRIP) "
				+ "VALUES ('"+clientUserName+"', '"+packageID+"', '"+orderDescrip+"')";
		//DataConnection.updateQuery(insertOrder);
	}
	
	/*Adding Completed Orders*/
	public static void addCompletedOrder(String clientUserName, int packageID, String orderDescrip, String Working)
	{
		String insertOrder = "INSERT INTO \"RIVERR_DB\".\"COMPLETEORDERS\" (CLIENT_USERNAME, PACKAGES_PACKAGES_ID, ORDERDESCRIP, WORKLINK) "
				+ "VALUES ('"+clientUserName+"', '"+packageID+"', '"+orderDescrip+"', '"+Working+"')";
		//DataConnection.updateQuery(insertOrder);
	}
}
