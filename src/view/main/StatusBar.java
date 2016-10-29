package view.main;

import controller.PaintController;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class StatusBar extends BorderPane {

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

}
