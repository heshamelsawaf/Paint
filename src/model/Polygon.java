package model;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Transform;
import util.Point;

public class Polygon implements Shape {

	private ArrayList<Double> polygonVertices;
	private javafx.scene.shape.Shape shape;

	public Polygon() {
		this.shape = new javafx.scene.shape.Polygon();
	}

	public Polygon(Point... vertices) {
		this.polygonVertices = new ArrayList<Double>();
		this.shape = new javafx.scene.shape.Polygon();
		for (Point point : vertices) {
			polygonVertices.add(point.getX());
			polygonVertices.add(point.getY());
		}
	}

	public Polygon(double... vertices) {
		this.polygonVertices = new ArrayList<Double>();
		this.shape = new javafx.scene.shape.Polygon();
		for (double vertex : vertices) {
			this.polygonVertices.add(vertex);
		}
	}

	public void addPoint(double x, double y) {
		this.polygonVertices.add(x);
		this.polygonVertices.add(y);
	}

	@Override
	public void setFill(Paint value) {
		this.shape.setFill(value);
	}

	@Override
	public Paint getFill() {
		return this.shape.getFill();
	}

	@Override
	public void setStroke(Paint value) {
		this.shape.setStroke(value);
	}

	@Override
	public Paint getStroke() {
		return this.shape.getStroke();
	}

	@Override
	public void setStrokeWidth(double value) {
		this.shape.setStrokeWidth(value);
	}

	@Override
	public double getStrokeWidth() {
		return this.shape.getStrokeWidth();
	}

	@Override
	public void setCursor(Cursor value) {
		this.shape.setCursor(value);
	}

	@Override
	public void setOnMouseMoved(EventHandler<? super MouseEvent> value) {
		this.shape.setOnMouseMoved(value);
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMouseMoved() {
		return this.shape.getOnMouseMoved();
	}

	@Override
	public void setOnMousePressed(EventHandler<? super MouseEvent> value) {
		this.shape.setOnMousePressed(value);
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMousePressed() {
		return this.shape.getOnMousePressed();
	}

	@Override
	public void setOnMouseDragged(EventHandler<? super MouseEvent> value) {
		this.shape.setOnMouseDragged(value);
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMouseDragged() {
		return this.shape.getOnMouseDragged();
	}

	@Override
	public void setOnMouseReleased(EventHandler<? super MouseEvent> value) {
		this.shape.setOnMouseReleased(value);
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMouseReleased() {
		return this.shape.getOnMouseReleased();
	}

	@Override
	public ObservableList<Transform> getTransforms() {
		return this.shape.getTransforms();
	}
}
