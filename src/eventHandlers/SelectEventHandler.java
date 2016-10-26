package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SelectEventHandler extends MouseEventHandler {

  public SelectEventHandler(PaintController paintController) {
    super(paintController);
  }

  @Override
  public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        delta = false;
        if (event.isPrimaryButtonDown()) {

        }
      }
    };
    return eventHandler;
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {

        if (event.isPrimaryButtonDown()) {

        }
        delta = true;
      }
    };
    return eventHandler;
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseExitedEventHandler() {
    return null;
  }
}
