package view.main;

import controller.PaintController;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class HomeScene extends Scene {

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
    this.statusBar = new StatusBar(this.paintController);

    this.showMenuBar(true);
    this.showToolBar(true);
    this.showStatusBar(true);

    this.borderPane.setTop(this.barsPane);

    this.drawingAreaGroup = new Group();
    this.drawingAreaZoomGroup = new Group();
    this.drawingAreaZoomGroup.getChildren().add(this.drawingAreaGroup);

    StackPane stackPane = new StackPane(this.drawingAreaZoomGroup);
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setContent(stackPane);

    stackPane.minWidthProperty().bind(Bindings.createDoubleBinding(
        () -> scrollPane.getViewportBounds().getWidth(), scrollPane.viewportBoundsProperty()));
    stackPane.minHeightProperty().bind(Bindings.createDoubleBinding(
        () -> scrollPane.getViewportBounds().getHeight(), scrollPane.viewportBoundsProperty()));

    this.borderPane.setCenter(scrollPane);


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



  public Home getHome() {
    return home;
  }

  public BorderPane getBorderPane() {
    return borderPane;
  }

  public VBox getBarsPane() {
    return barsPane;
  }

  public MenuBar getMenuBar() {
    return menuBar;
  }

  public ToolBar getToolBar() {
    return toolBar;
  }

  public BorderMessage getBorderMessage() {
    return borderMessage;
  }

  public DrawingArea getDrawingArea() {
    return drawingArea;
  }

  public StatusBar getStatusBar() {
    return statusBar;
  }

  public void setStatusBar(StatusBar statusBar) {
    this.statusBar = statusBar;
  }
}
