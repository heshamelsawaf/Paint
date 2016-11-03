package view.main;

import controller.PaintController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import util.HomeConstants;

public class BorderMessage extends BorderPane {

  private PaintController paintController;
  private Label label;
  private Button dismiss;

  public BorderMessage(PaintController paintController, String message) {
    super();
    this.paintController = paintController;
    this.setPadding(new Insets(HomeConstants.BORDER_MESSAGE_PADDING_TOP,
        HomeConstants.BORDER_MESSAGE_PADDING_RIGHT, HomeConstants.BORDER_MESSAGE_PADDING_BOTTOM,
        HomeConstants.BORDER_MESSAGE_PADDING_LEFT));

    this.label = new Label(message);
    this.label.setMaxHeight(Double.MAX_VALUE);

    this.dismiss = new Button();
    this.dismiss.setTooltip(new Tooltip(HomeConstants.BORDER_MESSAGE_TOOLTIP));
    this.dismiss.setOnAction(event -> {
      this.paintController.getGUIController().getHome().getHomeScene().hideBorderMessage();
    });
    
    this.setLeft(label);
    this.setRight(dismiss);
  }
}
