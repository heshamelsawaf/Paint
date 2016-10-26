package view.main;

import controller.PaintController;
import javafx.stage.Stage;

public class Home {

  private PaintController paintController;
  private Stage primaryStage;

  public Home(PaintController paintController, Stage primaryStage) {
    this.paintController = paintController;
    this.primaryStage = primaryStage;
    this.init();
  }

  private void init() {

  }

  public void setTitle(String title) {
    if (title != null) {

    } else {

    }
  }

}
