package model;

import javafx.scene.paint.Paint;
import util.Point;

public class Triangle extends Polygon {

	public Triangle(Point pt1, Point pt2, Point pt3) {
		super(pt1, pt2, pt3);
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

	@Override
	public void setStrokeWidth(double value) {
		super.setStrokeWidth(value);
	}

	@Override
	public Paint getStrokeColor() {
		return super.getStrokeColor();
	}

	@Override
	public Paint getFillColor() {
		return super.getFillColor();
	}

	@Override
	public double getStrokeWidth() {
		return super.getStrokeWidth();
	}
}
