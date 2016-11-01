package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GUIHelper;
import model.shapes.Ellipse;

public class EllipseEventHandler extends MouseEventHandler {

  private Ellipse ellipse;

  private double selectedX;
  private double selectedY;


  public EllipseEventHandler(PaintController paintController) {
    super(paintController);
  }

  @Override
  public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
    return event -> {
      if (event.isPrimaryButtonDown()) {
        GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();

        guiHelper.setSelectedShape(null);
        this.selectedX = event.getX();
        this.selectedY = event.getY();

        this.ellipse = new Ellipse();
        this.ellipse.setFill(guiHelper.getFillColor());
        this.ellipse.setStroke(guiHelper.getStrokeColor());
        this.ellipse.setStrokeWidth(guiHelper.getStrokeWidth().getStrokeWidth());

        this.ellipse.setRadiusX(1);
        this.ellipse.setRadiusY(1);
        this.ellipse.setCenterX(this.selectedX);
        this.ellipse.setCenterY(this.selectedY);
        this.ellipse.setCursor(Cursor.CROSSHAIR);
        this.ellipse.setOnMouseMoved(this.getOnMouseMovedEventHandler());
        this.ellipse.setOnMousePressed(this.getOnMousePressedEventHandler());
        this.ellipse.setOnMouseDragged(this.getOnMouseDraggedEventHandler());
        this.ellipse.setOnMouseReleased(this.getOnMouseReleasedEventHandler());


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
        double radiusX = Math.abs(tempX - this.selectedX) / 2;
        double radiusY = Math.abs(tempY - this.selectedY) / 2;

        if (event.isShiftDown()) {
          radiusX = Math.min(radiusX, radiusY);
          radiusY = Math.min(radiusX, radiusY);
        }

        if (tempX < this.selectedX) {
          centerX = this.selectedX - radiusX;
        } else {
          centerX = this.selectedX + radiusX;
        }
        if (tempY < this.selectedY) {
          centerY = this.selectedY - radiusY;
        } else {
          centerY = this.selectedY + radiusY;
        }
        this.ellipse.setRadiusX(radiusX);
        this.ellipse.setRadiusY(radiusY);

        this.ellipse.setCenterX(centerX);
        this.ellipse.setCenterY(centerY);

      }
      this.homeScene.getStatusBar().updateCoordinates(tempX, tempY);
      this.setDelta(true);
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
    return event -> {
      this.paintController.getGUIController().getGuiHelper().setSelectedShape(this.ellipse);
      this.ellipse = null;
    };
  }

}
