package controller;

import model.Drawing;
import model.GUIHelper;
import model.Shape;
import model.shapes.Ellipse;
import model.shapes.Line;
import model.shapes.Polygon;
import util.HomeConstants;
import view.main.HomeScene;

public class DrawingController {

  private PaintController paintController;
  private Drawing drawing;
  private GUIHelper guiHelper;
  private HomeScene homeScene;

  public DrawingController(PaintController paintController) {
    this.paintController = paintController;
    this.homeScene = paintController.getGUIController().getHome().getHomeScene();
    this.guiHelper = paintController.getGUIController().getGuiHelper();
  }

  public Drawing createDrawing(String title, double width, double height) {
    this.drawing = new Drawing(this.paintController);
    this.setDrawingTitle(title);
    this.setupDrawingArea(width, height);
    return this.drawing;
  }

  public void setDrawingTitle(String title) {
    this.drawing.setTitle(title);
  }

  public void setupDrawingArea(double width, double height) {
    this.drawing.setDimensions(width, height);
    this.homeScene.setupDrawingArea(width, height);
    this.homeScene.activateControls(true);
  }

  public Drawing getDrawing() {
    return this.drawing;
  }

  public void setDrawing(Drawing drawing) {
    this.drawing = drawing;
  }

  public void addShape(Shape shape) {
    this.drawing.addShape(shape);
  }

  public void removeShape(Shape shape) {
    this.drawing.removeShape(shape);
    HistoryController.getInstance(this.paintController).createHistoryEntry();
  }

  public void clearAllShapes() {
    this.drawing.clearAllShapes();
    HistoryController.getInstance(this.paintController).createHistoryEntry();
    this.guiHelper.setSelectedShape(null);
  }

  public boolean closeDrawing() {
    boolean closeIt = true;
    if (this.drawing != null) {
      boolean shouldBeClosed = false;
      if (this.drawing.isSaved()) {
        shouldBeClosed = true;
      } else {
        String choice = this.paintController.getGUIController().getHome().alertAboutUnsavedData()
            .get().getText();
        if (choice.equals(HomeConstants.SAVE_BUTTON)
            || choice.equals(HomeConstants.CLOSE_WITHOUT_SAVING_BUTTON)) {
          shouldBeClosed = true; // Don't forget to edit it after adding save
        } else {
          closeIt = false;
        }
      }
      if (shouldBeClosed) {
        this.guiHelper.setSelectedShape(null);
        this.drawing = null;
        HistoryController.getInstance(this.paintController).resetHistory();
        this.homeScene.reset();
        this.homeScene.getStatusBar().clear();
        this.homeScene.hideBorderMessage();
        this.homeScene.activateControls(false);
      }
    }
    return closeIt;
  }

  public void duplicateShape(Shape shape) {
    if (shape instanceof Polygon) {
      Polygon polygon = (Polygon) shape;

      Polygon clone = (Polygon) polygon.getClone();
      for (int i = 0; i < polygon.getPoints().size(); i++) {
        if (i % 2 == 0)
          clone.getPoints()
              .add(polygon.getPoints().get(i) + HomeConstants.DUPLICATED_SHAPE_OFFSET_X);
        else
          clone.getPoints()
              .add(polygon.getPoints().get(i) + HomeConstants.DUPLICATED_SHAPE_OFFSET_Y);

      }
      this.paintController.getDrawingController().addShape(clone);
      this.guiHelper.setSelectedShape(clone);
    }

    else if (shape instanceof Ellipse) {
      Ellipse ellipse = (Ellipse) shape;

      Ellipse clone = (Ellipse) ellipse.getClone();
      clone.setCenterX(clone.getCenterX() + HomeConstants.DUPLICATED_SHAPE_OFFSET_X);
      clone.setCenterY(clone.getCenterY() + HomeConstants.DUPLICATED_SHAPE_OFFSET_Y);

      this.paintController.getDrawingController().addShape(clone);
      this.guiHelper.setSelectedShape(clone);
    }

    else if (shape instanceof Line) {
      Line line = (Line) shape;

      Line clone = (Line) line.getClone();
      clone.setStartX(clone.getStartX() + HomeConstants.DUPLICATED_SHAPE_OFFSET_X);
      clone.setStartY(clone.getStartY() + HomeConstants.DUPLICATED_SHAPE_OFFSET_Y);
      clone.setEndX(clone.getEndX() + HomeConstants.DUPLICATED_SHAPE_OFFSET_X);
      clone.setEndY(clone.getEndY() + HomeConstants.DUPLICATED_SHAPE_OFFSET_Y);

      this.paintController.getDrawingController().addShape(clone);
      this.guiHelper.setSelectedShape(clone);
    }

    HistoryController.getInstance(this.paintController).createHistoryEntry();
  }

  public void setBehind(Shape shape) {
    this.drawing.setBehind(shape);
  }

  public void setBehindAll(Shape shape) {
    this.drawing.setBehindAll(shape);
  }

  public void setInFront(Shape shape) {
    this.drawing.setInFront(shape);
  }

  public void setInFrontOfAll(Shape shape) {
    this.drawing.setInFrontOfAll(shape);
  }
}
