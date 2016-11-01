package model.shapes;

import util.Point;

public class Square extends Polygon {

	public Square() {}
	public Square(Point topLeftPoint, Point topRightPoint,
			 Point bottomRightPoint, Point bottomLeftPoint) {
		super(topLeftPoint, topRightPoint, bottomRightPoint, bottomLeftPoint);
	}

	public Square(double topLeftX, double topLeftY,
				  double topRightX, double topRightY,
				  double bottomRightX, double bottomRightY,
				  double bottomLeftX, double bottomLeftY) {
		super(topLeftX, topLeftY, topRightX, topRightY, bottomRightX,
				bottomRightY, bottomLeftX, bottomLeftY);
	}
}
