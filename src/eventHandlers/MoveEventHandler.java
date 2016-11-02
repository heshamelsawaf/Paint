package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.GUIHelper;
import view.focusOutline.FocusOutline;

public class MoveEventHandler extends MouseEventHandler {

  private double deltaX;
  private double deltaY;

  public MoveEventHandler(PaintController paintController) {
    super(paintController);
  }

  @Override
  public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
    return event -> {
      this.setDelta(false);

      if (event.isPrimaryButtonDown()) {
        GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();

        if (!(event.getSource() instanceof Rectangle)
            || !((Rectangle) event.getSource()).getFill().equals(Color.TRANSPARENT)) {
          FocusOutline focusOutline = guiHelper.getFocusOutline();
          Rectangle highlightedRectangle = focusOutline.getHighlightedRectangle();

          highlightedRectangle.fireEvent(event);
        }
      }
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseExitedEventHandler() {
    return null;
  }

  public EventHandler<MouseEvent> getMousePressedOnHighlightedEventHandler() {
    return event -> {
      this.setDelta(false);
      if (event.isPrimaryButtonDown()) {
        Rectangle rectangle = (Rectangle) event.getSource();
        this.deltaX = event.getX() - rectangle.getX();
        this.deltaY = event.getY() - rectangle.getY();
      }
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
    return event -> {
      super.getOnMouseDraggedEventHandler().handle(event);
      if (event.isPrimaryButtonDown()) {
        if (!(event.getSource() instanceof Rectangle)
            || !((Rectangle) event.getSource()).getFill().equals(Color.TRANSPARENT)) {
          GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();

          FocusOutline focusOutline = guiHelper.getFocusOutline();
          Rectangle highlightedRectangle = focusOutline.getHighlightedRectangle();

          highlightedRectangle.fireEvent(event);
          return;
        }
        Rectangle rectangle = (Rectangle) event.getSource();
        rectangle.setX(event.getX() - deltaX);
        rectangle.setY(event.getY() - deltaY);
        this.setDelta(true);
      }
    };
  }

}
