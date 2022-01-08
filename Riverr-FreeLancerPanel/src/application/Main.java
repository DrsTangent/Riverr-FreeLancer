package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screeenComponents.loaders;

public class Main extends Application {
	public static Stage primaryStage;
	public static void loadScene(String fxml){
	    Parent pane;
		pane = loaders.loadScene(fxml);
		Scene scene = new Scene( pane );
	    primaryStage.setScene(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		try {
			Parent root =  loaders.loadScene("../layouts/login.fxml");
			Scene scene = new Scene(root);
			Main.primaryStage.setTitle("Riverr - Free Lancer Panel");
			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}