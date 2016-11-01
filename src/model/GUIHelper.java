package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.PaintController;
import javafx.scene.paint.Color;
import util.HomeConstants;
import view.DrawingTools;
import view.StrokeWidth;
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
  private StrokeWidth strokeWidth;

  private Shape selectedObject;
  private FocusOutline focusOutline;

  public GUIHelper(PaintController paintController) {
    this.paintController = paintController;

    this.observers = new ArrayList<>();

    this.zoomLevel = HomeConstants.ZOOM_LEVEL;
    this.lastUsedDirectory = new File(System.getProperty("user.home"));

    this.fillColor = Color.WHITE;
    this.strokeColor = Color.BLACK;
    this.strokeWidth = StrokeWidth.MEDIUM;
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

  public Shape getSelectedObject() {
    return this.selectedObject;
  }

  public void setSelectedObject(Shape selectedObject) {
    this.selectedObject = selectedObject;
    this.focusOutline = new FocusOutline(this.paintController, selectedObject);
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

  public StrokeWidth getStrokeWidth() {
    return strokeWidth;
  }

  public void setStrokeWidth(StrokeWidth strokeWidth) {
    this.strokeWidth = strokeWidth;
    this.notifyObservers();
  }

  public FocusOutline getFocusOutline() {
    return focusOutline;
  }

  public void setSelectedDrawTool(DrawingTools selectedDrawingTool) {
    this.selectedDrawingTool = selectedDrawingTool;
    this.notifyObservers();
  }

  public DrawingTools getSelectedDrawTool() {
    return this.selectedDrawingTool;
  }

  public void setZoomLevel(double zoomLevel) {
    zoomLevel = Math.max(Math.min(zoomLevel, 5), 0.1);
    this.zoomLevel = zoomLevel;
    this.notifyObservers();
  }

  public double getZoomLevel() {
    return this.zoomLevel;
  }



}
