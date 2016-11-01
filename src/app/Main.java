package app;

import controller.PaintController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    new PaintController(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static void exit() {
    System.exit(0);
  }
}
