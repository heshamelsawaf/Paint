package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeystrokeEventHandler {

  private PaintController paintController;

  public KeystrokeEventHandler(PaintController paintController) {
    this.paintController = paintController;
  }

  public EventHandler<KeyEvent> getOnKeyPressedEventHandler() {
    return event -> {
      if (event.getCode().equals(KeyCode.ALT)) {
        this.paintController.getGUIController().getHome().getHomeScene().showMenuBar(true);
      }
    };
  }
}
