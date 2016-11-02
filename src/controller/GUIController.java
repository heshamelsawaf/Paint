package controller;

import eventHandlers.EllipseEventHandler;
import eventHandlers.LineEventHandler;
import eventHandlers.MouseEventHandler;
import eventHandlers.RectangleEventHandler;
import eventHandlers.SelectEventHandler;
import eventHandlers.SquareEventHandler;
import javafx.scene.Cursor;
import javafx.stage.Stage;
import model.GUIHelper;
import util.Message;
import view.DrawingTools;
import view.main.DrawingArea;
import view.main.Home;
import view.newDrawing.NewDrawingStage;

public class GUIController {

  private PaintController paintController;
  private GUIHelper guiHelper;
  private Home home;

  public GUIController(PaintController paintController, Stage primaryStage) {

    this.paintController = paintController;
    this.guiHelper = new GUIHelper(paintController);
    this.home = new Home(paintController, primaryStage);
  }

  public void openHome() {
    this.home.open();
  }

  public void closeHome() {
    this.home.close();
  }

  public void openNewDrawingDialog(Stage parentStage) {
    NewDrawingStage newDrawingStage = new NewDrawingStage(this.paintController, parentStage);
    newDrawingStage.show();
    newDrawingStage.requestFocus();
  }

  public void setSelectedTool(DrawingTools drawingTool) {
    DrawingArea drawingArea = this.getHome().getHomeScene().getDrawingArea();

    MouseEventHandler mouseEventHandler = null;

    switch (drawingTool) {
      case SELECT:
        mouseEventHandler = new SelectEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.DEFAULT);
        break;
      case RECTANGLE:
        this.getHome().getHomeScene().displayBorderMessage(Message.PERFECT_SQUARE);
        mouseEventHandler = new RectangleEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        break;
      case SQUARE:
    	mouseEventHandler = new SquareEventHandler(this.paintController);
    	drawingArea.setCursor(Cursor.CROSSHAIR);
    	break;
      case ELLIPSE:
        this.getHome().getHomeScene().displayBorderMessage(Message.PERFECT_CIRCLE);
        mouseEventHandler = new EllipseEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        break;
      case LINE:
        this.getHome().getHomeScene().displayBorderMessage(Message.PERFECT_LINE);
        mouseEventHandler = new LineEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        break;
      default:
        break;
    }
    drawingArea.setMouseEventHandlers(mouseEventHandler);
    this.guiHelper.setSelectedDrawTool(drawingTool);
  }

  public Home getHome() {
    return this.home;
  }

  public GUIHelper getGuiHelper() {
    return guiHelper;
  }
}
