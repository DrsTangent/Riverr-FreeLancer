package controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import screeenComponents.loaders;

public class packageController {
	
	@FXML
	Label type;
	@FXML
	Label price;
	@FXML
	Label time;
	@FXML
	Text packageDescription;

	public Parent makePackage(String type, String price, String deatiledDescription)
	{
		Parent loadPackage = loaders.loadScene("../layouts/packageViewer.fxml");
		this.type.setText(type);
		this.price.setText(price);
		this.packageDescription.setText(deatiledDescription);
		
		return loadPackage;
	}
	
	public void initialize()
	{
		System.out.print("Checkin");
	}
	@FXML
	public void orderPackage()
	{
		
	}
}
