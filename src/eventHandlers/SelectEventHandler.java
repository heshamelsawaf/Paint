package eventHandlers;

import controller.PaintController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.GUIHelper;
import model.Shape;
import model.shapes.Line;
import view.StrokeWidth;
import view.main.DrawingArea;

public class SelectEventHandler extends MouseEventHandler {

  private MoveEventHandler moveEventHandler;

  public SelectEventHandler(PaintController paintController) {
    super(paintController);
    this.moveEventHandler = new MoveEventHandler(paintController);
  }

  @Override
  public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
    return event -> {
      this.setDelta(false);
      if (event.isPrimaryButtonDown()) {
        GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();
        guiHelper.setSelectedShape(null);
        if (!(event.getSource() instanceof DrawingArea)) {
          Shape shape = (Shape) event.getSource();
          if (!(shape instanceof Line)) {
            guiHelper.setFillColor((Color) shape.getFill());
          }
          guiHelper.setStrokeColor((Color) shape.getStroke());
          guiHelper.setStrokeWidth(StrokeWidth.fromInteger((int) shape.getStrokeWidth()));
          guiHelper.setSelectedShape(shape);
          this.moveEventHandler.getOnMousePressedEventHandler().handle(event);
        }
      }
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
    return event -> {
      super.getOnMouseDraggedEventHandler().handle(event);
      if (event.isPrimaryButtonDown()) {
        this.moveEventHandler.getOnMouseDraggedEventHandler().handle(event);
      }
      this.setDelta(true);
    };
  }

  @Override
  public EventHandler<MouseEvent> getOnMouseExitedEventHandler() {
    return null;
  }

  public EventHandler<MouseEvent> getMousePressedOnHighlightedEventHandler() {
    return event -> {
      if (event.isPrimaryButtonDown()) {
        this.moveEventHandler.getMousePressedOnHighlightedEventHandler().handle(event);
      }
    };
  }
}
