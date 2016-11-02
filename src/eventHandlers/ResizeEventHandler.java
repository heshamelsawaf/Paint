package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ResizeEventHandler extends MouseEventHandler {

  public ResizeEventHandler(PaintController paintController) {
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
