package view.main;

import controller.PaintController;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Drawing;
import model.GUIHelper;
import model.Observer;

public class StatusBar extends BorderPane implements Observer {

  private PaintController paintController;

  private HBox leftHBox;
  private Label coordinates;
  private Label drawingAreaDimensions;

  private HBox rightHBox;
  private Label zoom;

  public StatusBar(PaintController paintController) {
    super();

    this.paintController = paintController;

    this.leftHBox = new HBox();
    this.rightHBox = new HBox();
    this.coordinates = new Label();
    this.drawingAreaDimensions = new Label();
    this.zoom = new Label();

    this.buildStatusBar();

    this.setLeft(leftHBox);
    this.setRight(rightHBox);
  }

  private void buildStatusBar() {
    this.buildLeftBox();
    this.buildRightBox();
  }

  private void buildLeftBox() {


    this.leftHBox.getChildren().addAll(this.coordinates, this.drawingAreaDimensions);
  }

  private void buildRightBox() {


    this.rightHBox.getChildren().addAll(this.zoom);
  }

  public void updateCoordinates(double x, double y) {
    this.coordinates.setText("(" + x + ", " + y + ")");
  }

  public void clear() {
    this.coordinates.setText(null);
    this.drawingAreaDimensions.setText(null);
    this.zoom.setText(null);
  }

  @Override
  public void update() {
    Drawing drawing = this.paintController.getDrawingController().getDrawing();
    GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();

    if (drawing != null) {
      this.drawingAreaDimensions.setText(drawing.getWidth() + " x " + drawing.getHeight() + " px ");
    }

    this.zoom.setText(Double.toString(guiHelper.getZoomLevel() * 100) + "% ");
  }

}
