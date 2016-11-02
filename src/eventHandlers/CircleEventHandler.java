	package eventHandlers;

import controller.HistoryController;
import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GUIHelper;
import model.shapes.Circle;

public class CircleEventHandler extends MouseEventHandler {

  private Circle circle;

  private double masterX;
  private double masterY;

  public CircleEventHandler(PaintController paintController) {
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

        this.circle = new Circle();
        this.circle.setFill(guiHelper.getFillColor());
        this.circle.setStroke(guiHelper.getStrokeColor());
        this.circle.setStrokeWidth(guiHelper.getStrokeWidth().getStrokeWidthAsInt());

        this.circle.setRadiusX(1);
        this.circle.setRadiusY(1);
        this.circle.setCenterX(this.masterX);
        this.circle.setCenterY(this.masterY);
        this.circle.setCursor(Cursor.CROSSHAIR);
        this.circle.setOnMouseMoved(this.getOnMouseMovedEventHandler());
        this.circle.setOnMousePressed(this.getOnMousePressedEventHandler());
        this.circle.setOnMouseDragged(this.getOnMouseDraggedEventHandler());
        this.circle.setOnMouseReleased(this.getOnMouseReleasedEventHandler());

        this.paintController.getDrawingController().addShape(this.circle);
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

      double centerX, centerY;
      double tempX = event.getX();
      double tempY = event.getY();

      if (event.isPrimaryButtonDown()) {
        double radiusX = Math.abs(tempX - this.masterX) / 2;
        double radiusY = Math.abs(tempY - this.masterY) / 2;
         radiusX = Math.min(radiusX, radiusY);
         radiusY = Math.min(radiusX, radiusY);

        if (tempX < this.masterX) {
          centerX = this.masterX - radiusX;
        } else {
          centerX = this.masterX + radiusX;
        }
        if (tempY < this.masterY) {
          centerY = this.masterY - radiusY;
        } else {
          centerY = this.masterY + radiusY;
        }
        this.circle.setRadiusX(radiusX);
        this.circle.setRadiusY(radiusY);

        this.circle.setCenterX(centerX);
        this.circle.setCenterY(centerY);

      }
      this.homeScene.getStatusBar().updateCoordinates(tempX, tempY);
      this.setDelta(true);
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
    return event -> {
      this.paintController.getGUIController().getGuiHelper().setSelectedShape(this.circle);
      HistoryController.getInstance(this.paintController).createHistoryEntry();

      this.circle = null;
    };
  }
}
