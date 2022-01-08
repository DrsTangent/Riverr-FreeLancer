package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import relations.User;
import screeenComponents.customDialogBox;
import screeenComponents.errorAlert;

public class confirmationController {
	
	final static String title = "Riverr-Action Confirmation";
	final static String fxml = "../layouts/confirmation.fxml";
	private static boolean confirm;
	
	private static customDialogBox dialog;
	
	public static boolean init()
	{
		confirm = false;
		
		dialog = new customDialogBox(title, fxml);
		
		//Showing Dialog Box
		dialog.showAndWait();
		
		//return the confirmation.
		return confirm;
	}
	
	@FXML
	PasswordField passField;
	
	@FXML
	public void imSure()
	{
		if(User.confirmAction(passField.getText()))
		{
			confirm = true;
			dialog.close();
		}
		else
		{
			errorAlert.showError("Password is incorrect", "Incorrect password, please insert the correct password");
		}
	}
}
