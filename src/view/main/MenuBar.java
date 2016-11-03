package view.main;

import controller.HistoryController;
import controller.PaintController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import model.GUIHelper;
import model.Observer;
import util.MenuBarConstants;
import view.DrawingTools;
import view.StrokeWidths;;

public class MenuBar extends javafx.scene.control.MenuBar implements Observer {
  private PaintController paintController;

  // Menus
  private File file;
  private Edit edit;
  private View view;
  private Action action;
  private Tool tool;
  private Help help;


  public MenuBar(PaintController paintController) {
    super();

    this.paintController = paintController;
    this.file = new File(MenuBarConstants.FILE);
    this.edit = new Edit(MenuBarConstants.EDIT);
    this.view = new View(MenuBarConstants.VIEW);
    this.action = new Action(MenuBarConstants.ACTION);
    this.tool = new Tool(MenuBarConstants.TOOL);
    this.help = new Help(MenuBarConstants.HELP);
    this.getMenus().addAll(this.file, this.edit, this.view, this.action, this.tool, this.help);
    this.takeControl(false);
  }

  public void takeControl(Boolean key) {

    this.file.getSave().setDisable(!key);
    this.file.getSaveAs().setDisable(!key);
    this.file.getExportFile().setDisable(!key);
    this.file.getClose().setDisable(!key);

    this.edit.getClear().setDisable(!key);

    this.view.getZoom().setDisable(!key);

    this.tool.getFillColor().setDisable(!key);
    this.tool.getFillColorPicker().setDisable(!key);
    this.tool.getStrokeColor().setDisable(!key);
    this.tool.getStrokeColorPicker().setDisable(!key);
    this.tool.getStrokeWidth().setDisable(!key);
    this.tool.getSelect().setDisable(!key);
    this.tool.getRectangle().setDisable(!key);
    this.tool.getSquare().setDisable(!key);
    this.tool.getTriangle().setDisable(!key);
    this.tool.getLine().setDisable(!key);
    this.tool.getEllipse().setDisable(!key);
    this.tool.getCircle().setDisable(!key);

  }

  public void shapeControl(boolean key) {
    this.action.delete.setDisable(!key);
    this.action.duplicate.setDisable(!key);
    this.action.rotateClockwise.setDisable(!key);
    this.action.rotateCounterclockwise.setDisable(!key);
  }

  public void selectView_Toolbars_MenuBar(boolean key) {
    this.view.toolbars.menuBar.setSelected(key);
  }

  public void selectView_Toolbars_StatusBar(boolean key) {
    this.view.toolbars.statusBar.setSelected(key);
  }

  public void selectView_Toolbars_ToolBar(boolean key) {
    this.view.toolbars.toolBar.setSelected(key);
  }

  private class File extends Menu {
    private MenuItem newFile;
    private MenuItem openFile;
    private MenuItem save;
    private MenuItem saveAs;
    private MenuItem exportFile;
    private MenuItem importFile;
    private MenuItem close;
    private MenuItem exit;


    public File(String text) {
      super();
      this.setText(text);
      this.newFile = new MenuItem(MenuBarConstants.FILE_NEWFILE);
      this.openFile = new MenuItem(MenuBarConstants.FILE_OPENFILE);
      this.save = new MenuItem(MenuBarConstants.FILE_SAVE);
      this.saveAs = new MenuItem(MenuBarConstants.FILE_SAVEAS);
      this.exportFile = new MenuItem(MenuBarConstants.FILE_EXPORT);
      this.importFile = new MenuItem(MenuBarConstants.FILE_IMPORT);
      this.close = new MenuItem(MenuBarConstants.FILE_CLOSE);
      this.exit = new MenuItem(MenuBarConstants.FILE_EXIT);
      this.buildFileMenu();
      this.getItems().addAll(this.newFile, new SeparatorMenuItem(), this.openFile, this.save,
          this.saveAs, new SeparatorMenuItem(), this.exportFile, this.importFile,
          new SeparatorMenuItem(), this.close, this.exit);
    }


