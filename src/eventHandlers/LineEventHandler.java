package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LineEventHandler extends MouseEventHandler {

  public LineEventHandler(PaintController paintController) {
    super(paintController);
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
