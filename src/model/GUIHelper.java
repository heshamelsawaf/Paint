package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.PaintController;
import javafx.scene.paint.Color;
import util.HomeConstants;
import view.DrawingTools;
import view.StrokeWidths;
import view.focusOutline.FocusOutline;

public class GUIHelper {

  private PaintController paintController;

  private List<Observer> observers;
  private double zoomLevel;

  private File file;
  private File lastUsedDirectory;
  private DrawingTools selectedDrawingTool;

  private Color fillColor;
  private Color strokeColor;
  private StrokeWidths strokeWidth;

  private Shape selectedShape;
  private FocusOutline focusOutline;

  public GUIHelper(PaintController paintController) {
    this.paintController = paintController;

    this.observers = new ArrayList<>();

    this.zoomLevel = HomeConstants.ZOOM_LEVEL;
    this.lastUsedDirectory = new File(System.getProperty("user.home"));

    this.fillColor = Color.TRANSPARENT;
    this.strokeColor = Color.BLACK;
    this.strokeWidth = StrokeWidths.MEDIUM;
  }

  public void registerObserver(Observer observer) {
    this.observers.add(observer);
  }

  public void notifyObservers() {
    this.observers.forEach(Observer::update);
  }

  public File getLastUsedDirectory() {
    return this.lastUsedDirectory;
  }

  public void setLastUsedDirectory(File lastUsedDirectory) {
    this.lastUsedDirectory = lastUsedDirectory;
  }

  public File getFile() {
    return this.file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public Shape getSelectedShape() {
    return this.selectedShape;
  }

  public void setSelectedShape(Shape selectedShape) {
    this.selectedShape = selectedShape;
    this.focusOutline = new FocusOutline(this.paintController, selectedShape);
    this.notifyObservers();
  }

  public Color getFillColor() {
    return this.fillColor;
  }

  public void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
    this.notifyObservers();
  }

  public Color getStrokeColor() {
    return this.strokeColor;
  }

  public void setStrokeColor(Color strokeColor) {
    this.strokeColor = strokeColor;
    this.notifyObservers();
  }

  public StrokeWidths getStrokeWidth() {
    return strokeWidth;
  }

  public void setStrokeWidth(StrokeWidths strokeWidth) {
    this.strokeWidth = strokeWidth;
    this.notifyObservers();
  }

  public FocusOutline getFocusOutline() {
    return this.focusOutline;
  }

  public void setSelectedDrawTool(DrawingTools selectedDrawingTool) {
    this.selectedDrawingTool = selectedDrawingTool;
    this.notifyObservers();
  }

  public DrawingTools getSelectedDrawTool() {
    return this.selectedDrawingTool;
  }

  public void setZoomLevel(double zoomLevel) {
    this.zoomLevel = Math.max(Math.min(zoomLevel, 5), 0.1);
    this.notifyObservers();
  }

  public double getZoomLevel() {
    return this.zoomLevel;
  }
}
