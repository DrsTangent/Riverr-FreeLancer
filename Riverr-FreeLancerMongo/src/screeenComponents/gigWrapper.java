package screeenComponents;

import controllers.gigViewerController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class gigWrapper extends wrapper {
	private int gigID;
	private String gigName;
	private String gigDescrip;
	
	public gigWrapper(int gigID, String gigName, String gigDes)
	{
		this.setHeight(40);
		this.gigID = gigID;
		this.gigName = gigName;
		this.gigDescrip = gigDes;
		
		//Formatting//
		format();
	}
	
	public void action()
	{
		gigViewerController.gigID = gigID;
		
		customDialogBox viewGig = new customDialogBox(gigName, "../layouts/gigviewer.fxml");
		
		viewGig.showAndWait();
	}
	
	private void format()
	{
		//Setting Up Data//
		Label name = new Label(gigName);
		Label gigDesp = new Label(gigDescrip);

		VBox detailsContainer = new VBox();
		HBox namesContainer = new HBox();

		// Joining things up
		namesContainer.getChildren().addAll(name);
		detailsContainer.getChildren().addAll(namesContainer, gigDesp);
		this.getChildren().addAll(detailsContainer);
		//Format
		name.setPadding(new Insets(0,30,0,0));
		this.setHgrow(detailsContainer, Priority.ALWAYS);
	}
	
}
