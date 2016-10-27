package model;

import javafx.scene.paint.Paint;
import util.Point;

public class Circle extends Ellipse {

	public Circle(Point center, double radius) {
		super(center, radius, radius);
	}

	public void setCenter(Point center) {
		super.setCenter(center);
	}


	public void setRadius(double radius) {
		super.setRadiusX(radius);
		super.setRadiusY(radius);
	}

	@Override
	public javafx.scene.shape.Shape getDrawableShape() {
		return super.getDrawableShape();
	}

	@Override
	public void setStrokeColor(Paint value) {
		super.setStrokeColor(value);
	}

	@Override
	public void setFillColor(Paint value) {
		super.setFillColor(value);
	}
}
