package model;

import java.util.ArrayList;

import javafx.scene.paint.Paint;
import util.Point;

public class Polygon implements Shape {

	private ArrayList<Double> polygonVertices;
	private javafx.scene.shape.Polygon shape;

	public Polygon(Point... vertices) {
		polygonVertices = new ArrayList<Double>();
		shape = new javafx.scene.shape.Polygon();
		shape.setStroke(Paint.valueOf("BLACK"));
		shape.setFill(Paint.valueOf("WHITE"));
		for (Point point : vertices) {
			polygonVertices.add(point.getX());
			polygonVertices.add(point.getY());
		}
	}

	public Polygon(double... vertices) {
		polygonVertices = new ArrayList<Double>();
		shape = new javafx.scene.shape.Polygon();
		shape.setStroke(Paint.valueOf("BLACK"));
		shape.setFill(Paint.valueOf("WHITE"));
		for (double vertex : vertices) {
			polygonVertices.add(vertex);
		}
	}

	@Override
	public javafx.scene.shape.Shape getDrawableShape() {
		Double[] pointsToDraw = new Double[polygonVertices.size()];
		pointsToDraw = polygonVertices.toArray(pointsToDraw);
		shape.getPoints().addAll(pointsToDraw);
		return shape;
	}

	@Override
	public void setStrokeColor(Paint value) {
		shape.setStroke(value);
	}

	@Override
	public void setFillColor(Paint value) {
		shape.setFill(value);
	}

	@Override
	public void setStrokeWidth(double value) {
		shape.setStrokeWidth(value);
	}

	@Override
	public Paint getStrokeColor() {
		return shape.getStroke();
	}

	@Override
	public Paint getFillColor() {
		return shape.getFill();
	}

	@Override
	public double getStrokeWidth() {
		return shape.getStrokeWidth();
	}
}
