package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.main.HomeScene;

public abstract class MouseEventHandler {

  protected PaintController paintController;
  protected HomeScene homeScene;
  //
  private boolean delta;

  public MouseEventHandler(PaintController paintController) {
    this.paintController = paintController;

  }

  public EventHandler<MouseEvent> getOnMouseMovedEventHandler() {
    return event -> {
      this.homeScene.getStatusBar().updateCoordinates(event.getX(), event.getY());
    };
  }

  public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
    return event -> {
      this.homeScene.getStatusBar().updateCoordinates(event.getX(), event.getY());
    };
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

  protected boolean isDelta() {
    return delta;
  }

  protected void setDelta(boolean delta) {
    this.delta = delta;
  }


}
