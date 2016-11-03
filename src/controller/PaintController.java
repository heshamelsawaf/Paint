package controller;

import app.Main;
import javafx.stage.Stage;

public class PaintController {

  private GUIController guiController;
  private DrawingController drawingController;
  private XMLController xmlController;

  public PaintController(Stage primaryStage) {
    this.guiController = new GUIController(this, primaryStage);
    this.drawingController = new DrawingController(this);
    this.xmlController = new XMLController();
    this.guiController.openHome();
  }

  public void exit() {
    if (this.drawingController.closeDrawing()) {
      this.guiController.closeHome();
      Main.exit();
    }
  }

  public GUIController getGUIController() {
    return this.guiController;
  }

  public DrawingController getDrawingController() {
    return this.drawingController;
  }

  public XMLController getXMLController() {
    return this.xmlController;
  }
}
