package model.shapes;

import util.Point;

public class Triangle extends Polygon {

	public Triangle() {}

	public Triangle(Point pt1, Point pt2, Point pt3) {
		super(pt1, pt2, pt3);
	}

	public Triangle(double pt1X, double pt1Y, 
					double pt2X, double pt2Y,
					double pt3X, double pt3Y) {
		super(pt1X, pt1Y, pt2X, pt2Y, pt3X, pt3Y);
	}
}
