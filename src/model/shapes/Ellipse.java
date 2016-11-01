package model.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Transform;
import model.Shape;

public class Ellipse implements Shape {

    private javafx.scene.shape.Ellipse ellipse;
    private double centerX, centerY, radiusX, radiusY;
    
    public Ellipse() {
        this.ellipse = new javafx.scene.shape.Ellipse();
    }

    public Ellipse(double centerX, double centerY, double radiusX, double radiusY) {
        this.ellipse = new javafx.scene.shape.Ellipse();
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterX() {
        return this.centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getCenterY() {
        return this.centerY;
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
    public void setFill(Paint value) {
        this.ellipse.setFill(value);
    }

    @Override
    public Paint getFill() {
        return this.ellipse.getFill();
    }

    @Override
    public void setStroke(Paint value) {
        this.ellipse.setStroke(value);
    }

    @Override
    public Paint getStroke() {
        return this.ellipse.getStroke();
    }

    @Override
    public void setStrokeWidth(double value) {
        this.ellipse.setStrokeWidth(value);
    }

    @Override
    public double getStrokeWidth() {
        return this.ellipse.getStrokeWidth();
    }

    @Override
    public void setCursor(Cursor value) {
        this.ellipse.setCursor(value);
    }

    @Override
	public Cursor getCursor() {
		return this.ellipse.getCursor();
	}

    @Override
    public EventHandler<? super MouseEvent> getOnMouseMoved() {
        return this.ellipse.getOnMouseMoved();
    }

    @Override
    public void setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        this.ellipse.setOnMouseMoved(value);
    }

    @Override
    public void setOnMousePressed(EventHandler<? super MouseEvent> value) {
        this.ellipse.setOnMousePressed(value);
    }

    @Override
    public void setOnMouseDragged(EventHandler<? super MouseEvent> value) {
        this.ellipse.setOnMouseDragged(value);
    }

    @Override
    public void setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        this.ellipse.setOnMouseReleased(value);
    }

    @Override
    public ObservableList<Transform> getTransforms() {
        return this.ellipse.getTransforms();
    }

    @Override
    public EventHandler<? super MouseEvent> getOnMousePressed() {
        return this.ellipse.getOnMousePressed();
    }

    @Override
    public EventHandler<? super MouseEvent> getOnMouseDragged() {
        return this.ellipse.getOnMouseDragged();
    }

    @Override
    public EventHandler<? super MouseEvent> getOnMouseReleased() {
        return this.ellipse.getOnMouseReleased();
    }

    @Override
    public ObjectProperty<Paint> fillProperty() {
        return this.ellipse.fillProperty();
    }

    @Override
    public ObjectProperty<Paint> StrokeProperty() {
        return this.ellipse.strokeProperty();
    }

    @Override
    public DoubleProperty StrokeWidthProperty() {
        return this.ellipse.strokeWidthProperty();
    }

	@Override
	public Shape getClone() {
		Ellipse clone = new Ellipse();
		clone.setCenterX(this.getCenterX());
		clone.setCenterY(this.getCenterY());
		clone.setFill(this.getFill());
		clone.setRadiusX(this.getRadiusX());
		clone.setRadiusY(this.getRadiusY());
		clone.setStroke(this.getStroke());
		clone.setCursor(this.getCursor());
		clone.setStrokeWidth(this.getStrokeWidth());
		clone.setOnMouseDragged(this.getOnMouseDragged());
		clone.setOnMouseMoved(this.getOnMouseMoved());
		clone.setOnMousePressed(this.getOnMousePressed());
		clone.setOnMouseReleased(this.getOnMouseReleased());
		return clone;
	}

	@Override
	public Node getNode() {
		return (Node)ellipse;
	}
}
