package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import eventHandlers.CircleEventHandler;
import eventHandlers.EllipseEventHandler;
import eventHandlers.LineEventHandler;
import eventHandlers.MouseEventHandler;
import eventHandlers.RectangleEventHandler;
import eventHandlers.SelectEventHandler;
import eventHandlers.SquareEventHandler;
import eventHandlers.TriangleEventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Drawing;
import model.GUIHelper;
import model.Shape;
import util.HomeConstants;
import util.Message;
import view.DrawingTools;
import view.help.HelpStage;
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

  public void openHelpDialog(Stage parentStage) {
    HelpStage helpStage = new HelpStage(this.paintController, parentStage);
    helpStage.show();
    helpStage.requestFocus();
  }

  public void setSelectedTool(DrawingTools drawingTool) {
    DrawingArea drawingArea = this.getHome().getHomeScene().getDrawingArea();
    Drawing drawing = this.paintController.getDrawingController().getDrawing();
    List<Shape> shapes = drawing.getShapes();

    MouseEventHandler mouseEventHandler = null;

    switch (drawingTool) {
      case SELECT:
        this.getHome().getHomeScene().hideBorderMessage();
        mouseEventHandler = new SelectEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.DEFAULT);
        for (Shape shape : shapes) {
          shape.setCursor(Cursor.MOVE);
        }
        break;
      case RECTANGLE:
        this.getHome().getHomeScene().displayBorderMessage(Message.PERFECT_SQUARE);
        mouseEventHandler = new RectangleEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        for (Shape shape : shapes) {
          shape.setCursor(Cursor.CROSSHAIR);
        }
        break;
      case SQUARE:
        this.getHome().getHomeScene().hideBorderMessage();
        mouseEventHandler = new SquareEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        for (Shape shape : shapes) {
          shape.setCursor(Cursor.CROSSHAIR);
        }
        break;
      case ELLIPSE:
        this.getHome().getHomeScene().displayBorderMessage(Message.PERFECT_CIRCLE);
        mouseEventHandler = new EllipseEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        for (Shape shape : shapes) {
          shape.setCursor(Cursor.CROSSHAIR);
        }
        break;
      case CIRCLE:
        this.getHome().getHomeScene().hideBorderMessage();
        mouseEventHandler = new CircleEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        for (Shape shape : shapes) {
          shape.setCursor(Cursor.CROSSHAIR);
        }
        break;
      case LINE:
        this.getHome().getHomeScene().displayBorderMessage(Message.PERFECT_LINE);
        mouseEventHandler = new LineEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        for (Shape shape : shapes) {
          shape.setCursor(Cursor.CROSSHAIR);
        }
        break;
      case TRIANGLE:
        this.getHome().getHomeScene().displayBorderMessage(Message.EQUILATERAL_TRIANGLE);
        mouseEventHandler = new TriangleEventHandler(this.paintController);
        drawingArea.setCursor(Cursor.CROSSHAIR);
        for (Shape shape : shapes) {
          shape.setCursor(Cursor.CROSSHAIR);
        }
        break;
      default:
        break;
    }
    drawingArea.setMouseEventHandlers(mouseEventHandler);
    for (Shape shape : shapes) {
      shape.setOnMouseMoved(mouseEventHandler.getOnMouseMovedEventHandler());
      shape.setOnMousePressed(mouseEventHandler.getOnMousePressedEventHandler());
      shape.setOnMouseDragged(mouseEventHandler.getOnMouseDraggedEventHandler());
      shape.setOnMouseReleased(mouseEventHandler.getOnMouseReleasedEventHandler());

    }
    this.guiHelper.setSelectedDrawTool(drawingTool);
  }

  public Home getHome() {
    return this.home;
  }

  public GUIHelper getGuiHelper() {
    return guiHelper;
  }

  public boolean saveDrawing(Stage parentStage) {
    if (this.getGuiHelper().getFile() != null) {
      try {
        this.paintController.getDrawingController().saveDrawingAsXML(this.getGuiHelper().getFile());
        return true;
      } catch (IOException e) {
        e.printStackTrace();
        this.paintController.getDrawingController().getDrawing().setSaved(false);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(parentStage);
        alert.setContentText(Message.FILECHOOSER_SAVE_ERROR + e.getMessage());
        alert.showAndWait();
      }
      return false;
    }
    return this.saveDrawingAs(parentStage);
  }

  public boolean saveDrawingAs(Stage parentStage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(Message.FILECHOOSER_TITLE);
    fileChooser.setInitialDirectory(this.guiHelper.getLastUsedDirectory());
    fileChooser.setInitialFileName(
        this.paintController.getDrawingController().getDrawing().getTitle() + ".xml");

    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Extensible Markup Language (*.xml)", "*.xml", "*.XML"),
        new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
    File file = fileChooser.showSaveDialog(parentStage);
    if (file != null) {
      if (file.getParentFile().isDirectory()) {
        this.guiHelper.setLastUsedDirectory(file.getParentFile());
      }

      try {
        this.paintController.getDrawingController().saveDrawingAsXML(file);
        this.guiHelper.setFile(file);
        return true;
      } catch (IOException e) {
        e.printStackTrace();
        this.paintController.getDrawingController().getDrawing().setSaved(false);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(parentStage);
        alert.setContentText(Message.FILECHOOSER_SAVE_ERROR + e.getMessage());
        alert.showAndWait();
      }
    }
    return false;
  }
}
