package eventHandlers;

import controller.HistoryController;
import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GUIHelper;
import model.shapes.Square;

public class SquareEventHandler extends MouseEventHandler {

  private Square square;

  private double masterX;
  private double masterY;

  public SquareEventHandler(PaintController paintController) {
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

        this.square = new Square();
        this.square.setFill(guiHelper.getFillColor());
        this.square.setStroke(guiHelper.getStrokeColor());
        this.square.setStrokeWidth(guiHelper.getStrokeWidth().getStrokeWidthAsInt());

        this.square.getPoints().addAll(this.masterX, this.masterY, this.masterX,
            this.masterY + 1, this.masterX + 1, this.masterY + 1, this.masterX + 1, this.masterY);
        this.square.setCursor(Cursor.CROSSHAIR);
        this.square.setOnMouseMoved(this.getOnMouseMovedEventHandler());
        this.square.setOnMousePressed(this.getOnMousePressedEventHandler());
        this.square.setOnMouseDragged(this.getOnMouseDraggedEventHandler());
        this.square.setOnMouseReleased(this.getOnMouseReleasedEventHandler());


        this.paintController.getDrawingController().addShape(this.square);
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
        width = Math.min(width, height);
        height = Math.min(width, height);

        if (tempX < this.masterX) {
          topX = this.masterX - width;
        }

        if (tempY < this.masterY) {
          topY = this.masterY - height;
        }

        this.square.getPoints().clear();
        this.square.getPoints().addAll(topX, topY, topX, topY + height, topX + width,
            topY + height, topX + width, topY);
      }

      this.homeScene.getStatusBar().updateCoordinates(tempX, tempY);
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
    return event -> {
      this.paintController.getGUIController().getGuiHelper().setSelectedShape(this.square);

      HistoryController.getInstance(this.paintController).createHistoryEntry();

      this.square = null;
    };
  }
}
