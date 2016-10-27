package model;

import javafx.scene.paint.Paint;
import util.Point;

public class Line extends Polygon {

	public Line(Point pt1, Point pt2) {
		super(pt1, pt2);
	}

	@Override
	public javafx.scene.shape.Shape getDrawableShape() {
		return super.getDrawableShape();
	}

	@Override
	public void setStrokeColor(Paint value) {
		super.setStrokeColor(value);
	}
}
