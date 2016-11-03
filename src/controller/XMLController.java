package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import model.Shape;
import model.shapes.Circle;
import model.shapes.Ellipse;
import model.shapes.Line;
import model.shapes.Rectangle;
import model.shapes.Square;
import model.shapes.Triangle;
import util.HomeConstants;

public class XMLController {

  private Document xmlDrawing;
  private Element xmlSuperElement;

  public void createXMLDrawing() {
    this.xmlDrawing = DocumentHelper.createDocument();
    this.xmlSuperElement = xmlDrawing.addElement(HomeConstants.APP_TITLE.replaceAll(" ", "_"));
    this.xmlSuperElement.addAttribute("version", "1.0");
  }

  public void addDimensionsToXML(double width, double height) {
    this.xmlSuperElement.addAttribute("width", String.valueOf(width));
    this.xmlSuperElement.addAttribute("height", String.valueOf(height));
  }

  public double getWidthFromXML() {
    return Double.parseDouble(this.xmlSuperElement.attributeValue("width"));
  }

  public double getHeightFromXML() {
    return Double.parseDouble(this.xmlSuperElement.attributeValue("height"));
  }

  public void addDrawingTitleToXML(String title) {
    this.xmlSuperElement.addAttribute("title", title);
  }

  public String getDrawingTitleFromXML() {
    return this.xmlSuperElement.attributeValue("title");
  }

  public void addShapesToXML(List<Shape> shapes) {
    for (Shape shape : shapes) {
      this.xmlSuperElement.add(shape.getXMLShape());
    }
  }

  public List<Shape> getShapesFromXML() {
    List<Shape> shapes = new ArrayList<>();
    List<org.dom4j.Node> nodes = this.xmlSuperElement.elements();
    for (org.dom4j.Node node : nodes) {
      String nameOfShape = node.getName().toLowerCase().trim();
      Shape shape = null;
      switch (nameOfShape) {
        case "rectangle":
          shape = new Rectangle();
          shape = (Rectangle) shape.getShapeFromXML((Element) node);
          break;
        case "circle":
          shape = new Circle();
          shape = (Circle) shape.getShapeFromXML((Element) node);
          break;
        case "line":
          shape = new Line();
          shape = (Line) shape.getShapeFromXML((Element) node);
          break;
        case "ellipse":
          shape = new Ellipse();
          shape = (Ellipse) shape.getShapeFromXML((Element) node);
          break;
        case "triangle":
          shape = new Triangle();
          shape = (Triangle) shape.getShapeFromXML((Element) node);
          break;
        case "square":
          shape = new Square();
          shape = (Square) shape.getShapeFromXML((Element) node);
          break;
        default:
          break;
      }
      if (shape != null) {
        shapes.add(shape);
      }
    }
    return shapes;
  }

  public void inputFileToXML(File file) throws DocumentException {
    SAXReader saxReader = new SAXReader();
    this.xmlDrawing = saxReader.read(file);
  }

  public void outputXMLToFile(File file) throws IOException {
    OutputFormat outputFormat = OutputFormat.createPrettyPrint();
    XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file), outputFormat);
    xmlWriter.write(this.xmlDrawing);
    xmlWriter.close();
  }
}

