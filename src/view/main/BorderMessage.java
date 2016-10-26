package view.main;

import controller.PaintController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class BorderMessage extends BorderPane {

  private PaintController paintController;
  private Label label;
  private Button dismiss;

  public BorderMessage(PaintController paintController, String message) {
    this.paintController = paintController;
    this.label = new Label(message);
    this.dismiss = new Button();
    this.setLeft(label);
    this.setRight(dismiss);
  }
}
