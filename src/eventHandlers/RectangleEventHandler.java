package eventHandlers;

import controller.HistoryController;
import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GUIHelper;
import model.shapes.Rectangle;

public class RectangleEventHandler extends MouseEventHandler {

  private Rectangle rectangle;

  private double masterX;
  private double masterY;

  public RectangleEventHandler(PaintController paintController) {
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

        this.rectangle = new Rectangle();
        this.rectangle.setFill(guiHelper.getFillColor());
        this.rectangle.setStroke(guiHelper.getStrokeColor());
        this.rectangle.setStrokeWidth(guiHelper.getStrokeWidth().getStrokeWidthAsInt());

        this.rectangle.getPoints().addAll(this.masterX, this.masterY, this.masterX,
            this.masterY + 1, this.masterX + 1, this.masterY + 1, this.masterX + 1, this.masterY);
        this.rectangle.setCursor(Cursor.CROSSHAIR);
        this.rectangle.setOnMouseMoved(this.getOnMouseMovedEventHandler());
        this.rectangle.setOnMousePressed(this.getOnMousePressedEventHandler());
        this.rectangle.setOnMouseDragged(this.getOnMouseDraggedEventHandler());
        this.rectangle.setOnMouseReleased(this.getOnMouseReleasedEventHandler());


        this.paintController.getDrawingController().addShape(this.rectangle);
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
        double topX = this.masterX;
        double topY = this.masterY;

        double width = Math.abs(tempX - this.masterX);
        double height = Math.abs(tempY - this.masterY);

        if (event.isShiftDown()) {
          width = Math.min(width, height);
          height = Math.min(width, height);
        }

        if (tempX < this.masterX) {
          topX = this.masterX - width;
        }

        if (tempY < this.masterY) {
          topY = this.masterY - height;
        }

        this.rectangle.getPoints().clear();
        this.rectangle.getPoints().addAll(topX, topY, topX, topY + height, topX + width,
            topY + height, topX + width, topY);
      }

      this.homeScene.getStatusBar().updateCoordinates(tempX, tempY);
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
    return event -> {
      this.paintController.getGUIController().getGuiHelper().setSelectedShape(this.rectangle);

      HistoryController.getInstance(this.paintController).createHistoryEntry();

      this.rectangle = null;
    };
  }

}
