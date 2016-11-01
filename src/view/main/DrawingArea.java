package view.main;

import controller.PaintController;
import eventHandlers.MouseEventHandler;
import eventHandlers.SelectEventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class DrawingArea extends Canvas {

  private PaintController paintController;

  public DrawingArea(PaintController paintController, double width, double height) {

    super(width, height);
    this.paintController = paintController;

    this.getGraphicsContext2D().setFill(Color.WHITE);
    this.setMouseEventHandlers(new SelectEventHandler(this.paintController));
  }

  public void setMouseEventHandlers(MouseEventHandler mouseEventHandler) {
    this.setOnMouseMoved(mouseEventHandler.getOnMouseMovedEventHandler());
    this.setOnMouseExited(mouseEventHandler.getOnMouseExitedEventHandler());
    this.setOnMousePressed(mouseEventHandler.getOnMousePressedEventHandler());
    this.setOnMouseDragged(mouseEventHandler.getOnMouseDraggedEventHandler());
    this.setOnMouseReleased(mouseEventHandler.getOnMouseReleasedEventHandler());
  }
}
