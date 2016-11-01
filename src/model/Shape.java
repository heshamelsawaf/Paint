package model;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Transform;

public interface Shape {

	public void setFill(Paint value);

	public Paint getFill();

	public void setStroke(Paint value);
	
	public Paint getStroke();

	public void setStrokeWidth(double value);

	public double getStrokeWidth();

	public void setCursor(Cursor value);

	public void setOnMouseMoved(EventHandler<? super MouseEvent> value);

	public EventHandler<? super MouseEvent> getOnMouseMoved();

	public void setOnMousePressed(EventHandler<? super MouseEvent> value);

	public EventHandler<? super MouseEvent> getOnMousePressed();

	public void setOnMouseDragged(EventHandler<? super MouseEvent> value);

	public EventHandler<? super MouseEvent> getOnMouseDragged();

	public void setOnMouseReleased(EventHandler<? super MouseEvent> value);

	public EventHandler<? super MouseEvent> getOnMouseReleased();

	public ObservableList<Transform> getTransforms();
}
