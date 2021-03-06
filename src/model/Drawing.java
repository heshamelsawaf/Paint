package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.HistoryController;
import controller.PaintController;
import javafx.scene.transform.Rotate;
import view.focusOutline.FocusOutline;
import view.focusOutline.ResizeAnchor;

public class Drawing implements Cloneable {

  private PaintController paintController;

  private List<Observer> observers;
  private String title;
  private boolean saved;

  private List<Shape> shapes;
  private double width, height;

  public Drawing(PaintController paintController) {
    this.paintController = paintController;
    this.shapes = new ArrayList<>();
    this.observers = new ArrayList<>();
    this.saved = false;
  }

  public void registerObserver(Observer observer) {
    this.observers.add(observer);
  }

  public void notifyObservers() {
    this.observers.forEach(Observer::update);
  }

  public void setTitle(String title) {
    this.title = title;
    this.notifyObservers();
  }

  public String getTitle() {
    return this.title;
  }

  public void setDimensions(double width, double height) {
    this.width = width;
    this.height = height;
    this.notifyObservers();
  }

  public void setWidth(double width) {
    this.width = width;
    this.notifyObservers();
  }

  public double getWidth() {
    return this.width;
  }

  public void setHeight(int height) {
    this.height = height;
    this.notifyObservers();
  }

  public double getHeight() {
    return this.height;
  }

  public void setSaved(boolean saved) {
    this.saved = saved;
    this.notifyObservers();
  }

  public boolean isSaved() {
    return this.saved;
  }

  public void addShape(Shape shape) {
    this.shapes.add(shape);
    this.addListenersToShape(shape);
    this.notifyObservers();
  }

  public void addListenersToShape(Shape shape) {
    shape.fillProperty().addListener((observableValue, oldValue, newValue) -> {
      HistoryController.getInstance(this.paintController).createHistoryEntry();
    });
    shape.StrokeProperty().addListener((observableValue, oldValue, newValue) -> {
      HistoryController.getInstance(this.paintController).createHistoryEntry();
    });
    shape.StrokeWidthProperty().addListener((observableValue, oldValue, newValue) -> {
      HistoryController.getInstance(this.paintController).createHistoryEntry();
    });
  }

  public List<Shape> getShapes() {
    return this.shapes;
  }

  public List<Observer> getObservers() {
    return this.observers;
  }

  public void removeShape(Shape shape) {
    int index = this.shapes.indexOf(shape);
    this.shapes.set(index, null);
    this.shapes.remove(index);
    this.notifyObservers();
  }

  public void clearAllShapes() {
    this.shapes.clear();
    this.notifyObservers();
  }

  public void setBehindAll(Shape shape) {
    int index;
    while ((index = this.shapes.indexOf(shape)) > 0) {
      Collections.swap(this.shapes, index, index - 1);
    }
    HistoryController.getInstance(this.paintController).createHistoryEntry();
    this.notifyObservers();
  }

  public void setBehind(Shape shape) {
    int index = this.shapes.indexOf(shape);
    if (index > 0) {
      Collections.swap(this.shapes, index, index - 1);
      HistoryController.getInstance(this.paintController).createHistoryEntry();
      this.notifyObservers();
    }
  }

  public void setInFrontOfAll(Shape shape) {
    int index;
    while ((index = this.shapes.indexOf(shape)) < this.shapes.size() - 1) {
      Collections.swap(this.shapes, index, index + 1);
    }
    HistoryController.getInstance(this.paintController).createHistoryEntry();
    this.notifyObservers();
  }

  public void setInFront(Shape shape) {
    int index = this.shapes.indexOf(shape);
    if (index < this.shapes.size() - 1) {
      Collections.swap(this.shapes, index, index + 1);
      HistoryController.getInstance(this.paintController).createHistoryEntry();
      this.notifyObservers();
    }
  }

  public void rotateShape(double angle, boolean createSaveEntry) {
    GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();
    FocusOutline focusOutline = guiHelper.getFocusOutline();

    Rotate rotate = new Rotate(angle);
    rotate.setPivotX(focusOutline.getRotateAnchor().getCenterX());
    rotate.setPivotY(focusOutline.getRotateAnchor().getCenterY());

    focusOutline.getHighlightedRectangle().getTransforms().add(rotate);
    focusOutline.getRotateAnchor().getTransforms().add(rotate);
    guiHelper.getSelectedShape().getTransforms().add(rotate);

    for (ResizeAnchor resizeAnchor : focusOutline.getResizeAnchors()) {
      resizeAnchor.getTransforms().add(rotate);
    }
    if (createSaveEntry) {
      HistoryController.getInstance(this.paintController).createHistoryEntry();
    }
  }

  @Override
  public Drawing clone() {
    Drawing clone = new Drawing(this.paintController);
    clone.setTitle(this.getTitle());
    clone.setDimensions(this.getWidth(), this.getHeight());
    clone.setSaved(this.isSaved());
    for (Shape shape : this.getShapes()) {
      clone.addShape(shape.getClone());
    }
    this.observers.forEach(clone::registerObserver);
    return clone;
  }
}
