package model.shapes;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Transform;
import model.Shape;
import util.ConvertColor;
import util.ConvertShape;
import util.Point;

public class Line implements Shape {

  private javafx.scene.shape.Line line;

  public Line() {
    this.line = new javafx.scene.shape.Line();
  }

  public Line(Point pt1, Point pt2) {
    this.line.setStartX(pt1.getX());
    this.line.setStartY(pt1.getY());
    this.line.setEndX(pt2.getX());
    this.line.setEndY(pt2.getY());
  }

  public Line(double startX, double startY, double endX, double endY) {
    this.line.setStartX(startX);
    this.line.setStartY(startY);
    this.line.setEndX(endX);
    this.line.setEndY(endY);
  }

  public void setStartX(double startX) {
    this.line.setStartX(startX);
  }

  public double getStartX() {
    return this.line.getStartX();
  }

  public void setStartY(double startY) {
    this.line.setStartY(startY);
  }

  public double getStartY() {
    return this.line.getStartY();
  }

  public void setEndX(double endX) {
    this.line.setEndX(endX);
  }

  public double getEndX() {
    return this.line.getEndX();
  }

  public void setEndY(double endY) {
    this.line.setEndY(endY);
  }

  public double getEndY() {
    return this.line.getEndY();
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
    clone.getTransforms().addAll(this.getTransforms());
    return clone;
  }

  @Override
  public Cursor getCursor() {
    return this.line.getCursor();
  }

  @Override
  public Node getNode() {
    return this.line;
  }

  @Override
  public DoubleProperty scaleXProperty() {
    return this.line.scaleXProperty();
  }

  @Override
  public DoubleProperty scaleYProperty() {
    return this.line.scaleYProperty();
  }

  @Override
  public DoubleProperty translateXProperty() {
    return this.line.translateXProperty();
  }

  @Override
  public DoubleProperty translateYProperty() {
    return this.line.translateYProperty();
  }

  @Override
  public Element getXMLShape() {
    Element shape = DocumentHelper.createElement("line");
    shape.addAttribute("x1", String.valueOf(this.getStartX()));
    shape.addAttribute("y1", String.valueOf(this.getStartY()));
    shape.addAttribute("x2", String.valueOf(this.getEndX()));
    shape.addAttribute("y2", String.valueOf(this.getEndY()));

    shape.addAttribute("stroke-color", ConvertColor.toHex((Color) this.getStroke()));
    shape.addAttribute("stroke-width", String.valueOf(this.getStrokeWidth()));

    String transforms = ConvertShape.transformsToString(this.line);

    if (!transforms.isEmpty()) {
      shape.addAttribute("transform", transforms);
    }

    return shape;
  }

  @Override
  public Shape getShapeFromXML(Element element) {
    Line line = new Line();
    line.setStartX(Double.parseDouble(element.attributeValue("x1")));
    line.setStartY(Double.parseDouble(element.attributeValue("y1")));
    line.setEndX(Double.parseDouble(element.attributeValue("x2")));
    line.setEndY(Double.parseDouble(element.attributeValue("y2")));
    line.setStroke(Color.web(element.attributeValue("stroke-color")));
    line.setStrokeWidth(Double.parseDouble(element.attributeValue("stroke-width")));
    String transforms = element.attributeValue("transform");
    if (transforms != null) {
      line.getTransforms().addAll(ConvertShape.transformsFromString(transforms));
    }
    return line;
  }
}
