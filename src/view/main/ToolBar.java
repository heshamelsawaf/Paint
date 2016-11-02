package view.main;

import controller.PaintController;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.GUIHelper;
import model.Observer;
import util.ToolBarConstants;
import view.DrawingTools;

public class ToolBar extends javafx.scene.control.ToolBar implements Observer {
  private PaintController paintController;

  private Button _new;
  private Button open;
  private Button save;

  private Button undo;
  private Button redo;
  private Action action;

  private ColorPicker fillColor;
  private ColorPicker strokeColor;
  private StrokeWidth strokeWidth;

  private ToggleGroup group;
  private ToggleButton select;
  private ToggleButton rectangle;
  private ToggleButton square;
  private ToggleButton triangle;
  private ToggleButton line;
  private ToggleButton ellipse;
  private ToggleButton circle;


  public ToolBar(PaintController paintController) {
    super();
    this.paintController = paintController;

    this._new = new Button();
    this.open = new Button();
    this.save = new Button();

    this.undo = new Button();
    this.redo = new Button();
    this.action = new Action(ToolBarConstants.ACTION_ON_SHAPE);

    this.fillColor = new ColorPicker();
    this.strokeColor = new ColorPicker();
    this.strokeWidth = new StrokeWidth(ToolBarConstants.STROKE_WIDTH);

    this.group = new ToggleGroup();
    this.select = new ToggleButton();
    this.select.setSelected(true);
    this.rectangle = new ToggleButton();
    this.square = new ToggleButton();
    this.triangle = new ToggleButton();
    this.line = new ToggleButton();
    this.ellipse = new ToggleButton();
    this.circle = new ToggleButton();

    this.BuildToolBar();
    this.takeControl(false);

  }

  public void takeControl(Boolean key) {

    this.save.setDisable(!key);

    this.fillColor.setDisable(!key);
    this.strokeColor.setDisable(!key);
    this.action.setDisable(!key);

    this.select.setDisable(!key);
    this.rectangle.setDisable(!key);
    this.square.setDisable(!key);
    this.triangle.setDisable(!key);
    this.line.setDisable(!key);
    this.ellipse.setDisable(!key);
    this.circle.setDisable(!key);

  }

  public void shapeControl(boolean key) {

  }

  private void BuildToolBar() {
    this.BuildFile();
    this.getItems().add(new Separator());
    this.BuildEdit();
    this.getItems().add(new Separator());
    this.buildFont();
    this.getItems().add(new Separator());
    this.buildTools();
  }

  private void BuildFile() {
    this._new.setTooltip(new Tooltip(ToolBarConstants.NEWFILE));
    this._new.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/new.png"))));
    this._new.setOnAction(event -> {
      if (this.paintController.getDrawingController().closeDrawing()) {
        this.paintController.getGUIController()
            .openNewDrawingDialog((Stage) this.getScene().getWindow());
      }
    });
    this.open.setTooltip(new Tooltip(ToolBarConstants.OPENFILE));
    this.open.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/open.png"))));
    this.open.setOnAction(event -> {

    });
    this.save.setTooltip(new Tooltip(ToolBarConstants.SAVEFILE));
    this.save.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/save.png"))));
    this.save.setOnAction(event -> {

    });
    this.getItems().addAll(this._new, this.open, this.save);
  }

  private void BuildEdit() {
    this.undo.setTooltip(new Tooltip(ToolBarConstants.UNDO));
    this.undo.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/undo.png"))));
    this.undo.setOnAction(event -> {

    });
    this.redo.setTooltip(new Tooltip(ToolBarConstants.REDO));
    this.redo.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/redo.png"))));
    this.redo.setOnAction(event -> {

    });
    this.getItems().addAll(this.undo, this.redo, this.action);
  }

  private void buildFont() {
    this.fillColor.setOnAction(event -> {

    });
    this.strokeColor.setOnAction(event -> {

    });
    this.getItems().addAll(this.fillColor, this.strokeColor, this.strokeWidth);
  }

