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
import util.Point;

public class Line implements Shape {

    private javafx.scene.shape.Line line;
    private double startX, startY, endX, endY;

    public Line() {
        this.line = new javafx.scene.shape.Line();
    }

    public Line(Point pt1, Point pt2) {
        this.startX = pt1.getX();
        this.startY = pt1.getY();
        this.endX = pt2.getX();
        this.endY = pt2.getY();
    }

    public Line(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartX() {
        return this.startX;
    }
    
    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getStartY() {
        return this.startY;
    }
    
    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndX() {
        return this.endX;
    }
    
    public void setEndY(double endY) {
        this.endY = endY;
    }

    public double getEndY() {
        return this.endY;
    }

    @Override
    public void setFill(Paint value) {
        this.line.setFill(value);
    }

    @Override
    public Paint getFill() {
        return this.line.getFill();
    }

    @Override
    public void setStroke(Paint value) {
        this.line.setStroke(value);
    }

    @Override
    public Paint getStroke() {
        return this.line.getStroke();
    }

    @Override
    public void setStrokeWidth(double value) {
        this.line.setStrokeWidth(value);
    }

    @Override
    public double getStrokeWidth() {
        return this.line.getStrokeWidth();
    }

    @Override
    public void setCursor(Cursor value) {
        this.line.setCursor(value);
    }

    @Override
    public void setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        this.line.setOnMouseMoved(value);
    }

    @Override
    public EventHandler<? super MouseEvent> getOnMouseMoved() {
        return this.line.getOnMouseMoved();
    }

    @Override
    public void setOnMousePressed(EventHandler<? super MouseEvent> value) {
        this.line.setOnMousePressed(value);
    }

    @Override
    public EventHandler<? super MouseEvent> getOnMousePressed() {
        return this.line.getOnMousePressed();
    }

    @Override
    public void setOnMouseDragged(EventHandler<? super MouseEvent> value) {
        this.line.setOnMouseDragged(value);;
    }

    @Override
    public EventHandler<? super MouseEvent> getOnMouseDragged() {
        return this.line.getOnMouseDragged();
    }

    @Override
    public void setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        this.line.setOnMouseReleased(value);
    }

    @Override
    public EventHandler<? super MouseEvent> getOnMouseReleased() {
        return this.line.getOnMouseReleased();
    }

    @Override
    public ObservableList<Transform> getTransforms() {
        return this.line.getTransforms();
    }

    @Override
    public ObjectProperty<Paint> fillProperty() {
        return this.line.fillProperty();
    }

    @Override
    public ObjectProperty<Paint> StrokeProperty() {
        return this.line.strokeProperty();
    }

    @Override
    public DoubleProperty StrokeWidthProperty() {
        return this.line.strokeWidthProperty();
    }

	@Override
	public Shape getClone() {
		Line clone = new Line();
		clone.setStartX(this.getStartX());
		clone.setStartY(this.getStartY());
		clone.setEndX(this.getEndX());
		clone.setEndY(this.getEndY());
		clone.setFill(this.getFill());
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
	public Cursor getCursor() {
		return this.line.getCursor();
	}

	@Override
	public Node getNode() {
		return (Node)line;
	}
}
