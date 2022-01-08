package screeenComponents;

import java.awt.Component;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class helperMethods {
	public static void setRegionBackground(Region region, Color color)
	{
		region.setBackground(new Background(new BackgroundFill(color, null, Insets.EMPTY)));
	}
	
	public static void onlyNumberTextField(TextField textField)
	{
		textField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            textField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	public static void setBorder(Region region)
	{
		BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT);
		region.setBorder(new Border(stroke));
	}
}
