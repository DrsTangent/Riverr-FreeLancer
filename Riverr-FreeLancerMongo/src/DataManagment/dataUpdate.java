package DataManagment;

import relations.User;

public class dataUpdate {
	public static void main(String args[])
	{
		System.out.println("here");
		//Testing Update Client -- Status : PASS
		dataUpdate.updateUserData("Ali", "Hussain", 
				"03185556339", "alihussainpid@gmail.com");
		//
		
		System.out.println("here");
	}
	
	public static void updateUserData(String firstName, String lastName, String contact, String email)
	{
		String updateClientData = "UPDATE USERS SET FIRST_NAME = '"+firstName+"', LAST_NAME = '"+lastName+"', EMAIL = '"+email+"', CONTACTS = '"+contact+"' "
				+ "WHERE USERNAME = '"+User.getUserName()+"'";
		System.out.println(updateClientData);
		//DataConnection.updateQuery(updateClientData);
	}
	
	
}
