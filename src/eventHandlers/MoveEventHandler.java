package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.GUIHelper;
import model.Shape;
import model.shapes.Rectangle;

public class MoveEventHandler extends MouseEventHandler {

  private double selectedX;
  private double selectedY;

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
        // GUIState guiState = mainController.getGUIController().getGuiState();

        if (!(event.getSource() instanceof Rectangle)
            || !((Rectangle) event.getSource()).isHighlighted()) {
          // FocusOutline focusOutline = guiState.getFocusOutline();
          // Rectangle focusRectangle = focusOutline.getFocusRectangle();

          // focusRectangle.fireEvent(event);
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
        GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();
        if(!(event.getSource() instanceof Rectangle)){
          
        }
      }
    };
  }

}
