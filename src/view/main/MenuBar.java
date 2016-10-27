package view.main;

import controller.PaintController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import util.MenuBarConstants;

public class MenuBar extends javafx.scene.control.MenuBar {
  private PaintController paintController;

  // menus
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
    this.tool.getPolygon().setDisable(!key);
    this.tool.getRectangle().setDisable(!key);
    this.tool.getSquare().setDisable(!key);
    this.tool.getTriangle().setDisable(!key);
    this.tool.getLine().setDisable(!key);
    this.tool.getEllipse().setDisable(!key);
    this.tool.getCircle().setDisable(!key);

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
      this.newFile = new MenuItem();
      this.openFile = new MenuItem();
      this.save = new MenuItem();
      this.saveAs = new MenuItem();
      this.exportFile = new MenuItem();
      this.importFile = new MenuItem();
      this.close = new MenuItem();
      this.exit = new MenuItem();
      this.buildFileMenu();
      this.getItems().addAll(this.newFile, new SeparatorMenuItem(), this.openFile, this.save,
          this.saveAs, new SeparatorMenuItem(), this.exportFile, this.importFile,
          new SeparatorMenuItem(), this.close, this.exit);
    }


    private void buildFileMenu() {

      this.newFile.setOnAction(event -> {

      });
      this.openFile.setOnAction(event -> {

      });
      this.save.setOnAction(event -> {

      });
      this.saveAs.setOnAction(event -> {

      });
      this.exportFile.setOnAction(event -> {

      });
      this.importFile.setOnAction(event -> {

      });
      this.close.setOnAction(event -> {

      });
      this.exit.setOnAction(event -> {

      });
    }


    public MenuItem getNewFile() {
      return newFile;
    }


    public MenuItem getOpenFile() {
      return openFile;
    }


    public MenuItem getSave() {
      return save;
    }


    public MenuItem getSaveAs() {
      return saveAs;
    }


    public MenuItem getExportFile() {
      return exportFile;
    }


    public MenuItem getImportFile() {
      return importFile;
    }


    public MenuItem getClose() {
      return close;
    }


    public MenuItem getExit() {
      return exit;
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
      this.undo = new MenuItem();
      this.redo = new MenuItem();
      this.cut = new MenuItem();
      this.copy = new MenuItem();
      this.paste = new MenuItem();
      this.clear = new MenuItem();
      this.buildFileMenu();
      this.getItems().addAll(this.undo, this.redo, new SeparatorMenuItem(), this.cut, this.copy,
          this.paste, new SeparatorMenuItem(), this.clear);
    }

    private void buildFileMenu() {

      this.undo.setOnAction(event -> {

      });
      this.redo.setOnAction(event -> {

      });
      this.cut.setOnAction(event -> {

      });
      this.copy.setOnAction(event -> {

      });
      this.paste.setOnAction(event -> {

      });
      this.clear.setOnAction(event -> {

      });
    }

    public MenuItem getUndo() {
      return undo;
    }

    public MenuItem getRedo() {
      return redo;
    }

    public MenuItem getCut() {
      return cut;
    }

    public MenuItem getCopy() {
      return copy;
    }

    public MenuItem getPaste() {
      return paste;
    }

    public MenuItem getClear() {
      return clear;
    }
  }

  private class View extends Menu {

    private Toolbars toolbars;
    private Zoom zoom;
    private MenuItem fullScreen;

    public View(String text) {
      super();
      this.setText(text);
      this.toolbars = new Toolbars(MenuBarConstants.VIEW_TOOLBARS);
      this.zoom = new Zoom(MenuBarConstants.VIEW_ZOOM);
      this.fullScreen = new MenuItem(MenuBarConstants.VIEW_FULLSCREEN);
      this.buildViewMenu();
      this.getItems().addAll(this.toolbars, this.zoom, new SeparatorMenuItem(), this.fullScreen);
    }

    private void buildViewMenu() {
      this.fullScreen.setOnAction(event -> {

      });
    }

    public Toolbars getToolbars() {
      return toolbars;
    }

    public Zoom getZoom() {
      return zoom;
    }

    public MenuItem getFullScreen() {
      return fullScreen;
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

      public CheckMenuItem getMenuBar() {
        return menuBar;
      }

      public CheckMenuItem getStatusBar() {
        return statusBar;
      }

      public CheckMenuItem getToolBar() {
        return toolBar;
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
        this.zoomIn.setOnAction(event -> {

        });
        this.zoomOut.setOnAction(event -> {

        });
        this.reset.setOnAction(event -> {

        });
      }

      public MenuItem getZoomIn() {
        return zoomIn;
      }

      public MenuItem getZoomOut() {
        return zoomOut;
      }

      public MenuItem getReset() {
        return reset;
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
      this.delete = new MenuItem(MenuBarConstants.ACTION_DELETE);
      this.rotateClockwise = new MenuItem(MenuBarConstants.ACTION_ROTATECLOCKWIESE);
      this.rotateCounterclockwise = new MenuItem(MenuBarConstants.ACTION_ROTATECOUNTERCLOCKWIESE);
      this.buildActionsMenu();
      this.getItems().addAll(this.duplicate, this.delete, new SeparatorMenuItem(),
          this.rotateClockwise, this.rotateCounterclockwise);
    }

    private void buildActionsMenu() {
      this.duplicate.setOnAction(event -> {

      });
      this.delete.setOnAction(event -> {

      });
      this.rotateClockwise.setOnAction(event -> {

      });
      this.rotateCounterclockwise.setOnAction(event -> {

      });
    }

    public MenuItem getDuplicate() {
      return duplicate;
    }

    public MenuItem getDelete() {
      return delete;
    }

    public MenuItem getRotateClockwise() {
      return rotateClockwise;
    }

    public MenuItem getRotateCounterclockwise() {
      return rotateCounterclockwise;
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
    private RadioMenuItem polygon;
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
      this.polygon = new RadioMenuItem(MenuBarConstants.TOOL_POLYGON);
      this.rectangle = new RadioMenuItem(MenuBarConstants.TOOL_RECTANGLE);
      this.square = new RadioMenuItem(MenuBarConstants.TOOL_SQUARE);
      this.triangle = new RadioMenuItem(MenuBarConstants.TOOL_TRIANGLE);
      this.line = new RadioMenuItem(MenuBarConstants.TOOL_LINE);
      this.ellipse = new RadioMenuItem(MenuBarConstants.TOOL_ELLIPSE);
      this.circle = new RadioMenuItem(MenuBarConstants.TOOL_CIRCLE);
      this.buildToolsMenu();
      this.getItems().addAll(this.fillColor, this.strokeColor, this.strokeWidth,
          new SeparatorMenuItem(), this.select, this.polygon, this.rectangle, this.square,
          this.triangle, this.line, this.ellipse, this.circle);
    }

    private void buildToolsMenu() {
      this.fillColorPicker.setOnAction(event -> {

      });
      this.strokeColorPicker.setOnAction(event -> {

      });

      this.select.setOnAction(event -> {

      });
      this.select.setToggleGroup(this.group);
      this.polygon.setOnAction(event -> {

      });
      this.polygon.setToggleGroup(this.group);
      this.rectangle.setOnAction(event -> {

      });
      this.rectangle.setToggleGroup(this.group);
      this.square.setOnAction(event -> {

      });
      this.square.setToggleGroup(this.group);
      this.triangle.setOnAction(event -> {

      });
      this.triangle.setToggleGroup(this.group);
      this.line.setOnAction(event -> {

      });
      this.line.setToggleGroup(this.group);
      this.ellipse.setOnAction(event -> {

      });
      this.ellipse.setToggleGroup(this.group);
      this.circle.setOnAction(event -> {

      });
      this.circle.setToggleGroup(this.group);
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

    public RadioMenuItem getPolygon() {
      return polygon;
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
        this.none = new RadioMenuItem();
        this.thin = new RadioMenuItem();
        this.medium = new RadioMenuItem();
        this.medium.setSelected(true);
        this.thick = new RadioMenuItem();
        this.veryThick = new RadioMenuItem();
        this.buildStrokeWidthMenu();
        this.getItems().addAll(this.none, this.thin, this.medium, this.thick, this.veryThick);
      }

      private void buildStrokeWidthMenu() {
        this.none.setOnAction(event -> {

        });
        this.none.setToggleGroup(this.group);
        this.thin.setOnAction(event -> {

        });
        this.thin.setToggleGroup(this.group);
        this.medium.setOnAction(event -> {

        });
        this.medium.setToggleGroup(this.group);
        this.thick.setOnAction(event -> {

        });
        this.thick.setToggleGroup(this.group);
        this.veryThick.setOnAction(event -> {

        });
        this.veryThick.setToggleGroup(this.group);
      }

      public RadioMenuItem getNone() {
        return none;
      }

      public RadioMenuItem getThin() {
        return thin;
      }

      public RadioMenuItem getMedium() {
        return medium;
      }

      public RadioMenuItem getThick() {
        return thick;
      }

      public RadioMenuItem getVeryThick() {
        return veryThick;
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

      });
    }

    public MenuItem getAbout() {
      return about;
    }
  }

}