    private void buildFileMenu() {

      this.newFile.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
      this.newFile.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/new.png"))));
      this.newFile.setOnAction(event -> {
        if (paintController.getDrawingController().closeDrawing()) {
          paintController.getGUIController()
              .openNewDrawingDialog(paintController.getGUIController().getHome().getPrimaryStage());
        }
      });
      this.openFile.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
      this.openFile.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/open.png"))));
      this.openFile.setOnAction(event -> {

      });
      this.save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
      this.save.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/save.png"))));
      this.save.setOnAction(event -> {

      });
      this.saveAs.setAccelerator(
          new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN, KeyCombination.CONTROL_DOWN));
      this.saveAs.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/save.png"))));
      this.saveAs.setOnAction(event -> {

      });
      this.exportFile
          .setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
      this.exportFile.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/export.png"))));
      this.exportFile.setOnAction(event -> {

      });
      this.importFile
          .setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
      this.importFile.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/import.png"))));
      this.importFile.setOnAction(event -> {

      });
      this.close.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
      this.close.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/close.png"))));
      this.close.setOnAction(event -> {
        paintController.getDrawingController().closeDrawing();
      });
      this.exit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
      this.exit.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/exit.png"))));
      this.exit.setOnAction(event -> {
        paintController.exit();
      });
    }


    public MenuItem getSave() {
      return this.save;
    }


    public MenuItem getSaveAs() {
      return this.saveAs;
    }


    public MenuItem getExportFile() {
      return this.exportFile;
    }


