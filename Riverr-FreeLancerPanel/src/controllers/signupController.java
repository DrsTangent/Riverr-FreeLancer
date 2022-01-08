package controllers;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import screeenComponents.errorAlert;
import DataManagment.dataEntry;
import application.Main;
import javafx.fxml.*;
public class signupController {
	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	TextField contactNum;
	@FXML
	TextField emailAddress;
	@FXML
	TextField userName;
	@FXML
	PasswordField password;
	@FXML
	PasswordField confirmPassword;
	@FXML
	VBox window;
	
	public void initialize()
	{
		Main.primaryStage.setMinHeight(window.getPrefHeight());
		Main.primaryStage.setMinWidth(window.getPrefWidth());
	}
	public void loginAccount()
	{
		Main.loadScene("../layouts/login.fxml");
	}
	public void signup()
	{
		if(isBlank())
		{
			errorAlert.showError("Madatory Fields are left blank", 
					"Username ,Password, Confirm Password, Email Address, FirstName Fields cannot be blank");
		}
		else if(!password.getText().equals(confirmPassword.getText()))
		{
			errorAlert.showError("Password is doesn't match", "Password and Confirm Password doesn't match");
		}
		else
		{
			try
			{
			dataEntry.addFreeLancer( firstName.getText(), 
					lastName.getText(), userName.getText(), contactNum.getText(),
					emailAddress.getText(), password.getText());
			Main.loadScene("../layouts/login.fxml");
			}
			catch(Exception e)
			{
				errorAlert.showError("Error", "Something went wrong");
			}
		}
	}
	private boolean isBlank()
	{
		return 	userName.getText().isBlank() || password.getText().isBlank() || 
				confirmPassword.getText().isBlank() || firstName.getText().isBlank() ||
				emailAddress.getText().isBlank();
	}
}
