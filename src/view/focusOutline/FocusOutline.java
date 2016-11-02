package view.focusOutline;

import java.util.ArrayList;
import java.util.List;

import controller.PaintController;
import eventHandlers.SelectEventHandler;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import model.Shape;
import model.shapes.Ellipse;
import model.shapes.Line;
import model.shapes.Polygon;
import util.HomeConstants;

public class FocusOutline {

  private PaintController paintController;
  private Shape selectedShape;
  private javafx.scene.shape.Rectangle highlightedRectangle;

  private List<ResizeAnchor> resizeAnchors;
  private RotateAnchor rotateAnchor;

  public FocusOutline(PaintController paintController, Shape shape) {
    this.paintController = paintController;

    this.selectedShape = shape;
    this.buildHighlightedRectangle();
    this.buildResizeAnchors();
    this.buildRotateAnchor();
  }

  private void buildHighlightedRectangle() {
    this.highlightedRectangle = new javafx.scene.shape.Rectangle();
    this.highlightedRectangle.setStroke(HomeConstants.FOCUS_OUTLINE_COLOR);
    this.highlightedRectangle.setFill(Color.TRANSPARENT);
    this.highlightedRectangle.getStrokeDashArray().addAll(5.0, 5.0);
    this.highlightedRectangle.setStrokeWidth(HomeConstants.FOCUS_OUTLINE_WIDTH);

    this.highlightedRectangle.setCursor(Cursor.MOVE);


    if (this.selectedShape instanceof Polygon) {
      Polygon polygon = (Polygon) this.selectedShape;
      double width = Math.abs(polygon.getPoints().get(0) - polygon.getPoints().get(6));
      double height = Math.abs(polygon.getPoints().get(1) - polygon.getPoints().get(3));

      this.highlightedRectangle.setX(polygon.getPoints().get(0));
      this.highlightedRectangle.setY(polygon.getPoints().get(1));
      this.highlightedRectangle.setWidth(width);
      this.highlightedRectangle.setHeight(height);
      polygon.scaleXProperty().bind(this.highlightedRectangle.scaleXProperty());
      polygon.scaleYProperty().bind(this.highlightedRectangle.scaleYProperty());

      polygon.translateXProperty().bind(this.highlightedRectangle.translateXProperty());
      polygon.translateYProperty().bind(this.highlightedRectangle.translateYProperty());
    } else if (this.selectedShape instanceof Ellipse) {
      Ellipse circle = (Ellipse) this.selectedShape;
      this.highlightedRectangle.setX(circle.getCenterX() - circle.getRadiusX());
      this.highlightedRectangle.setY(circle.getCenterY() - circle.getRadiusY());
      this.highlightedRectangle.setWidth(2 * circle.getRadiusX());
      this.highlightedRectangle.setHeight(2 * circle.getRadiusY());
      
      circle.centerXProperty().bind(this.highlightedRectangle.xProperty().add(circle.radiusXProperty()));
      circle.centerYProperty().bind(this.highlightedRectangle.yProperty().add(circle.radiusYProperty()));

      circle.radiusXProperty().bind(this.highlightedRectangle.widthProperty().divide(2));
      circle.radiusYProperty().bind(this.highlightedRectangle.heightProperty().divide(2));
    } else if (this.selectedShape instanceof Line) {
      Line line = (Line) this.selectedShape;

      if (line.getStartX() < line.getEndX()) {
        this.highlightedRectangle.setX(line.getStartX());
        this.highlightedRectangle.setWidth(line.getEndX() - line.getStartX());
      } else {
        this.highlightedRectangle.setX(line.getEndX());
        this.highlightedRectangle.setWidth(line.getStartX() - line.getEndX());
      }

      if (line.getStartY() < line.getEndY()) {
        this.highlightedRectangle.setY(line.getStartY());
        this.highlightedRectangle.setHeight(line.getEndY() - line.getStartY());
      } else {
        this.highlightedRectangle.setY(line.getEndY());
        this.highlightedRectangle.setHeight(line.getStartY() - line.getEndY());
      }
      line.scaleXProperty().bind(this.highlightedRectangle.scaleXProperty());
      line.scaleYProperty().bind(this.highlightedRectangle.scaleYProperty());

      line.translateXProperty().bind(this.highlightedRectangle.translateXProperty());
      line.translateYProperty().bind(this.highlightedRectangle.translateYProperty());
    }

    if (this.selectedShape != null) {
      this.highlightedRectangle.getTransforms().addAll(this.selectedShape.getTransforms());
    }

    SelectEventHandler selectEventHandler = new SelectEventHandler(this.paintController);

    this.highlightedRectangle.setOnMouseDragged(selectEventHandler.getOnMouseDraggedEventHandler());
    this.highlightedRectangle.setOnMouseMoved(selectEventHandler.getOnMouseMovedEventHandler());
    this.highlightedRectangle
        .setOnMousePressed(selectEventHandler.getMousePressedOnHighlightedEventHandler());
    this.highlightedRectangle
        .setOnMouseReleased(selectEventHandler.getOnMouseReleasedEventHandler());

  }

  private void buildResizeAnchors() {
    this.resizeAnchors = new ArrayList<>();

    ResizeAnchor nwResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.NW_RESIZE, this.highlightedRectangle);
    ResizeAnchor neResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.NE_RESIZE, this.highlightedRectangle);
    ResizeAnchor seResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.SE_RESIZE, this.highlightedRectangle);
    ResizeAnchor swResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.SW_RESIZE, this.highlightedRectangle);

    ResizeAnchor nResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.N_RESIZE, this.highlightedRectangle);
    ResizeAnchor eResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.E_RESIZE, this.highlightedRectangle);
    ResizeAnchor sResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.S_RESIZE, this.highlightedRectangle);
    ResizeAnchor wResizeAnchor =
        new ResizeAnchor(this.paintController, Cursor.W_RESIZE, this.highlightedRectangle);

    this.resizeAnchors.add(nwResizeAnchor);
    this.resizeAnchors.add(neResizeAnchor);
    this.resizeAnchors.add(seResizeAnchor);
    this.resizeAnchors.add(swResizeAnchor);

    this.resizeAnchors.add(nResizeAnchor);
    this.resizeAnchors.add(eResizeAnchor);
    this.resizeAnchors.add(sResizeAnchor);
    this.resizeAnchors.add(wResizeAnchor);
  }

  private void buildRotateAnchor() {
    this.rotateAnchor = new RotateAnchor(this.paintController, this.highlightedRectangle);
  }

  public javafx.scene.shape.Rectangle getHighlightedRectangle() {
    return this.highlightedRectangle;
  }

  public List<ResizeAnchor> getResizeAnchors() {
    return this.resizeAnchors;
  }

  public RotateAnchor getRotateAnchor() {
    return this.rotateAnchor;
  }

}
