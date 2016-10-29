package model;

import javafx.scene.paint.Paint;

public interface Shape {

	public javafx.scene.shape.Shape getDrawableShape();

	public void resize();

	public void move();

	public void delete();

	public void setStrokeColor(Paint value);

	public void setFillColor(Paint value);

	public void setStrokeWidth(double value);

	public Paint getStrokeColor();

	public Paint getFillColor();

	public double getStrokeWidth();
}
