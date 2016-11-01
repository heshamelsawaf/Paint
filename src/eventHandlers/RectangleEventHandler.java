package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class RectangleEventHandler extends MouseEventHandler {

  public RectangleEventHandler(PaintController paintController) {
    super(paintController);
    // TODO Auto-generated constructor stub
  }

  @Override
  public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseExitedEventHandler() {
    // TODO Auto-generated method stub
    return null;
  }

}
