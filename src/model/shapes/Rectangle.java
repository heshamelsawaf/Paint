package model.shapes;

import util.Point;

public class Rectangle extends Polygon {

  private boolean highlight;

  public Rectangle(Point topLeftPoint, Point topRightPoint, Point bottomLeftPoint,
      Point bottomRightPoint) {
    super(topLeftPoint, topRightPoint, bottomLeftPoint, bottomRightPoint);
    this.highlight = false;
  }

  public Rectangle() {
    this.highlight = false;
  }

  public void setHighlighted(boolean highlight) {
    this.highlight = highlight;
  }

  public boolean isHighlighted() {
    return this.highlight;
  }

}
