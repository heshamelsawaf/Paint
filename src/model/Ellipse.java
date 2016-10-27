package model;

import javafx.scene.paint.Paint;
import util.Point;

public class Ellipse implements Shape {

	private double radiusX, radiusY;
	private Point center;
	private javafx.scene.shape.Ellipse ellipse;

	public Ellipse(Point center, double radiusX, double radiusY) {
		ellipse = new javafx.scene.shape.Ellipse();
		ellipse.setStroke(Paint.valueOf("BLACK"));
		ellipse.setFill(Paint.valueOf("WHITE"));
		this.center = center;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public Point getCenter() {
		return this.center;
	}

	public void setRadiusX(double radiusX) {
		this.radiusX = radiusX;
	}

	public double getRadiusX() {
		return this.radiusX;
	}

	public void setRadiusY(double radiusY) {
		this.radiusY = radiusY;
	}

	public double getRadiusY() {
		return this.radiusY;
	}

	@Override
	public javafx.scene.shape.Shape getDrawableShape() {
		ellipse.setCenterX(center.getX());
		ellipse.setCenterY(center.getY());
		ellipse.setRadiusX(radiusX);
		ellipse.setRadiusY(radiusY);
		return ellipse;
	}

	@Override
	public void setStrokeColor(Paint value) {
		ellipse.setStroke(value);
	}

	@Override
	public void setFillColor(Paint value) {
		ellipse.setFill(value);
	}

	@Override
	public void setStrokeWidth(double value) {
		ellipse.setStrokeWidth(value);
	}

	@Override
	public Paint getStrokeColor() {
		return ellipse.getStroke();
	}

	@Override
	public Paint getFillColor() {
		return ellipse.getFill();
	}

	@Override
	public double getStrokeWidth() {
		return ellipse.getStrokeWidth();
	}
}
