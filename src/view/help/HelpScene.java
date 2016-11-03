package view.help;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.HomeConstants;

public class HelpScene extends Scene {

  public HelpScene() {
    super(new VBox());

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setPadding(
        new Insets(HomeConstants.HELP_VBOX_PADDING_TOP, HomeConstants.HELP_VBOX_PADDING_RIGHT,
            HomeConstants.HELP_VBOX_PADDING_BOTTOM, HomeConstants.HELP_VBOX_PADDING_LEFT));
    vBox.setSpacing(HomeConstants.HELP_VBOX_SPACING);

    Label label_1 = new Label(HomeConstants.HELP_MESSAGE_1);
    Label label_2 = new Label(HomeConstants.HELP_MESSAGE_2);
    Button button = new Button(HomeConstants.HELP_DISMISS_BUTTON);
    button.setDefaultButton(true);
    button.setCancelButton(true);
    button.setOnAction(event -> ((Stage) this.getWindow()).close());

    vBox.getChildren().addAll(label_1, label_2, button);

    this.setRoot(vBox);

  }

}
