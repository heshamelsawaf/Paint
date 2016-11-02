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
import model.shapes.Rectangle;
import util.HomeConstants;

public class FocusOutline {

  private PaintController paintController;
  private Shape selectedShape;
  private Rectangle highlightedRectangle;

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
    this.highlightedRectangle = new Rectangle();
    this.highlightedRectangle.setHighlighted(true);
    this.highlightedRectangle.setStroke(HomeConstants.FOCUS_OUTLINE_COLOR);
    this.highlightedRectangle.setFill(Color.TRANSPARENT);
    this.highlightedRectangle.getStrokeDashArray().addAll(5.0, 5.0);
    this.highlightedRectangle.setStrokeWidth(HomeConstants.FOCUS_OUTLINE_WIDTH);

    this.highlightedRectangle.setCursor(Cursor.MOVE);


    if (this.selectedShape instanceof Polygon) {
      Polygon polygon = (Polygon) this.selectedShape;
      this.highlightedRectangle.getPoints().addAll(polygon.getPoints());

      polygon.scaleXProperty().bind(this.highlightedRectangle.scaleXProperty());
      polygon.scaleYProperty().bind(this.highlightedRectangle.scaleYProperty());

      polygon.translateXProperty().bind(this.highlightedRectangle.translateXProperty());
      polygon.translateYProperty().bind(this.highlightedRectangle.translateYProperty());
    } else if (this.selectedShape instanceof Ellipse) {
      Ellipse circle = (Ellipse) this.selectedShape;
      this.highlightedRectangle.getPoints().addAll(circle.getCenterX() - circle.getRadiusX(),
          circle.getCenterY() - circle.getRadiusY(), circle.getCenterX() - circle.getRadiusX(),
          circle.getCenterY() + circle.getRadiusY(), circle.getCenterX() + circle.getRadiusX(),
          circle.getCenterY() + circle.getRadiusY(), circle.getCenterX() + circle.getRadiusX(),
          circle.getCenterY() - circle.getRadiusY());

      circle.scaleXProperty().bind(this.highlightedRectangle.scaleXProperty());
      circle.scaleYProperty().bind(this.highlightedRectangle.scaleYProperty());

      circle.translateXProperty().bind(this.highlightedRectangle.translateXProperty());
      circle.translateYProperty().bind(this.highlightedRectangle.translateYProperty());
    } else if (this.selectedShape instanceof Line) {
      Line line = (Line) this.selectedShape;

      this.highlightedRectangle.getPoints().addAll(line.getStartX(), line.getStartY(),
          line.getStartX(), line.getEndY(), line.getEndX(), line.getStartY(), line.getEndX(),
          line.getEndY());

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

  public Rectangle getHighlightedRectangle() {
    return this.highlightedRectangle;
  }

  public List<ResizeAnchor> getResizeAnchors() {
    return this.resizeAnchors;
  }

  public RotateAnchor getRotateAnchor() {
    return this.rotateAnchor;
  }

}
