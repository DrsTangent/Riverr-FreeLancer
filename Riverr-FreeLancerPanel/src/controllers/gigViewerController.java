package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import DataManagment.dataRetrival;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import screeenComponents.loaders;
import screeenComponents.packageViewer;

public class gigViewerController {
	public static int gigID;
	@FXML
	Label gigTitle;
	@FXML
	Label freeLancerName;
	@FXML
	Label userName;
	@FXML
	Text description;
	@FXML
	HBox packageWindow;
	
	public void initialize()
	{
		ArrayList<Object> gig = dataRetrival.getGig(gigID);
		gigTitle.setText((String) gig.get(1));
		freeLancerName.setText((String) gig.get(4));
		userName.setText((String) gig.get(2));
		description.setText((String) gig.get(3));
		
		ArrayList<ArrayList<Object>> relatedPackages = dataRetrival.getPackages(gigID);
		for(int i = 0; i<relatedPackages.size(); i++)
		{
			ArrayList<Object> pkgInfo = relatedPackages.get(i);
			packageWindow.getChildren().add(new packageViewer(
					((BigDecimal) pkgInfo.get(0)).intValue(),
					(String) pkgInfo.get(1),
					(String) pkgInfo.get(3), 
					((BigDecimal) pkgInfo.get(2)).doubleValue()));
		}
	}
}
