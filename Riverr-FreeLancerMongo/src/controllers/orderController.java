package controllers;

import DataManagment.dataEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import relations.User;
import screeenComponents.customDialogBox;
import screeenComponents.errorAlert;

public class orderController {
	private static int package_ID;
	private static String package_Type;
	
	private static customDialogBox dialog;
	
	private final static String title = "Order";
	private final static String fxml = "../layouts/order.fxml";
	
	@FXML
	Label packageType;
	@FXML
	TextArea descriptionField;
	
	public static void init(int packageID, String packageTitle)
	{
		package_ID = packageID;
		package_Type = packageTitle;
		
		dialog = new customDialogBox(title, fxml);
		
		//Showing Dialog Box
		dialog.showAndWait();
	}
	
	public void initialize()
	{
		packageType.setText(package_Type);
	}
	
	@FXML
	public void orderNow()
	{
		if(descriptionField.getText().isBlank())
		{
			errorAlert.showError("Description field is blank", "Enter some Description to Continue");
		}
		else 
		{
			dataEntry.addOrder(User.getUserName(), package_ID, descriptionField.getText());
			dialog.close();
		}
	}
}
