package DataManagment;

public class dataDeletion {
	public static void deletePendingOrder(String clientUserName, int packageID, String orderDescrip)
	{
		String deletePendingOrder = "DELETE FROM ORDERS "
				+ "WHERE client_username = '"+clientUserName+"' "
				+ "and orderdescrip = '"+orderDescrip+"' "
				+ "and packages_packages_id = " + packageID; 
		//DataConnection.updateQuery(deletePendingOrder);
	}
}
