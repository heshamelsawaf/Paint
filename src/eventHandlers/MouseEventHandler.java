package eventHandlers;

import controller.HistoryController;
import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Drawing;
import view.main.DrawingArea;
import view.main.HomeScene;

public abstract class MouseEventHandler {

  protected PaintController paintController;
  protected HomeScene homeScene;
  protected Drawing drawing;

  private boolean delta;

  public MouseEventHandler(PaintController paintController) {
    this.paintController = paintController;
    this.homeScene = this.paintController.getGUIController().getHome().getHomeScene();
    this.drawing = this.paintController.getDrawingController().getDrawing();
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
    return event -> {
      if (!(event.getSource() instanceof DrawingArea)) {
        if (isDelta()) {
          HistoryController.getInstance(this.paintController).createHistoryEntry();
        }
      }
    };
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
