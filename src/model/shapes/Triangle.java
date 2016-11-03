package model.shapes;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javafx.scene.paint.Color;
import model.Shape;
import util.ConvertColor;
import util.ConvertShape;
import util.Point;

public class Triangle extends Polygon {

  public Triangle() {}

  public Triangle(Point pt1, Point pt2, Point pt3) {
    super(pt1, pt2, pt3);
  }

  public Triangle(double pt1X, double pt1Y, double pt2X, double pt2Y, double pt3X, double pt3Y) {
    super(pt1X, pt1Y, pt2X, pt2Y, pt3X, pt3Y);
  }

  @Override
  public Element getXMLShape() {
    Element shape = DocumentHelper.createElement("triangle");
    double side1 =
        Math.sqrt(Math.pow(this.shape.getPoints().get(0) - this.shape.getPoints().get(2), 2)
            + Math.pow(this.shape.getPoints().get(1) - this.shape.getPoints().get(3), 2));
    double side2 =
        Math.sqrt(Math.pow(this.shape.getPoints().get(2) - this.shape.getPoints().get(4), 2)
            + Math.pow(this.shape.getPoints().get(3) - this.shape.getPoints().get(5), 2));
    double side3 =
        Math.sqrt(Math.pow(this.shape.getPoints().get(4) - this.shape.getPoints().get(0), 2)
            + Math.pow(this.shape.getPoints().get(5) - this.shape.getPoints().get(1), 2));
    shape.addAttribute("side1", String.valueOf(side1));
    shape.addAttribute("side2", String.valueOf(side2));
    shape.addAttribute("side3", String.valueOf(side3));
    shape.addAttribute("x1", String.valueOf(this.shape.getPoints().get(0)));
    shape.addAttribute("y1", String.valueOf(this.shape.getPoints().get(1)));
    shape.addAttribute("x2", String.valueOf(this.shape.getPoints().get(2)));
    shape.addAttribute("y2", String.valueOf(this.shape.getPoints().get(3)));
    shape.addAttribute("x3", String.valueOf(this.shape.getPoints().get(4)));
    shape.addAttribute("y3", String.valueOf(this.shape.getPoints().get(5)));

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
    Triangle triangle = new Triangle();
    double x1 = Double.parseDouble(element.attributeValue("x1"));
    double y1 = Double.parseDouble(element.attributeValue("y1"));
    double x2 = Double.parseDouble(element.attributeValue("x2"));
    double y2 = Double.parseDouble(element.attributeValue("y2"));
    double x3 = Double.parseDouble(element.attributeValue("x3"));
    double y3 = Double.parseDouble(element.attributeValue("y3"));
    triangle.getPoints().addAll(x1, y1, x2, y2, x3, y3);
    triangle.setFill(Color.web(element.attributeValue("fill-color")));
    triangle.setStroke(Color.web(element.attributeValue("stroke-color")));
    triangle.setStrokeWidth(Double.parseDouble(element.attributeValue("stroke-width")));
    String transforms = element.attributeValue("transform");
    if (transforms != null) {
      triangle.getTransforms().addAll(ConvertShape.transformsFromString(transforms));
    }
    return triangle;
  }
}

