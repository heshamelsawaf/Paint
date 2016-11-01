package model;

import javafx.scene.paint.Paint;
import util.Point;

public class Rectangle extends Polygon {

  private boolean highlight = false;
  private javafx.scene.shape.Rectangle drawableRectangle;

  public Rectangle(Point topLeftPoint, Point topRightPoint, Point bottomLeftPoint,
      Point bottomRightPoint) {
    super(topLeftPoint, topRightPoint, bottomLeftPoint, bottomRightPoint);
  }

  public Rectangle() {
    super(new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0));
  }

  @Override
  public javafx.scene.shape.Shape getDrawableShape() {
	  if (this.drawableRectangle == null) {
		  drawableRectangle = new javafx.scene.shape.Rectangle(); 
		  return drawableRectangle;
	  } else {
		  return this.drawableRectangle;
	  }
  }

  @Override
  public void setDrawableShape(javafx.scene.shape.Shape shape) {
    this.drawableRetangle = (javafx.scene.shape.Rectangle)shape;
  }

  @Override
  public void setStrokeColor(Paint value) {
    super.setStrokeColor(value);
  }

  @Override
  public void setFillColor(Paint value) {
    super.setFillColor(value);
  }

  @Override
  public void setStrokeWidth(double value) {
    super.setStrokeWidth(value);
  }

  @Override
  public Paint getStrokeColor() {
    return super.getStrokeColor();
  }

  @Override
  public Paint getFillColor() {
    return super.getFillColor();
  }

  @Override
  public double getStrokeWidth() {
    return super.getStrokeWidth();
  }

  public void setHighlighted(boolean highlight) {
    this.highlight = highlight;
  }

  public boolean isHighlighted() {
    return this.highlight;
  }
}
