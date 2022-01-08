package screeenComponents;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public abstract class wrapper  extends HBox {
	
	public wrapper()
	{	
		//must perfom an action on getting clicked//
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->action());
		//out line//
		setBorder();
	}
	
	abstract public void action();
	
	private void setBorder()
	{
		BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT);
		this.setBorder(new Border(stroke));
	}
}
