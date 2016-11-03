package view.main;

import java.util.Optional;

import controller.PaintController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Stage;
import util.HomeConstants;
import util.Message;

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
      this.paintController.exit();
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
      this.primaryStage.setTitle(title + " - " + HomeConstants.APP_TITLE);
    } else {
      this.primaryStage.setTitle(HomeConstants.APP_TITLE);
    }
  }

  public HomeScene getHomeScene() {
    return this.homeScene;
  }

  public Stage getPrimaryStage() {
    return this.primaryStage;
  }

  public Optional<ButtonType> alertAboutUnsavedData() {
    return this.initAlert().showAndWait();
  }

  private Alert initAlert() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.initOwner(this.primaryStage);
    alert.setHeaderText(Message.UNSAVED_CHANGES_MESSAGE);
    alert.setContentText(Message.UNSAVED_CHANGES_CONTEXT_MESSAGE);

    ButtonType save = new ButtonType(HomeConstants.SAVE_BUTTON);
    ButtonType closeWithoutSaving = new ButtonType(HomeConstants.CLOSE_WITHOUT_SAVING_BUTTON);
    ButtonType dismiss = new ButtonType(HomeConstants.DISMISS_BUTTON);

    alert.getButtonTypes().setAll(save, closeWithoutSaving, dismiss);
    return alert;
  }

  public void setTofullScreen(boolean activate) {
    this.primaryStage.setFullScreen(activate);
  }

}
