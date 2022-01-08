package screeenComponents;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class errorAlert {

	public static void showError(String title, String data)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(data);

		alert.showAndWait();
	}

}
