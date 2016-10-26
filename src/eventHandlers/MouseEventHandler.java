package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class MouseEventHandler {

  protected PaintController paintController;
  //
  //
  protected boolean delta;

  public MouseEventHandler(PaintController paintController) {
    this.paintController = paintController;

  }

  public EventHandler<MouseEvent> getOnMouseMovedEventHandler() {
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {

      }
    };
    return eventHandler;
  }

  public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {

      }
    };
    return eventHandler;
  }

  public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {

      }
    };
    return eventHandler;
  }


  public abstract EventHandler<MouseEvent> getOnMousePressedEventHandler();

  public abstract EventHandler<MouseEvent> getOnMouseExitedEventHandler();


}