    public MenuItem getClose() {
      return this.close;
    }

  }

  private class Edit extends Menu {
    private MenuItem undo;
    private MenuItem redo;
    private MenuItem cut;
    private MenuItem copy;
    private MenuItem paste;
    private MenuItem clear;

    public Edit(String text) {
      super();
      this.setText(text);
      this.undo = new MenuItem(MenuBarConstants.EDIT_UNDO);
      this.redo = new MenuItem(MenuBarConstants.EDIT_REDO);
      this.cut = new MenuItem(MenuBarConstants.EDIT_CUT);
      this.copy = new MenuItem(MenuBarConstants.EDIT_COPY);
      this.paste = new MenuItem(MenuBarConstants.EDIT_PASTE);
      this.clear = new MenuItem(MenuBarConstants.EDIT_CLEAR);
      this.buildFileMenu();
      this.getItems().addAll(this.undo, this.redo, new SeparatorMenuItem(), this.cut, this.copy,
          this.paste, new SeparatorMenuItem(), this.clear);
    }

    private void buildFileMenu() {
      this.undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
      this.undo.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/undo.png"))));
      this.undo.setOnAction(event -> {
        HistoryController.getInstance(paintController).undo();
      });
      this.redo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
      this.redo.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/redo.png"))));
      this.redo.setOnAction(event -> {
        HistoryController.getInstance(paintController).redo();
      });
      this.cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
      this.cut.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/cut.png"))));
      this.cut.setOnAction(event -> {

      });
      this.copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
      this.copy.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/copy.png"))));
      this.copy.setOnAction(event -> {

      });
      this.paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
      this.paste.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/paste.png"))));
      this.paste.setOnAction(event -> {

      });
      this.clear.setAccelerator(
          new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN, KeyCombination.CONTROL_DOWN));
      this.clear.setOnAction(event -> {
        paintController.getDrawingController().clearAllShapes();
      });
    }

    public MenuItem getClear() {
      return clear;
    }
  }

  private class View extends Menu {

    private Toolbars toolbars;
    private Zoom zoom;
    private CheckMenuItem fullScreen;

    public View(String text) {
      super();
      this.setText(text);
      this.toolbars = new Toolbars(MenuBarConstants.VIEW_TOOLBARS);
      this.zoom = new Zoom(MenuBarConstants.VIEW_ZOOM);
      this.fullScreen = new CheckMenuItem(MenuBarConstants.VIEW_FULLSCREEN);
      this.buildViewMenu();
      this.getItems().addAll(this.toolbars, this.zoom, new SeparatorMenuItem(), this.fullScreen);
    }

    private void buildViewMenu() {
      this.fullScreen.setAccelerator(new KeyCodeCombination(KeyCode.F11, KeyCombination.ALT_ANY));
      this.fullScreen.setOnAction(event -> {
        paintController.getGUIController().getHome().setTofullScreen(this.fullScreen.isSelected());
      });
    }


    public Zoom getZoom() {
      return zoom;
    }

    private class Toolbars extends Menu {
      private CheckMenuItem menuBar;
      private CheckMenuItem statusBar;
      private CheckMenuItem toolBar;

      public Toolbars(String text) {
        super();
        this.setText(text);
        this.menuBar = new CheckMenuItem(MenuBarConstants.VIEW_TOOLBARS_MENUBAR);
        this.menuBar.setSelected(true);
        this.statusBar = new CheckMenuItem(MenuBarConstants.VIEW_TOOLBARS_STATUSBAR);
        this.statusBar.setSelected(true);
        this.toolBar = new CheckMenuItem(MenuBarConstants.VIEW_TOOLBARS_TOOLSBAR);
        this.toolBar.setSelected(true);
        this.getItems().addAll(this.menuBar, this.statusBar, this.toolBar);
      }
    }

    private class Zoom extends Menu {
      private MenuItem zoomIn;
      private MenuItem zoomOut;
      private MenuItem reset;

      public Zoom(String text) {
        super();
        this.setText(text);
        this.zoomIn = new MenuItem(MenuBarConstants.VIEW_ZOOM_ZOOMIN);
        this.zoomOut = new MenuItem(MenuBarConstants.VIEW_ZOOM_ZOOMOUT);
        this.reset = new MenuItem(MenuBarConstants.VIEW_ZOOM_RESET);
        this.buildZoomSubMenu();
        this.getItems().addAll(this.zoomIn, this.zoomOut, new SeparatorMenuItem(), this.reset);
      }

      private void buildZoomSubMenu() {
        this.zoomIn
            .setAccelerator(new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN));
        this.zoomIn.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setZoomLevel(
              paintController.getGUIController().getGuiHelper().getZoomLevel() + 0.25);
        });
        this.zoomOut
            .setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN));
        this.zoomOut.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setZoomLevel(
              paintController.getGUIController().getGuiHelper().getZoomLevel() - 0.25);
        });
        this.reset
            .setAccelerator(new KeyCodeCombination(KeyCode.DIGIT0, KeyCombination.CONTROL_DOWN));
        this.reset.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setZoomLevel(1.0);
        });
      }
    }
  }

  private class Action extends Menu {
    private MenuItem duplicate;
    private MenuItem delete;
    private MenuItem rotateClockwise;
    private MenuItem rotateCounterclockwise;

    public Action(String text) {
      super();
      this.setText(text);
      this.duplicate = new MenuItem(MenuBarConstants.ACTION_DUPLICATE);
      this.duplicate.setDisable(true);
      this.delete = new MenuItem(MenuBarConstants.ACTION_DELETE);
      this.delete.setDisable(true);
      this.rotateClockwise = new MenuItem(MenuBarConstants.ACTION_ROTATECLOCKWIESE);
      this.rotateClockwise.setDisable(true);
      this.rotateCounterclockwise = new MenuItem(MenuBarConstants.ACTION_ROTATECOUNTERCLOCKWIESE);
      this.rotateCounterclockwise.setDisable(true);
      this.buildActionsMenu();
      this.getItems().addAll(this.duplicate, this.delete, new SeparatorMenuItem(),
          this.rotateClockwise, this.rotateCounterclockwise);
    }

    private void buildActionsMenu() {
      this.duplicate.setAccelerator(
          new KeyCodeCombination(KeyCode.D, KeyCombination.ALT_DOWN, KeyCombination.CONTROL_DOWN));
      this.duplicate.setOnAction(event -> {
        paintController.getDrawingController()
            .duplicateShape(paintController.getGUIController().getGuiHelper().getSelectedShape());
      });
      this.delete.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
      this.delete.setOnAction(event -> {
        paintController.getDrawingController()
            .removeShape(paintController.getGUIController().getGuiHelper().getSelectedShape());
        paintController.getGUIController().getGuiHelper().setSelectedShape(null);
      });
      this.rotateClockwise.setAccelerator(new KeyCodeCombination(KeyCode.PLUS,
          KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN));
      this.rotateClockwise.setOnAction(event -> {
        paintController.getDrawingController().getDrawing().rotateShape(90, true);
      });
      this.rotateCounterclockwise.setAccelerator(new KeyCodeCombination(KeyCode.MINUS,
          KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN));
      this.rotateCounterclockwise.setOnAction(event -> {
        paintController.getDrawingController().getDrawing().rotateShape(-90, true);
      });
    }
  }

  private class Tool extends Menu {

    private ColorPicker fillColorPicker;
    private ColorPicker strokeColorPicker;

    private CustomMenuItem fillColor;
    private CustomMenuItem strokeColor;

    private StrokeWidth strokeWidth;

    private ToggleGroup group;

    private RadioMenuItem select;
    private RadioMenuItem rectangle;
    private RadioMenuItem square;
    private RadioMenuItem triangle;
    private RadioMenuItem line;
    private RadioMenuItem ellipse;
    private RadioMenuItem circle;


    public Tool(String text) {
      super();
      this.setText(text);
      this.fillColorPicker = new ColorPicker();
      this.fillColor = new CustomMenuItem(this.fillColorPicker);

      this.strokeColorPicker = new ColorPicker();
      this.strokeColor = new CustomMenuItem(this.strokeColorPicker);

      this.strokeWidth = new StrokeWidth(MenuBarConstants.TOOL_STROKEWIDTH);

      this.group = new ToggleGroup();
      this.select = new RadioMenuItem(MenuBarConstants.TOOL_SELECT);
      this.select.setSelected(true);
      this.rectangle = new RadioMenuItem(MenuBarConstants.TOOL_RECTANGLE);
      this.square = new RadioMenuItem(MenuBarConstants.TOOL_SQUARE);
      this.triangle = new RadioMenuItem(MenuBarConstants.TOOL_TRIANGLE);
      this.line = new RadioMenuItem(MenuBarConstants.TOOL_LINE);
      this.ellipse = new RadioMenuItem(MenuBarConstants.TOOL_ELLIPSE);
      this.circle = new RadioMenuItem(MenuBarConstants.TOOL_CIRCLE);
      this.buildToolsMenu();
      this.getItems().addAll(this.fillColor, this.strokeColor, this.strokeWidth,
          new SeparatorMenuItem(), this.select, this.rectangle, this.square, this.triangle,
          this.line, this.ellipse, this.circle);
    }

    private void buildToolsMenu() {
      this.fillColorPicker.setDisable(true);
      this.fillColorPicker.setOnAction(event -> {
        paintController.getGUIController().getGuiHelper()
            .setFillColor(this.fillColorPicker.getValue());
      });
      this.fillColor.setHideOnClick(false);
      this.fillColor.setDisable(true);
      this.strokeColorPicker.setDisable(true);
      this.strokeColorPicker.setOnAction(event -> {
        paintController.getGUIController().getGuiHelper()
            .setStrokeColor(strokeColorPicker.getValue());
      });
      this.strokeColor.setHideOnClick(false);
      this.strokeColor.setDisable(true);
      this.select.setOnAction(event -> {
        paintController.getGUIController().setSelectedTool(DrawingTools.SELECT);
      });
      this.select.setToggleGroup(this.group);
      this.select.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/select.png"))));
      this.rectangle.setOnAction(event -> {
        paintController.getGUIController().setSelectedTool(DrawingTools.RECTANGLE);
      });
      this.rectangle.setToggleGroup(this.group);
      this.rectangle.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/rectangle.png"))));
      this.square.setOnAction(event -> {
        paintController.getGUIController().setSelectedTool(DrawingTools.SQUARE);
      });
      this.square.setToggleGroup(this.group);
      this.square.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/square.png"))));
      this.triangle.setOnAction(event -> {
        paintController.getGUIController().setSelectedTool(DrawingTools.TRIANGLE);
      });
      this.triangle.setToggleGroup(this.group);
      this.triangle.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/triangle.png"))));
      this.line.setOnAction(event -> {
        paintController.getGUIController().setSelectedTool(DrawingTools.LINE);
      });
      this.line.setToggleGroup(this.group);
      this.line.setGraphic(
          new ImageView(new Image(ClassLoader.getSystemResourceAsStream("assets/icons/line.png"))));
      this.ellipse.setOnAction(event -> {
        paintController.getGUIController().setSelectedTool(DrawingTools.ELLIPSE);
      });
      this.ellipse.setToggleGroup(this.group);
      this.ellipse.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/ellipse.png"))));
      this.circle.setOnAction(event -> {
        paintController.getGUIController().setSelectedTool(DrawingTools.CIRCLE);
      });
      this.circle.setToggleGroup(this.group);
      this.circle.setGraphic(new ImageView(
          new Image(ClassLoader.getSystemResourceAsStream("assets/icons/circle.png"))));
    }

    public CustomMenuItem getFillColor() {
      return fillColor;
    }

    public CustomMenuItem getStrokeColor() {
      return strokeColor;
    }

    public Menu getStrokeWidth() {
      return strokeWidth;
    }

    public RadioMenuItem getSelect() {
      return select;
    }

    public RadioMenuItem getRectangle() {
      return rectangle;
    }

    public RadioMenuItem getSquare() {
      return square;
    }

    public RadioMenuItem getTriangle() {
      return triangle;
    }

    public RadioMenuItem getLine() {
      return line;
    }

    public RadioMenuItem getEllipse() {
      return ellipse;
    }

    public RadioMenuItem getCircle() {
      return circle;
    }

    public ColorPicker getFillColorPicker() {
      return fillColorPicker;
    }

    public ColorPicker getStrokeColorPicker() {
      return strokeColorPicker;
    }

    private class StrokeWidth extends Menu {
      private ToggleGroup group;

      private RadioMenuItem none;
      private RadioMenuItem thin;
      private RadioMenuItem medium;
      private RadioMenuItem thick;
      private RadioMenuItem veryThick;

      public StrokeWidth(String text) {
        super();
        this.setText(text);
        this.none = new RadioMenuItem(MenuBarConstants.TOOL_STROKEWIDTH_NONE);
        this.thin = new RadioMenuItem(MenuBarConstants.TOOL_STROKEWIDTH_THIN);
        this.medium = new RadioMenuItem(MenuBarConstants.TOOL_STROKEWIDTH_MEDIUM);
        this.medium.setSelected(true);
        this.thick = new RadioMenuItem(MenuBarConstants.TOOL_STROKEWIDTH_THICK);
        this.veryThick = new RadioMenuItem(MenuBarConstants.TOOL_STROKEWIDTH_VERYTHICK);
        this.buildStrokeWidthMenu();
        this.getItems().addAll(this.none, this.thin, this.medium, this.thick, this.veryThick);
      }

      private void buildStrokeWidthMenu() {
        this.none.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.NONE);
        });
        this.none.setToggleGroup(this.group);
        this.thin.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.THIN);
        });
        this.thin.setToggleGroup(this.group);
        this.medium.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.MEDIUM);
        });
        this.medium.setToggleGroup(this.group);
        this.thick.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.THICK);
        });
        this.thick.setToggleGroup(this.group);
        this.veryThick.setOnAction(event -> {
          paintController.getGUIController().getGuiHelper().setStrokeWidth(StrokeWidths.VERY_THICK);
        });
        this.veryThick.setToggleGroup(this.group);
      }
    }
  }

  private class Help extends Menu {
    private MenuItem about;

    public Help(String text) {
      super();
      this.setText(text);
      this.about = new MenuItem(MenuBarConstants.HELP_ABOUTPAINT);
      this.buildHelpMenu();
      this.getItems().addAll(this.about);
    }

    private void buildHelpMenu() {
      this.about.setOnAction(event -> {
        paintController.getGUIController()
            .openHelpDialog((Stage) MenuBar.this.getScene().getWindow());
      });
    }
  }

  @Override
  public void update() {
    GUIHelper guiHelper = new GUIHelper(this.paintController);
    this.edit.undo.setDisable(!HistoryController.getInstance(this.paintController).canUndo());
    this.edit.redo.setDisable(!HistoryController.getInstance(this.paintController).canRedo());
    this.tool.fillColorPicker.setValue(guiHelper.getFillColor());
    this.tool.strokeColorPicker.setValue(guiHelper.getStrokeColor());

    switch (guiHelper.getStrokeWidth()) {
      case NONE:
        this.tool.strokeWidth.none.setSelected(true);
        break;
      case THIN:
        this.tool.strokeWidth.thin.setSelected(true);
        break;
      case MEDIUM:
        this.tool.strokeWidth.medium.setSelected(true);
        break;
      case THICK:
        this.tool.strokeWidth.thick.setSelected(true);
        break;
      case VERY_THICK:
        this.tool.strokeWidth.veryThick.setSelected(true);
        break;
    }

    if (guiHelper.getSelectedDrawTool() != null) {
      switch (guiHelper.getSelectedDrawTool()) {
        case SELECT: {
          this.tool.select.setSelected(true);
          break;
        }
        case RECTANGLE: {
          this.tool.rectangle.setSelected(true);
          break;
        }
        case ELLIPSE: {
          this.tool.ellipse.setSelected(true);
          break;
        }
        case LINE: {
          this.tool.line.setSelected(true);
          break;
        }
        case CIRCLE: {
          this.tool.circle.setSelected(true);
          break;
        }
        case TRIANGLE: {
          this.tool.triangle.setSelected(true);
          break;
        }
        case SQUARE: {
          this.tool.square.setSelected(true);
          break;
        }
      }
    }
  }

}
