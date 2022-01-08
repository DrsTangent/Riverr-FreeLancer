package screeenComponents;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class customDialogBox extends Dialog{
	public customDialogBox(String title, String fxml)
	{
		super();
		//Loading Data in it
		this.setTitle(title);
		Parent loadEditProfile = loaders.loadScene(fxml);
		this.getDialogPane().setContent(loadEditProfile);
		//Adding Close Button//
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);//Add Close Button Functionality
		Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);//look for Close button at bottom
		closeButton.setVisible(false);//Delete the Button at the Bottom.
	}
}