  private void buildTools() {
    this.select.setToggleGroup(this.group);
    this.select.setTooltip(new Tooltip(ToolBarConstants.SELECT));
    this.select.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/select.png"))));
    this.select.setOnAction(event -> {

    });
    this.rectangle.setToggleGroup(this.group);
    this.rectangle.setTooltip(new Tooltip(ToolBarConstants.RECTANGLE));
    this.rectangle.setGraphic(new ImageView(
        new Image(ClassLoader.getSystemResourceAsStream("assets/icons/rectangle.png"))));
    this.rectangle.setOnAction(event -> {

    });
    this.square.setToggleGroup(this.group);
    this.square.setTooltip(new Tooltip(ToolBarConstants.SQUARE));
    this.square.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/square.png"))));
    this.square.setOnAction(event -> {

    });
    this.triangle.setToggleGroup(this.group);
    this.triangle.setTooltip(new Tooltip(ToolBarConstants.TRIANGLE));
    this.triangle.setGraphic(new ImageView(
        new Image(ClassLoader.getSystemResourceAsStream("assets/icons/triangle.png"))));
    this.triangle.setOnAction(event -> {

    });
    this.line.setToggleGroup(this.group);
    this.line.setTooltip(new Tooltip(ToolBarConstants.LINE));
    this.line.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/line.png"))));
    this.line.setOnAction(event -> {
      this.paintController.getGUIController().setSelectedTool(DrawingTools.LINE);
    });
    this.ellipse.setToggleGroup(this.group);
    this.ellipse.setTooltip(new Tooltip(ToolBarConstants.ELLIPSE));
    this.ellipse.setGraphic(new ImageView(
        new Image(ClassLoader.getSystemResourceAsStream("assets/icons/ellipse.png"))));
    this.ellipse.setOnAction(event -> {
      this.paintController.getGUIController().setSelectedTool(DrawingTools.ELLIPSE);
    });
    this.circle.setToggleGroup(this.group);
    this.circle.setTooltip(new Tooltip(ToolBarConstants.CIRCLE));
    this.circle.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/circle.png"))));
    this.circle.setOnAction(event -> {

    });
    this.getItems().addAll(this.select, this.rectangle, this.square, this.triangle, this.line,
        this.ellipse, this.circle);
  }

  private class Action extends MenuButton {

    private MenuItem setBehind;
    private MenuItem setBehindAll;
    private MenuItem setInFront;
    private MenuItem SetInFrontAll;
    private MenuItem rotateClockwise;
    private MenuItem rotateCounterclockwise;

    public Action(String text) {
      super();
      this.setText(text);
      this.setBehind = new MenuItem();
      this.setBehindAll = new MenuItem();
      this.setInFront = new MenuItem();
      this.SetInFrontAll = new MenuItem();
      this.rotateClockwise = new MenuItem();
      this.rotateCounterclockwise = new MenuItem();
      this.buildActionMenuButton();
      this.getItems().addAll(this.setBehind, this.setBehindAll, this.setInFront, this.SetInFrontAll,
          new SeparatorMenuItem(), this.rotateClockwise, this.rotateCounterclockwise);
    }

    private void buildActionMenuButton() {
      this.setBehind.setOnAction(event -> {

      });
      this.setBehindAll.setOnAction(event -> {

      });
      this.setInFront.setOnAction(event -> {

      });
      this.SetInFrontAll.setOnAction(event -> {

      });
      this.rotateClockwise.setOnAction(event -> {

      });
      this.rotateCounterclockwise.setOnAction(event -> {

      });
    }
  }

  private class StrokeWidth extends MenuButton {

    private ToggleGroup group;

    private RadioMenuItem none;
    private RadioMenuItem thin;
    private RadioMenuItem medium;
    private RadioMenuItem thick;
    private RadioMenuItem veryThick;

    public StrokeWidth(String text) {
      super();
      this.setText(text);
      this.group = new ToggleGroup();

      this.none = new RadioMenuItem();
      this.thin = new RadioMenuItem();
      this.medium = new RadioMenuItem();
      this.medium.setSelected(true);
      this.thick = new RadioMenuItem();
      this.veryThick = new RadioMenuItem();
      this.buildStrokeWidthMenuButton();
      this.getItems().addAll(this.none, this.thin, this.medium, this.thick, this.veryThick);
    }

    private void buildStrokeWidthMenuButton() {
      this.none.setToggleGroup(this.group);
      this.none.setOnAction(event -> {

      });
      this.thin.setToggleGroup(this.group);
      this.thin.setOnAction(event -> {

      });
      this.medium.setToggleGroup(this.group);
      this.medium.setOnAction(event -> {

      });
      this.thick.setToggleGroup(this.group);
      this.thick.setOnAction(event -> {

      });
      this.veryThick.setToggleGroup(this.group);
      this.veryThick.setOnAction(event -> {

      });
    }
  }

  @Override
  public void update() {
    GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();


    this.fillColor.setValue(guiHelper.getFillColor());
    this.strokeColor.setValue(guiHelper.getStrokeColor());

    switch (guiHelper.getStrokeWidth()) {
      case NONE: {
        this.strokeWidth.none.setSelected(true);
        break;
      }
      case THIN: {
        this.strokeWidth.thin.setSelected(true);
        break;
      }
      case MEDIUM: {
        this.strokeWidth.medium.setSelected(true);
        break;
      }
      case THICK: {
        this.strokeWidth.thick.setSelected(true);
        break;
      }
      case EXTRA_THICK: {
        this.strokeWidth.veryThick.setSelected(true);
        break;
      }
    }

    if (guiHelper.getSelectedDrawTool() != null) {
      switch (guiHelper.getSelectedDrawTool()) {
        case SELECT: {
          this.select.setSelected(true);
          break;
        }
        case RECTANGLE: {
          this.rectangle.setSelected(true);
          break;
        }
        case ELLIPSE: {
          this.ellipse.setSelected(true);
          break;
        }
        case LINE: {
          this.line.setSelected(true);
          break;
        }
      }
    }

  }
}
