package view.focusOutline;

import controller.PaintController;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import model.shapes.Rectangle;
import util.HomeConstants;

public class ResizeAnchor extends Rectangle {

  private PaintController paintController;

  public ResizeAnchor(PaintController paintController, Cursor cursor, Rectangle rectangle) {
    super();

    this.paintController = paintController;
    this.initAnchor(cursor);
    this.mode(cursor, rectangle);
  }

  private void initAnchor(Cursor cursor) {
    this.getPoints().addAll(0.0, 0.0, 0.0, HomeConstants.RESIZE_ANCHOR_HEIGHT,
        HomeConstants.RESIZE_ANCHOR_WIDTH, HomeConstants.RESIZE_ANCHOR_HEIGHT, 0.0,
        HomeConstants.RESIZE_ANCHOR_WIDTH);
    this.setStrokeWidth(HomeConstants.RESIZE_ANCHOR_STROKEWIDTH);
    this.setFill(Color.WHITE);
    this.setStroke(HomeConstants.FOCUS_OUTLINE_COLOR);
    this.setCursor(cursor);
  }

  private void mode(Cursor cursor, Rectangle rectangle) {
    double width = Math.abs(rectangle.getPoints().get(0) - rectangle.getPoints().get(6));
    double height = Math.abs(rectangle.getPoints().get(1) - rectangle.getPoints().get(3));
    switch (cursor.toString()) {
      case "NW_RESIZE":
        this.translateXProperty()
            .bind(rectangle.translateXProperty().subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty()
            .bind(rectangle.translateYProperty().subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
      case "NE_RESIZE":
        this.translateXProperty().bind(rectangle.translateXProperty().add(width)
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty()
            .bind(rectangle.translateYProperty().subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "SE_RESIZE":
        this.translateXProperty().bind(rectangle.translateXProperty().add(width)
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty().bind(rectangle.translateYProperty().add(height)
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "SW_RESIZE":
        this.translateXProperty()
            .bind(rectangle.translateXProperty().subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty().bind(rectangle.translateYProperty().add(height)
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
      case "N_RESIZE":
        this.translateXProperty().bind(rectangle.translateXProperty().add(width / 2)
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty()
            .bind(rectangle.translateYProperty().subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
      case "E_RESIZE":
        this.translateXProperty().bind(rectangle.translateXProperty().add(width)
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty().bind(rectangle.translateYProperty().add(height / 2)
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "S_RESIZE":
        this.translateXProperty().bind(rectangle.translateXProperty().add(width / 2)
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty().bind(rectangle.translateYProperty().add(height)
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "W_RESIZE":
        this.translateXProperty()
            .bind(rectangle.translateXProperty().subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.translateYProperty().bind(rectangle.translateYProperty().add(height / 2)
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
    }
    this.getTransforms().addAll(rectangle.getTransforms());
  }
}
