package view.main;

import controller.HistoryController;
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
import view.StrokeWidths;

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
    this.strokeWidth.setDisable(!key);

    this.select.setDisable(!key);
    this.rectangle.setDisable(!key);
    this.square.setDisable(!key);
    this.triangle.setDisable(!key);
    this.line.setDisable(!key);
    this.ellipse.setDisable(!key);
    this.circle.setDisable(!key);

  }

  public void shapeControl(boolean key) {
    this.action.setDisable(!key);
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
    this.undo.setDisable(true);
    this.undo.setTooltip(new Tooltip(ToolBarConstants.UNDO));
    this.undo.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/undo.png"))));
    this.undo.setOnAction(event -> {
      HistoryController.getInstance(paintController).undo();
    });
    this.redo.setDisable(true);
    this.redo.setTooltip(new Tooltip(ToolBarConstants.REDO));
    this.redo.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/redo.png"))));
    this.redo.setOnAction(event -> {
      HistoryController.getInstance(paintController).redo();
    });
    this.getItems().addAll(this.undo, this.redo, this.action);
  }

  private void buildFont() {
    this.fillColor.setTooltip(new Tooltip(ToolBarConstants.FILL_COLOR));
    this.fillColor.setMaxHeight(Double.MAX_VALUE);
    this.fillColor.setOnAction(event -> {
      paintController.getGUIController().getGuiHelper().setFillColor(this.fillColor.getValue());
    });
    this.strokeColor.setTooltip(new Tooltip(ToolBarConstants.STROKE_COLOR));
    this.strokeColor.setMaxHeight(Double.MAX_VALUE);
    this.strokeColor.setOnAction(event -> {
      paintController.getGUIController().getGuiHelper().setStrokeColor(this.strokeColor.getValue());
    });
    this.getItems().addAll(this.fillColor, this.strokeColor, this.strokeWidth);
  }

  private void buildTools() {
    this.select.setToggleGroup(this.group);
    this.select.setTooltip(new Tooltip(ToolBarConstants.SELECT));
    this.select.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/select.png"))));
    this.select.setOnAction(event -> {
      this.paintController.getGUIController().setSelectedTool(DrawingTools.SELECT);
    });
    this.rectangle.setToggleGroup(this.group);
    this.rectangle.setTooltip(new Tooltip(ToolBarConstants.RECTANGLE));
    this.rectangle.setGraphic(new ImageView(
        new Image(ClassLoader.getSystemResourceAsStream("assets/icons/rectangle.png"))));
    this.rectangle.setOnAction(event -> {
      this.paintController.getGUIController().setSelectedTool(DrawingTools.RECTANGLE);
    });
    this.square.setToggleGroup(this.group);
    this.square.setTooltip(new Tooltip(ToolBarConstants.SQUARE));
    this.square.setGraphic(
        new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/square.png"))));
    this.square.setOnAction(event -> {
      this.paintController.getGUIController().setSelectedTool(DrawingTools.SQUARE);
    });
    this.triangle.setToggleGroup(this.group);
    this.triangle.setTooltip(new Tooltip(ToolBarConstants.TRIANGLE));
    this.triangle.setGraphic(new ImageView(
        new Image(ClassLoader.getSystemResourceAsStream("assets/icons/triangle.png"))));
    this.triangle.setOnAction(event -> {
      this.paintController.getGUIController().setSelectedTool(DrawingTools.TRIANGLE);
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
      this.paintController.getGUIController().setSelectedTool(DrawingTools.CIRCLE);
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
      this.setDisable(true);
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
        paintController.getDrawingController()
            .setBehind(paintController.getGUIController().getGuiHelper().getSelectedShape());
      });
      this.setBehindAll.setOnAction(event -> {
        paintController.getDrawingController()
            .setBehindAll(paintController.getGUIController().getGuiHelper().getSelectedShape());
      });
      this.setInFront.setOnAction(event -> {
        paintController.getDrawingController()
            .setInFront(paintController.getGUIController().getGuiHelper().getSelectedShape());
      });
      this.SetInFrontAll.setOnAction(event -> {
        paintController.getDrawingController()
            .setInFrontOfAll(paintController.getGUIController().getGuiHelper().getSelectedShape());
      });
      this.rotateClockwise.setOnAction(event -> {
        paintController.getDrawingController().getDrawing().rotateShape(90, true);
      });
      this.rotateCounterclockwise.setOnAction(event -> {
        paintController.getDrawingController().getDrawing().rotateShape(-90, true);
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
      this.setDisable(true);
      this.group = new ToggleGroup();
      this.none = new RadioMenuItem(ToolBarConstants.STROKEWIDTH_NONE);
      this.thin = new RadioMenuItem(ToolBarConstants.STROKEWIDTH_THIN);
      this.medium = new RadioMenuItem(ToolBarConstants.STROKEWIDTH_MEDIUM);
      this.medium.setSelected(true);
      this.thick = new RadioMenuItem(ToolBarConstants.STROKEWIDTH_THICK);
      this.veryThick = new RadioMenuItem(ToolBarConstants.STROKEWIDTH_VERYTHICK);
      this.buildStrokeWidthMenuButton();
      this.getItems().addAll(this.none, this.thin, this.medium, this.thick, this.veryThick);
    }

    private void buildStrokeWidthMenuButton() {
      this.none.setToggleGroup(this.group);
      this.none.setOnAction(event -> {
        paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.NONE);
      });
      this.thin.setToggleGroup(this.group);
      this.thin.setOnAction(event -> {
        paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.THIN);
      });
      this.medium.setToggleGroup(this.group);
      this.medium.setOnAction(event -> {
        paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.MEDIUM);
      });
      this.thick.setToggleGroup(this.group);
      this.thick.setOnAction(event -> {
        paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.THICK);
      });
      this.veryThick.setToggleGroup(this.group);
      this.veryThick.setOnAction(event -> {
        paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.VERY_THICK);
      });
    }
  }

  @Override
  public void update() {
    GUIHelper guiHelper = this.paintController.getGUIController().getGuiHelper();

    this.undo.setDisable(!HistoryController.getInstance(this.paintController).canUndo());
    this.redo.setDisable(!HistoryController.getInstance(this.paintController).canRedo());

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
      case VERY_THICK: {
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
        case SQUARE: {
          this.square.setSelected(true);
          break;
        }
        case TRIANGLE: {
          this.triangle.setSelected(true);
          break;
        }
        case ELLIPSE: {
          this.ellipse.setSelected(true);
          break;
        }
        case CIRCLE: {
          this.circle.setSelected(true);
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
