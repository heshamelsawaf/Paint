package model.shapes;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javafx.scene.paint.Color;
import model.Shape;
import util.ConvertColor;
import util.ConvertShape;
import util.Point;

public class Square extends Polygon {

  public Square() {}

  public Square(Point topLeftPoint, Point topRightPoint, Point bottomRightPoint,
      Point bottomLeftPoint) {
    super(topLeftPoint, topRightPoint, bottomRightPoint, bottomLeftPoint);
  }

  public Square(double topLeftX, double topLeftY, double topRightX, double topRightY,
      double bottomRightX, double bottomRightY, double bottomLeftX, double bottomLeftY) {
    super(topLeftX, topLeftY, topRightX, topRightY, bottomRightX, bottomRightY, bottomLeftX,
        bottomLeftY);
  }

  @Override
  public Element getXMLShape() {
    Element shape = DocumentHelper.createElement("square");
    double width = Math.abs(this.shape.getPoints().get(2) - this.shape.getPoints().get(4));
    double height = Math.abs(this.shape.getPoints().get(1) - this.shape.getPoints().get(3));
    shape.addAttribute("width", String.valueOf(width));
    shape.addAttribute("height", String.valueOf(height));
    shape.addAttribute("x", String.valueOf(this.shape.getPoints().get(0)));
    shape.addAttribute("y", String.valueOf(this.shape.getPoints().get(1)));

    shape.addAttribute("fill-color", ConvertColor.toHex((Color) this.getFill()));
    shape.addAttribute("stroke-color", ConvertColor.toHex((Color) this.getStroke()));
    shape.addAttribute("stroke-width", String.valueOf(this.getStrokeWidth()));

    String transforms = ConvertShape.transformsToString(this.shape);

    if (!transforms.isEmpty()) {
      shape.addAttribute("transform", transforms);
    }
    return shape;
  }

  @Override
  public Shape getShapeFromXML(Element element) {
    Square square = new Square();
    double width = Double.parseDouble(element.attributeValue("width"));
    double height = Double.parseDouble(element.attributeValue("height"));
    double x = Double.parseDouble(element.attributeValue("x"));
    double y = Double.parseDouble(element.attributeValue("y"));
    square.getPoints().addAll(x, y, x, y + height, x + width, y + height, x + width, y);
    square.setFill(Color.web(element.attributeValue("fill-color")));
    square.setStroke(Color.web(element.attributeValue("stroke-color")));
    square.setStrokeWidth(Double.parseDouble(element.attributeValue("stroke-width")));
    String transforms = element.attributeValue("transform");
    if (transforms != null) {
      square.getTransforms().addAll(ConvertShape.transformsFromString(transforms));
    }
    return square;
  }

}
