package view.main;

import java.util.Iterator;
import java.util.List;

import controller.PaintController;
import eventHandlers.KeystrokeEventHandler;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import model.Drawing;
import model.GUIHelper;
import model.Observer;
import model.Shape;
import model.shapes.Line;
import view.focusOutline.FocusOutline;

public class HomeScene extends Scene implements Observer {

  private PaintController paintController;
  private Home home;

  private BorderPane borderPane;

  private VBox barsPane;
  private MenuBar menuBar;
  private ToolBar toolBar;
  private StatusBar statusBar;
  private BorderMessage borderMessage;

  private DrawingArea drawingArea;
  private Group drawingAreaGroup;
  private Group drawingAreaZoomGroup;



  public HomeScene(PaintController paintController, Home home) {
    super(new BorderPane(), Screen.getPrimary().getVisualBounds().getWidth(),
        Screen.getPrimary().getVisualBounds().getHeight());


    this.paintController = paintController;
    this.home = home;

    this.borderPane = new BorderPane();
    this.barsPane = new VBox();

    this.menuBar = new MenuBar(this.paintController);
    this.toolBar = new ToolBar(this.paintController);

    this.showMenuBar(true);
    this.showToolBar(true);

    this.borderPane.setTop(this.barsPane);

    this.drawingAreaGroup = new Group();
    this.drawingAreaZoomGroup = new Group();
    this.drawingAreaZoomGroup.getChildren().add(this.drawingAreaGroup);

    StackPane stackPane = new StackPane(this.drawingAreaZoomGroup);
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setContent(stackPane);
//     scrollPane.setOnMouseMoved(event -> {
//     if (this.drawingArea != null) {
//     try {
//     this.drawingArea.fireEvent(event);
//     } catch (StackOverflowError e) {
//     e.printStackTrace();
//     }
//     }
//     });
//     scrollPane.setOnMouseDragged(event -> {
//     if (this.drawingArea != null) {
//     try {
//     this.drawingArea.fireEvent(event);
//     } catch (StackOverflowError e) {
//     e.printStackTrace();
//     }
//     }
//     });


    stackPane.minWidthProperty().bind(Bindings.createDoubleBinding(
        () -> scrollPane.getViewportBounds().getWidth(), scrollPane.viewportBoundsProperty()));
    stackPane.minHeightProperty().bind(Bindings.createDoubleBinding(
        () -> scrollPane.getViewportBounds().getHeight(), scrollPane.viewportBoundsProperty()));

    this.borderPane.setCenter(scrollPane);
    this.statusBar = new StatusBar(this.paintController);

    this.showStatusBar(true);

    KeystrokeEventHandler keyEventHandler = new KeystrokeEventHandler(paintController);
    this.setOnKeyPressed(keyEventHandler.getOnKeyPressedEventHandler());

    this.setRoot(this.borderPane);

  }

  public void displayBorderMessage(String message) {
    if (this.borderMessage != null) {
      this.hideBorderMessage();
    }
    this.borderMessage = new BorderMessage(this.paintController, message);
    this.getBarsPane().getChildren().add(2, this.borderMessage);
  }

  public void hideBorderMessage() {
    if (this.borderMessage != null) {
      this.getBarsPane().getChildren().remove(this.borderMessage);
      this.borderMessage = null;
    }
  }

  public void showMenuBar(boolean key) {
    if (key) {
      if (!this.barsPane.getChildren().contains(this.menuBar)) {
        this.barsPane.getChildren().add(0, this.menuBar);
      }
    } else {
      this.barsPane.getChildren().remove(this.menuBar);
    }

    this.getMenuBar().selectView_Toolbars_MenuBar(key);
  }

  public void showToolBar(boolean key) {
    if (key) {
      if (!this.barsPane.getChildren().contains(this.toolBar)) {
        this.barsPane.getChildren().add(1, this.toolBar);
      }
    } else {
      this.barsPane.getChildren().remove(this.toolBar);
    }

    this.getMenuBar().selectView_Toolbars_ToolBar(key);
  }

  public void showStatusBar(boolean key) {
    if (key) {
      this.borderPane.setBottom(this.statusBar);
    } else {
      this.borderPane.setBottom(null);
    }
    this.getMenuBar().selectView_Toolbars_StatusBar(key);
  }

  public void setupDrawingArea(double width, double height) {
    if (!this.drawingAreaGroup.getChildren().isEmpty()) {
      this.clearDrawingArea();
    }
    this.drawingArea = new DrawingArea(this.paintController, width, height);
    this.drawingAreaGroup.getChildren().add(this.drawingArea);
  }

  private void clearDrawingArea() {
    this.drawingArea = null;
    this.drawingAreaGroup.getChildren().clear();
  }

  public void activateControls(boolean key) {
    this.menuBar.takeControl(key);
    this.toolBar.takeControl(key);
  }

  public Home getHome() {
    return this.home;
  }

  public BorderPane getBorderPane() {
    return this.borderPane;
  }

  public VBox getBarsPane() {
    return this.barsPane;
  }

  public MenuBar getMenuBar() {
    return this.menuBar;
  }

  public ToolBar getToolBar() {
    return this.toolBar;
  }

  public BorderMessage getBorderMessage() {
    return this.borderMessage;
  }

  public DrawingArea getDrawingArea() {
    return this.drawingArea;
  }

  public StatusBar getStatusBar() {
    return this.statusBar;
  }

  public void setStatusBar(StatusBar statusBar) {
    this.statusBar = statusBar;
  }

  public void reset() {
    this.clearDrawingArea();
    this.home.setTitle(null);
  }

  private void updateZoom(double level) {
    this.drawingAreaGroup.setScaleX(level);
    this.drawingAreaGroup.setScaleY(level);
  }

  @Override
  public void update() {
    Drawing drawing = this.paintController.getDrawingController().getDrawing();
    GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();

    this.home.setTitle(drawing.getTitle());

    if (this.drawingArea != null) {
      this.drawingAreaGroup.getChildren().clear();
      this.drawingAreaGroup.getChildren().add(this.drawingArea);

      List<Shape> shapes = drawing.getShapes();
      for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext();) {
        Shape shape = (Shape) iterator.next();
        this.drawingAreaGroup.getChildren().add(shape.getNode());
      }
      if (guiHelper.getSelectedShape() != null) {
        Shape shape = guiHelper.getSelectedShape();

        FocusOutline focusOutline = guiHelper.getFocusOutline();
        Rectangle highlightedRectangle = focusOutline.getHighlightedRectangle();

        this.drawingAreaGroup.getChildren().add(highlightedRectangle);
        for (view.focusOutline.ResizeAnchor resizeAnchor : focusOutline.getResizeAnchors()) {
          this.drawingAreaGroup.getChildren().add(resizeAnchor);
        }
        this.drawingAreaGroup.getChildren().add(focusOutline.getRotateAnchor());

        if (!(shape instanceof Line)) {
          shape.setFill(guiHelper.getFillColor());
        }
        shape.setStroke(guiHelper.getStrokeColor());
        shape.setStrokeWidth(guiHelper.getStrokeWidth().getStrokeWidthAsInt());

        this.updateZoom(guiHelper.getZoomLevel());
        
        this.menuBar.shapeControl(guiHelper.getSelectedShape() != null);
        this.toolBar.shapeControl(guiHelper.getSelectedShape() != null);
      }

    }
  }
}
