package view.focusOutline;

import controller.PaintController;
import javafx.scene.shape.Ellipse;
import model.shapes.Rectangle;
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
    double width = Math.abs(rectangle.getPoints().get(0) - rectangle.getPoints().get(6));
    double height = Math.abs(rectangle.getPoints().get(1) - rectangle.getPoints().get(3));
    this.centerXProperty().bind(rectangle.translateXProperty().add(width / 2));
    this.centerYProperty().bind(rectangle.translateYProperty().add(height / 2));
    this.getTransforms().addAll(rectangle.getTransforms());
  }

}
