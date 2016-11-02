package eventHandlers;

import controller.HistoryController;
import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import model.GUIHelper;
import model.shapes.Triangle;

public class TriangleEventHandler extends MouseEventHandler {

  private Triangle triangle;

  private double masterX;
  private double masterY;

  public TriangleEventHandler(PaintController paintController) {
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

        this.triangle = new Triangle();
        this.triangle.setFill(guiHelper.getFillColor());
        this.triangle.setStroke(guiHelper.getStrokeColor());
        this.triangle.setStrokeWidth(guiHelper.getStrokeWidth().getStrokeWidthAsInt());

        this.triangle.getPoints().addAll(this.masterX, this.masterY, this.masterX,
            this.masterY + 1, this.masterX + 1, this.masterY + 1, this.masterX + 1, this.masterY);
        this.triangle.setCursor(Cursor.CROSSHAIR);
        this.triangle.setOnMouseMoved(this.getOnMouseMovedEventHandler());
        this.triangle.setOnMousePressed(this.getOnMousePressedEventHandler());
        this.triangle.setOnMouseDragged(this.getOnMouseDraggedEventHandler());
        this.triangle.setOnMouseReleased(this.getOnMouseReleasedEventHandler());


        this.paintController.getDrawingController().addShape(this.triangle);
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

        //this.square.getPoints().clear();
        //this.square.getPoints().addAll(topX, topY, topX, topY + height, topX + width,
          //  topY + height, topX + width, topY);
      }

      this.homeScene.getStatusBar().updateCoordinates(tempX, tempY);
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
    return event -> {
      this.paintController.getGUIController().getGuiHelper().setSelectedShape(this.triangle);

      HistoryController.getInstance(this.paintController).createHistoryEntry();

      this.triangle = null;
    };
  }
}
