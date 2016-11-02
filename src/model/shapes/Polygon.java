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

public class Polygon implements Shape {

  private javafx.scene.shape.Polygon shape;

  public Polygon() {
    this.shape = new javafx.scene.shape.Polygon();
  }

  public Polygon(Point... vertices) {
    this.shape = new javafx.scene.shape.Polygon();
    for (Point point : vertices) {
      this.shape.getPoints().add(point.getX());
      this.shape.getPoints().add(point.getY());
    }
  }

  public Polygon(double... vertices) {
    this.shape = new javafx.scene.shape.Polygon();
    for (double vertex : vertices) {
      this.shape.getPoints().add(vertex);
    }
  }

  public ObservableList<Double> getStrokeDashArray() {
    return this.shape.getStrokeDashArray();
  }

  public void addPoint(double x, double y) {
    this.shape.getPoints().add(x);
    this.shape.getPoints().add(y);
  }

  public ObservableList<Double> getPoints() {
    return this.shape.getPoints();
  }

  public void addCoordinate(double coordinate) {
    this.shape.getPoints().add(coordinate);
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

  @Override
  public ObjectProperty<Paint> fillProperty() {
    return this.shape.fillProperty();
  }

  @Override
  public ObjectProperty<Paint> StrokeProperty() {
    return this.shape.strokeProperty();
  }

  @Override
  public DoubleProperty StrokeWidthProperty() {
    return this.shape.strokeWidthProperty();
  }

  @Override
  public Shape getClone() {
    Polygon clone = new Polygon();
    for (double coordinate : this.shape.getPoints()) {
      clone.addCoordinate(coordinate);
    }
    clone.setFill(this.getFill());
    clone.setStroke(this.getStroke());
    clone.setCursor(this.getCursor());
    clone.setStrokeWidth(this.getStrokeWidth());
    clone.setOnMouseDragged(this.getOnMouseDragged());
    clone.setOnMouseMoved(this.getOnMouseMoved());
    clone.setOnMousePressed(this.getOnMousePressed());
    clone.setOnMouseReleased(this.getOnMouseReleased());
    clone.getTransforms().addAll(this.getTransforms());
    return clone;
  }

  @Override
  public Cursor getCursor() {
    return this.shape.getCursor();
  }

  @Override
  public Node getNode() {
    return this.shape;
  }

  @Override
  public DoubleProperty scaleXProperty() {
    return this.shape.scaleXProperty();
  }

  @Override
  public DoubleProperty scaleYProperty() {
    return this.shape.scaleYProperty();
  }

  @Override
  public DoubleProperty translateXProperty() {
    return this.shape.translateXProperty();
  }

  @Override
  public DoubleProperty translateYProperty() {
    return this.shape.translateYProperty();
  }
}
