package controller;

import javafx.stage.Stage;

public class PaintController {

  private GUIController guiController;

  public PaintController(Stage primaryStage) {
    this.guiController = new GUIController(this, primaryStage);
    this.guiController.openHome();
  }

  public GUIController getGUIController() {
    return guiController;
  }
}
