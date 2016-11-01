package model;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Transform;

public class Circle extends Ellipse {

	public Circle() {}

	public Circle(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius, radius);
	}

	public void setCenterX(double centerX) {
		super.setCenterX(centerX);
	}

	public double getCenterX() {
		return super.getCenterX();
	}

	public void setCenterY(double centerY) {
		super.setCenterY(centerY);
	}

	public double getCenterY() {
		return super.getCenterY();
	}

	public void setRadius(double radius) {
		super.setCenterX(radius);
		super.setCenterY(radius);
	}

	public double getRadius() {
		return super.getCenterX();
	}

	@Override
	public void setFill(Paint value) {
		super.setFill(value);;
	}

	@Override
	public Paint getFill() {
		return super.getFill();
	}

	@Override
	public void setStroke(Paint value) {
		super.setStroke(value);
	}

	@Override
	public Paint getStroke() {
		return super.getStroke();
	}

	@Override
	public void setStrokeWidth(double value) {
		super.setStrokeWidth(value);
	}

	@Override
	public double getStrokeWidth() {
		return super.getStrokeWidth();
	}

	@Override
	public void setCursor(Cursor value) {
		super.setCursor(value);
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMouseMoved() {
		return super.getOnMouseMoved();
	}

	@Override
	public void setOnMouseMoved(EventHandler<? super MouseEvent> value) {
		super.setOnMouseMoved(value);
	}

	@Override
	public void setOnMousePressed(EventHandler<? super MouseEvent> value) {
		super.setOnMousePressed(value);
	}

	@Override
	public void setOnMouseDragged(EventHandler<? super MouseEvent> value) {
		super.setOnMouseDragged(value);
	}

	@Override
	public void setOnMouseReleased(EventHandler<? super MouseEvent> value) {
		super.setOnMouseReleased(value);
	}

	@Override
	public ObservableList<Transform> getTransforms() {
		return super.getTransforms();
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMousePressed() {
		return super.getOnMousePressed();
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMouseDragged() {
		return super.getOnMouseDragged();
	}

	@Override
	public EventHandler<? super MouseEvent> getOnMouseReleased() {
		return super.getOnMouseReleased();
	}
}
