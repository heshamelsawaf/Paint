package view.focusOutline;

import controller.PaintController;
import eventHandlers.MouseEventHandler;
import eventHandlers.RotateEventHandler;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import util.HomeConstants;

public class RotateAnchor extends Ellipse {

  private PaintController paintController;

  public RotateAnchor(PaintController paintController, Rectangle rectangle) {
    super();

    this.paintController = paintController;
    this.initAnchor();
    this.mode(rectangle);
  }

  private void initAnchor() {
    this.setRadiusX(HomeConstants.ROTATE_ANCHOR_RADIUS_X / 2);
    this.setRadiusY(HomeConstants.ROTATE_ANCHOR_RADIUS_Y / 2);

    this.setFill(HomeConstants.FOCUS_OUTLINE_COLOR);

    // setCursor(Cursor.cursor(ClassLoader.getSystemResource("cur/rotate.png").toExternalForm()));
  }

  private void mode(Rectangle rectangle) {
    MouseEventHandler rotateEventHandler = new RotateEventHandler(this.paintController);
    this.setOnMousePressed(rotateEventHandler.getOnMousePressedEventHandler());
    this.setOnMouseDragged(rotateEventHandler.getOnMouseDraggedEventHandler());
    this.setOnMouseReleased(rotateEventHandler.getOnMouseReleasedEventHandler());

    this.centerXProperty().bind(rectangle.xProperty().add(rectangle.widthProperty().divide(2)));
    this.centerYProperty().bind(rectangle.yProperty().add(rectangle.heightProperty().divide(2)));

    this.getTransforms().addAll(rectangle.getTransforms());
  }

}
