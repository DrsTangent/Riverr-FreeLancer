package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import relations.User;
import screeenComponents.errorAlert;

public class loginController {
	@FXML
	TextField usernameField;
	@FXML
	TextField passwordField;
	@FXML
	VBox window;
	
	public void initialize()
	{
		Main.primaryStage.setMinHeight(window.getPrefHeight());
		Main.primaryStage.setMinWidth(window.getPrefWidth());
	}
	public void loginACC()
	{
		if(usernameField.getText().isBlank() || passwordField.getText().isBlank())
		{
			errorAlert.showError("Fields are left blank", "Username / Password cannot be blank");
		}
		else if(User.login(usernameField.getText(), passwordField.getText()))
		{
			Main.loadScene("../layouts/userPanel.fxml");
		}
		else
		{
			errorAlert.showError("Password is incorrect", "Incorrect password");
		}
	}
	public void signUp()
	{
		Main.loadScene("../layouts/signup.fxml");
	}
}
