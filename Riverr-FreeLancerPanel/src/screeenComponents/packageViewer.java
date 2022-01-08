package screeenComponents;

import controllers.orderController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class packageViewer extends VBox{
	private int packageID;
	private String packageType;
	private String packageDetails;
	private double packagePrice;
	
	public packageViewer(int packageID, String packageType, String packageDetails, double packagePrice)
	{
		this.packageID = packageID;
		this.packageType = packageType;
		this.packageDetails = packageDetails;
		this.packagePrice = packagePrice;
		
		format();
	}
	
	private void format()
	{
		/*Making Details*/
		Label pkgName = new Label(this.packageType);
		
		Label pkgPrice = new Label("Price: " + Double.toString(this.packagePrice));
		
		Label pkgDetails = new Label("Description:\n" + this.packageDetails);
		pkgDetails.setWrapText(true);
		
		this.setPrefHeight(300);
		
		/*Adding Details etc*/
		this.getChildren().addAll(pkgName, pkgPrice, pkgDetails);
		
		/*Alignment etc*/
		((HBox) this.getParent()).setHgrow(this, Priority.ALWAYS);
		this.setVgrow(pkgDetails, Priority.ALWAYS);
		this.setAlignment(Pos.TOP_CENTER);
		
		//Out Line
		helperMethods.setBorder(this);
	}
}
