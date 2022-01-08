package controllers;

import DataManagment.dataUpdate;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import screeenComponents.customDialogBox;
import screeenComponents.errorAlert;
import screeenComponents.loaders;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class editProfileController {
	final private static String title = "Riverr-Edit Profile";
	final private static String fxml = "../layouts/editprofile.fxml";
	
	private static customDialogBox dialog;
	
	public static void init()
	{
		dialog = new customDialogBox(title, fxml);
		
		//Showing Dialog Box
		dialog.showAndWait();
	}
	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	TextField contactNumber;
	@FXML
	TextField emailAddress;
	
	@FXML
	public void saveChanges()
	{
		if(firstName.getText().isBlank() || emailAddress.getText().isBlank())
		{
			errorAlert.showError("Fields are left blank", "Username / Password cannot be blank");
		}
		else if(confirmationController.init())
		{
			dataUpdate.updateUserData(firstName.getText(), lastName.getText(), 
					contactNumber.getText(), emailAddress.getText());
			dialog.close();
		}
		else
		{
			System.out.print("Error while updating");
		}
	}
}
