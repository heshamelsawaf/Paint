package controller;

import model.Drawing;
import model.GUIHelper;
import model.Shape;
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
    // here goes setDrawing
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
  }

  public void clearAllShapes(Shape shape) {
    this.drawing.clearAllShapes();
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
          shouldBeClosed = true;
        } else {
          closeIt = false;
        }
      }
      if (shouldBeClosed) {
        this.guiHelper.setSelectedShape(null);
        this.drawing = null;
        this.homeScene.reset();
        this.homeScene.getStatusBar().clear();
        this.homeScene.hideBorderMessage();
        this.homeScene.activateControls(false);
      }
    }
    return closeIt;
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
