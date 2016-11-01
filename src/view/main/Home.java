package view.main;

import controller.PaintController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;

public class Home {

  private PaintController paintController;
  private Stage primaryStage;
  private HomeScene homeScene;

  public Home(PaintController paintController, Stage primaryStage) {
    this.paintController = paintController;
    this.primaryStage = primaryStage;
    this.init();
  }

  private void init() {
    this.homeScene = new HomeScene(this.paintController, this);
    this.primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.F11));
    this.primaryStage.setOnCloseRequest(event -> {
      // this.paintController.exit();
      event.consume();
    });
    this.primaryStage.setScene(this.homeScene);
    this.setTitle(null);
  }

  public void open() {
    this.primaryStage.show();
    this.primaryStage.requestFocus();
  }

  public void close() {
    this.primaryStage.close();
  }

  public void setTitle(String title) {
    if (title != null) {

    } else {

    }
  }

  public HomeScene getHomeScene() {
    return homeScene;
  }

}
