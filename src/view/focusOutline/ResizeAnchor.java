package view.focusOutline;

import controller.PaintController;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    this.setWidth(HomeConstants.RESIZE_ANCHOR_WIDTH);
    this.setHeight(HomeConstants.RESIZE_ANCHOR_HEIGHT);
    this.setStrokeWidth(HomeConstants.RESIZE_ANCHOR_STROKEWIDTH);
    this.setFill(Color.WHITE);
    this.setStroke(HomeConstants.FOCUS_OUTLINE_COLOR);
    this.setCursor(cursor);
  }

  private void mode(Cursor cursor, Rectangle rectangle) {
    switch (cursor.toString()) {
      case "NW_RESIZE":
        this.xProperty()
            .bind(rectangle.xProperty().subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty()
            .bind(rectangle.yProperty().subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
      case "NE_RESIZE":
        this.xProperty().bind(rectangle.xProperty().add(rectangle.widthProperty())
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty()
            .bind(rectangle.yProperty().subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "SE_RESIZE":
        this.xProperty().bind(rectangle.xProperty().add(rectangle.widthProperty())
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty().bind(rectangle.yProperty().add(rectangle.heightProperty())
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "SW_RESIZE":
        this.xProperty()
            .bind(rectangle.xProperty().subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty().bind(rectangle.yProperty().add(rectangle.heightProperty())
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
      case "N_RESIZE":
        this.xProperty().bind(rectangle.xProperty().add(rectangle.widthProperty().divide(2))
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty()
            .bind(rectangle.yProperty().subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
      case "E_RESIZE":
        this.xProperty().bind(rectangle.xProperty().add(rectangle.widthProperty())
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty().bind(rectangle.yProperty().add(rectangle.heightProperty().divide(2))
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "S_RESIZE":
        this.xProperty().bind(rectangle.xProperty().add(rectangle.widthProperty().divide(2))
            .subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty().bind(rectangle.yProperty().add(rectangle.heightProperty())
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;

      case "W_RESIZE":
        this.xProperty()
            .bind(rectangle.xProperty().subtract(HomeConstants.RESIZE_ANCHOR_WIDTH / 2));
        this.yProperty().bind(rectangle.yProperty().add(rectangle.heightProperty().divide(2))
            .subtract(HomeConstants.RESIZE_ANCHOR_HEIGHT / 2));
        break;
    }
    this.getTransforms().addAll(rectangle.getTransforms());
  }
}
