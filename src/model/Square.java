package model;

import javafx.scene.paint.Paint;
import util.Point;

public class Square extends Polygon {

	public Square(Point topLeftPoint, Point topRightPoint,
			 Point bottomLeftPoint, Point bottomRightPoint) {
		super(topLeftPoint, topRightPoint, bottomLeftPoint, bottomRightPoint);
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
