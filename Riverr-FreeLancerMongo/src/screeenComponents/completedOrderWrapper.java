package screeenComponents;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class completedOrderWrapper extends VBox{
	private String gigName;
	private String customerName;
	private String packageType;
	private String orderDescription;
	private String working;
	public completedOrderWrapper(String gigName, String customerName, String packageType, String orderDescription, String working) {
		this.gigName = gigName;
		this.customerName = customerName;
		this.packageType = packageType;
		this.orderDescription = orderDescription;
		this.working = working;
		
		
		//Formatting//
		format();
	}
	
	private void format()
	{
		//Setting Up Data//
		Label name = new Label("Gig: " + this.gigName);
		Label fLName = new Label("FreeLancer : " + this.customerName);
		Label packageType = new Label("Package Type: " + this.packageType);
		Label orderDescription = new Label("Description: " + this.orderDescription);
		
		//Work Link
		this.getChildren().add(new Label("Work:"));
		TextArea workingArea = new TextArea(this.working);
		workingArea.setEditable(false);
		workingArea.setWrapText(true);
		// Joining things up
		this.getChildren().addAll(name, fLName, packageType, orderDescription, workingArea);

		//Format
		this.setWidth(800);
		BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT);
		this.setBorder(new Border(stroke));
		
	}
}
