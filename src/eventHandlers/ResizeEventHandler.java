package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import view.focusOutline.ResizeAnchor;

public class ResizeEventHandler extends MouseEventHandler {

  private double mouseMasterX;
  private double mouseMasterY;

  private double rectangleTopLeftX;
  private double rectangleTopLeftY;

  private double originalWidth;
  private double originalHeight;

  private Rectangle highlightedRectangle;

  public ResizeEventHandler(PaintController padoubleController) {
    super(padoubleController);
  }

  @Override
  public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
    return event -> {
      this.setDelta(false);

      this.mouseMasterX = event.getX();
      this.mouseMasterX = event.getY();

      this.highlightedRectangle = this.paintController.getGUIController().getGuiHelper()
          .getFocusOutline().getHighlightedRectangle();

      this.rectangleTopLeftX = this.highlightedRectangle.getX();
      this.rectangleTopLeftY = this.highlightedRectangle.getY();

      this.originalWidth = this.highlightedRectangle.getWidth();
      this.originalHeight = this.highlightedRectangle.getHeight();
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

      ResizeAnchor resizeAnchor = (ResizeAnchor) event.getSource();

      double deltaX = tempX - this.mouseMasterX;
      double deltaY = tempY - this.mouseMasterY;

      switch (resizeAnchor.getCursor().toString()) {
        case "NW_RESIZE": {
          double posX = this.rectangleTopLeftX + deltaX;
          posX = Math.min(posX, this.rectangleTopLeftX + this.originalWidth - 1);
          this.highlightedRectangle.setX(posX);

          double width = (this.rectangleTopLeftX + this.originalWidth) - posX;
          this.highlightedRectangle.setWidth(width);

          double ypos = this.rectangleTopLeftY + deltaY;
          ypos = Math.min(ypos, this.rectangleTopLeftY + this.originalHeight - 1);
          this.highlightedRectangle.setY(ypos);

          double height = (this.rectangleTopLeftY + this.originalHeight) - ypos;
          this.highlightedRectangle.setHeight(height);

          break;
        }
        case "NE_RESIZE": {
          double width = this.originalWidth + deltaX;
          width = Math.max(width, 1);
          this.highlightedRectangle.setWidth(width);

          double ypos = this.rectangleTopLeftY + deltaY;
          ypos = Math.min(ypos, this.rectangleTopLeftY + this.originalHeight - 1);
          this.highlightedRectangle.setY(ypos);

          double height = (this.rectangleTopLeftY + this.originalHeight) - ypos;
          this.highlightedRectangle.setHeight(height);

          break;
        }
        case "SE_RESIZE": {
          double width = this.originalWidth + deltaX;
          width = Math.max(width, 1);
          this.highlightedRectangle.setWidth(width);

          double height = this.originalHeight + deltaY;
          height = Math.max(height, 1);
          this.highlightedRectangle.setHeight(height);

          break;
        }
        case "SW_RESIZE": {
          double xpos = this.rectangleTopLeftX + deltaX;
          xpos = Math.min(xpos, this.rectangleTopLeftX + this.originalWidth - 1);
          this.highlightedRectangle.setX(xpos);

          double width = (this.rectangleTopLeftX + this.originalWidth) - xpos;
          this.highlightedRectangle.setWidth(width);

          double height = this.originalHeight + deltaY;
          height = Math.max(height, 1);
          this.highlightedRectangle.setHeight(height);

          break;
        }

        case "N_RESIZE": {
          double ypos = this.rectangleTopLeftY + deltaY;
          ypos = Math.min(ypos, this.rectangleTopLeftY + this.originalHeight - 1);
          this.highlightedRectangle.setY(ypos);

          double height = (this.rectangleTopLeftY + this.originalHeight) - ypos;
          this.highlightedRectangle.setHeight(height);

          break;
        }
        case "E_RESIZE": {
          double width = this.originalWidth + deltaX;
          width = Math.max(width, 1);
          this.highlightedRectangle.setWidth(width);

          break;
        }
        case "S_RESIZE": {
          double height = this.originalHeight + deltaY;
          height = Math.max(height, 1);
          this.highlightedRectangle.setHeight(height);

          break;
        }
        case "W_RESIZE": {
          double xpos = this.rectangleTopLeftX + deltaX;
          xpos = Math.min(xpos, this.rectangleTopLeftX + this.originalWidth - 1);
          this.highlightedRectangle.setX(xpos);

          double width = (this.rectangleTopLeftX + this.originalWidth) - xpos;
          this.highlightedRectangle.setWidth(width);

          break;
        }
      }

      this.setDelta(true);
    };
  }

}
