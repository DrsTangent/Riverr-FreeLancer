package screeenComponents;

import DataManagment.dataDeletion;
import DataManagment.dataEntry;
import controllers.userPanelController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class pendingOrderWrapper extends VBox {
	private int packageID;
	private String gigName;
	private String customerName;
	private String packageType;
	private String orderDescription;
	private Button completeOrder;
	TextArea workData;
	
	public pendingOrderWrapper(int packageID,String gigName, String packageType, String customerName, String orderDescription)
	{
		this.packageID = packageID;
		this.gigName = gigName;
		this.customerName = customerName;
		this.packageType = packageType;
		this.orderDescription = orderDescription;
		
		
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
		Label workLabel = new Label("Enter Work:");
		workData = new TextArea();
		completeOrder = new Button("Complete Order");
		//Working Data
		completeOrder.setOnAction(e -> completeOrder());
		// Joining things up
		this.getChildren().addAll(name, fLName, packageType, orderDescription, workLabel, workData, completeOrder);

		//Format
		this.setWidth(800);
		BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT);
		this.setBorder(new Border(stroke));
		
	}
	public boolean completeOrder()
	{
		if(workData.getText().isEmpty())
		{
			errorAlert.showError("Work Link is Empty", "Please Provide Working Data or Link");
			return false;
		}
		else
		{
			dataDeletion.deletePendingOrder(this.customerName, this.packageID, this.orderDescription);//Delete Data from Pending Orders//
			dataEntry.addCompletedOrder(this.customerName, this.packageID, this.orderDescription, workData.getText());
			return true;
		}
	}
	
	public Button getCompleteButton()
	{
		return completeOrder;
	}
}
