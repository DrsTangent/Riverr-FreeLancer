package DataManagment;

public class dataEntry {

	public static void main(String args[])
	{
		//Testing adding Client -- status: Sucessful
		//addClient("Abdul", "Hadi", "ffds", "sdffs", "sdfs@gmail.com", "sdf");
		
		//Testing adding Free Lancer --status: Sucessful
		addFreeLancer("Ahmed", "Ali", "EscanorTHEONE", "03165568686", "atthepinnacle@gmail.com", "Talagang122");
	}
	
	/* Adding Client */
	public static  void addClient( String firstName, 
			String lastName, String userName, String contact,
			String email, String password)
	{
		addUser(firstName, lastName, userName, contact, email, password, "client");
		String insertClientQuery = "INSERT INTO Riverr_db.CLIENT (CLIENT_ID, USERNAME) VALUES (CLIENTIDSEQ.nextval, '"+userName+"')";
		DataConnection.updateQuery(insertClientQuery);
		saveChanges();
	}
	
	/*Adding Free Lancer*/
	public static  void addFreeLancer(String firstName, 
			String lastName, String userName, String contact,
			String email, String password)
	{
		addUser(firstName, lastName, userName, contact, email, password, "Free Lancer");
		String insertClientQuery = "INSERT INTO Riverr_db.FREE_LANCER (FREELANCERID, USERNAME) VALUES (FREELANCERID_SEQ.nextval, '"+userName+"')";
		DataConnection.updateQuery(insertClientQuery);
		saveChanges();
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
		DataConnection.updateQuery(insertUserQuery);
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
		String insertPackageQuery = "INSERT INTO \"RIVERR_DB\".\"PACKAGES\" (PRICE, DETAILED_DESCRIPTION, GIGS_ID, PACKAGETYPES_PACKAGETYPE, PACKAGES_ID) "
				+ "VALUES ('"+price+"', '"+Description+"', '"+gigID+"', '"+packageType+"', '"+packageID+"')";
		DataConnection.updateQuery(insertPackageQuery);
	}
	
	/*Adding Gigs*/
	public static void addGig(int gigID, String title, String description, int freeLancerID, String status)
	{
		//Inserting Gig//
		String insertGig = "INSERT INTO \"RIVERR_DB\".\"GIGS\" (ID, TITLE, DESCRIPTION, FREE_LANCER_FREELANCERID, STATUS)"
				+ " VALUES ('"+gigID+"', '"+title+"', '"+description+"', '"+freeLancerID+"', '"+status+"')";
		DataConnection.updateQuery(insertGig);
	}
	
	/*Adding Order*/
	public static void addOrder(String clientUserName, int packageID, String orderDescrip)
	{
		String insertOrder = "INSERT INTO \"RIVERR_DB\".\"ORDERS\" (CLIENT_USERNAME, PACKAGES_PACKAGES_ID, ORDERDESCRIP) "
				+ "VALUES ('"+clientUserName+"', '"+packageID+"', '"+orderDescrip+"')";
		DataConnection.updateQuery(insertOrder);
	}
	
	/*Adding Completed Orders*/
	public static void addCompletedOrder(String clientUserName, int packageID, String orderDescrip, String Working)
	{
		String insertOrder = "INSERT INTO \"RIVERR_DB\".\"COMPLETEORDERS\" (CLIENT_USERNAME, PACKAGES_PACKAGES_ID, ORDERDESCRIP, WORKLINK) "
				+ "VALUES ('"+clientUserName+"', '"+packageID+"', '"+orderDescrip+"', '"+Working+"')";
		DataConnection.updateQuery(insertOrder);
	}
	
	
	/*Saving Changes*/
	private static void saveChanges()
	{
		DataConnection.updateQuery("COMMIT");
	}
}
