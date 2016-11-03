package eventHandlers;

import controller.HistoryController;
import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GUIHelper;
import model.shapes.Line;

public class LineEventHandler extends MouseEventHandler {

  private Line line;

  private double masterX;
  private double masterY;

  public LineEventHandler(PaintController paintController) {
    super(paintController);
  }

  @Override
  public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
    return event -> {
      if (event.isPrimaryButtonDown()) {
        GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();

        guiHelper.setSelectedShape(null);

        this.masterX = event.getX();
        this.masterY = event.getY();

        this.line = new Line();
        this.line.setStroke(guiHelper.getStrokeColor());
        this.line.setStrokeWidth(guiHelper.getStrokeWidth().getStrokeWidthAsInt());

        this.line.setStartX(this.masterX);
        this.line.setStartY(this.masterY);
        this.line.setEndX(this.masterX + 1);
        this.line.setEndY(this.masterY + 1);
        this.line.setCursor(Cursor.CROSSHAIR);
        this.line.setOnMouseMoved(this.getOnMouseMovedEventHandler());
        this.line.setOnMousePressed(this.getOnMousePressedEventHandler());
        this.line.setOnMouseDragged(this.getOnMouseDraggedEventHandler());
        this.line.setOnMouseReleased(this.getOnMouseReleasedEventHandler());

        this.paintController.getDrawingController().addShape(this.line);
      }
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseExitedEventHandler() {
    return null;
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
    return event -> {
      super.getOnMouseDraggedEventHandler().handle(event);

      double tempX = event.getX();
      double tempY = event.getY();

      if (event.isPrimaryButtonDown()) {
        double endX = tempX;
        double endY = tempY;

        if (event.isShiftDown()) {
          if (Math.abs(tempX - this.masterX) > Math.abs(tempY - this.masterY)) {
            endY = this.masterY;
          } else {
            endX = this.masterX;
          }
        }

        this.line.setEndX(endX);
        this.line.setEndY(endY);
      }

      this.homeScene.getStatusBar().updateCoordinates(tempX, tempY);
      this.setDelta(true);
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
    return event -> {
      this.paintController.getGUIController().getGuiHelper().setSelectedShape(this.line);
      HistoryController.getInstance(this.paintController).createHistoryEntry();
      this.line = null;
    };
  }

}
