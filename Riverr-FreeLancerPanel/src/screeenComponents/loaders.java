package screeenComponents;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class loaders {
	public static Parent loadScene(String fxml){
	    Parent pane = null;
		try {
			pane = FXMLLoader.load(Main.class.getResource(fxml));
		}
		catch (Exception e)
		{
			pane = new VBox();
			((VBox) pane).getChildren().add(new Label("Coudn't Load FXML"));
			e.printStackTrace();
		}
		
		return pane;
	}
}
