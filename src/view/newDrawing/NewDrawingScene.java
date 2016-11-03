package view.newDrawing;

import java.util.InputMismatchException;

import controller.HistoryController;
import controller.PaintController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Drawing;
import model.GUIHelper;
import util.HomeConstants;
import util.Message;
import view.DrawingTools;

public class NewDrawingScene extends Scene {

  private PaintController paintController;

  private GridPane gridPane;

  private Label titleLabel;
  private TextField titleTextField;

  private Label widthLabel;
  private TextField widthTextField;
  private Label widthUnitingLabel;

  private Label heightLabel;
  private TextField heightTextField;
  private Label heightUnitingLabel;

  private Button okButton;
  private Button cancelButton;


  public NewDrawingScene(PaintController paintController) {
    super(new VBox());

    this.paintController = paintController;
    this.init();
  }

  private void init() {
    VBox vBox = new VBox();
    vBox.setPadding(new Insets(HomeConstants.NEW_PROJECT_VBOX_PADDING_TOP,
        HomeConstants.NEW_PROJECT_VBOX_PADDING_RIGHT, HomeConstants.NEW_PROJECT_VBOX_PADDING_BOTTOM,
        HomeConstants.NEW_PROJECT_VBOX_PADDING_LEFT));

    this.gridPane = new GridPane();
    this.gridPane.setAlignment(Pos.TOP_CENTER);
    this.gridPane.setHgap(HomeConstants.NEW_PROJECT_GRIDPANE_HGAP);
    this.gridPane.setVgap(HomeConstants.NEW_PROJECT_GRIDPANE_VGAP);

    this.titleLabel = new Label(HomeConstants.NEW_PROJECT_TITLE_LABEL);
    this.titleLabel.setTextAlignment(TextAlignment.RIGHT);

    this.titleTextField = new TextField(System.getProperty("user.name"));

    this.widthLabel = new Label(HomeConstants.NEW_PROJECT_WIDTH_LABEL);
    this.widthLabel.setTextAlignment(TextAlignment.RIGHT);

    this.widthTextField =
        new TextField(Double.toString(Screen.getPrimary().getVisualBounds().getWidth()));
    this.widthUnitingLabel = new Label(HomeConstants.NEW_PROJECT_WIDTH_UNIT_LABEL);

    this.heightLabel = new Label(HomeConstants.NEW_PROJECT_HEIGHT_LABEL);
    this.heightLabel.setTextAlignment(TextAlignment.RIGHT);

    this.heightTextField =
        new TextField(Double.toString(Screen.getPrimary().getVisualBounds().getHeight()));
    this.heightUnitingLabel = new Label(HomeConstants.NEW_PROJECT_HEIGHT_UNIT_LABEL);

    HBox hBox = new HBox(HomeConstants.NEW_PROJECT_HBOX_SPACING);
    hBox.setPadding(new Insets(HomeConstants.NEW_PROJECT_HBOX_PADDING_TOP,
        HomeConstants.NEW_PROJECT_HBOX_PADDING_RIGHT, HomeConstants.NEW_PROJECT_HBOX_PADDING_BOTTOM,
        HomeConstants.NEW_PROJECT_HBOX_PADDING_LEFT));
    hBox.setAlignment(Pos.BOTTOM_RIGHT);

    this.okButton = new Button(HomeConstants.NEW_PROJECT_OK_BUTTON_LABEL);
    this.okButton.setDefaultButton(true);
    this.okButton.setOnAction(event -> {
      this.createNewDrawing();
    });

    this.cancelButton = new Button(HomeConstants.NEW_PROJECT_CANCEL_BUTTON_LABEL);
    this.cancelButton.setCancelButton(true);
    this.cancelButton.setOnAction(event -> {
      this.dismiss();
    });

    hBox.getChildren().addAll(this.okButton, this.cancelButton);

    this.gridPane.add(this.titleLabel, 0, 0, 1, 1);
    this.gridPane.add(this.titleTextField, 1, 0, 2, 1);
    this.gridPane.add(this.widthLabel, 0, 1, 1, 1);
    this.gridPane.add(this.widthTextField, 1, 1, 1, 1);
    this.gridPane.add(this.widthUnitingLabel, 2, 1, 1, 1);
    this.gridPane.add(this.heightLabel, 0, 2, 1, 1);
    this.gridPane.add(this.heightTextField, 1, 2, 1, 1);
    this.gridPane.add(this.heightUnitingLabel, 2, 2, 1, 1);

    vBox.getChildren().addAll(this.gridPane, hBox);
    this.setRoot(vBox);
  }

  private void createNewDrawing() {
    try {
      this.validateInputs();
      Drawing newDrawing = this.paintController.getDrawingController().createDrawing(
          this.titleTextField.getText(), Double.parseDouble(this.widthTextField.getText()),
          Double.parseDouble(this.heightTextField.getText()));
      GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();
      this.registerObservers(newDrawing, guiHelper);
      this.paintController.getGUIController().setSelectedTool(DrawingTools.SELECT);
      HistoryController.getInstance(this.paintController).setDrawing(newDrawing);
      HistoryController.getInstance(this.paintController).createHistoryEntry();
      this.dismiss();
    } catch (InputMismatchException e) {
      e.printStackTrace();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.initOwner(getWindow());
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    }
  }

  private void validateInputs() throws InputMismatchException {
    if (this.titleTextField.getText().isEmpty()) {
      throw new InputMismatchException(Message.NEW_PROJECT_EMPTY_TITLE_ERROR);
    }
    try {
      double width = Double.parseDouble(this.widthTextField.getText());
      double height = Double.parseDouble(this.heightTextField.getText());

      if (width < 1 || width > HomeConstants.DRAWING_AREA_MAX_WIDTH || height < 1
          || height > HomeConstants.DRAWING_AREA_MAX_HEIGHT) {
        throw new NumberFormatException();
      }

    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new InputMismatchException(Message.NEW_PROJECT_WIDTH_OR_HEIGHT_ERROR);
    }
  }

  private void registerObservers(Drawing newDrawing, GUIHelper guiHelper) {
    newDrawing.registerObserver(this.paintController.getGUIController().getHome().getHomeScene());
    newDrawing.registerObserver(
        this.paintController.getGUIController().getHome().getHomeScene().getMenuBar());
    newDrawing.registerObserver(
        this.paintController.getGUIController().getHome().getHomeScene().getToolBar());
    newDrawing.registerObserver(
        this.paintController.getGUIController().getHome().getHomeScene().getStatusBar());
    guiHelper.registerObserver(this.paintController.getGUIController().getHome().getHomeScene());
    guiHelper.registerObserver(
        this.paintController.getGUIController().getHome().getHomeScene().getMenuBar());
    guiHelper.registerObserver(
        this.paintController.getGUIController().getHome().getHomeScene().getToolBar());
    guiHelper.registerObserver(
        this.paintController.getGUIController().getHome().getHomeScene().getStatusBar());
  }

  private void dismiss() {
    ((Stage) (this.getWindow())).close();
  }
}
