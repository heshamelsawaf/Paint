package model;

import javafx.scene.paint.Paint;

public interface Shape {

	public javafx.scene.shape.Shape getDrawableShape();

	public void setStrokeColor(Paint value);

	public void setFillColor(Paint value);
}
