package controller;

import java.util.Stack;
import model.Drawing;

public class HistoryController {

  private static HistoryController instance;
  private Stack<Drawing> undoStack;
  private Stack<Drawing> redoStack;
  private PaintController paintController;
  private Drawing drawing;

  public HistoryController(PaintController paintController) {
    this.undoStack = new Stack<>();
    this.redoStack = new Stack<>();
    this.paintController = paintController;
  }

  public static HistoryController getInstance(PaintController paintController) {
    if (instance == null) {
      instance = new HistoryController(paintController);
    }
    return instance;
  }

  public void setDrawing(Drawing drawing) {
    this.drawing = drawing;
  }

  public boolean canUndo() {
    return (this.undoStack.size() > 1);
  }

  public boolean canRedo() {
    return !this.redoStack.isEmpty();
  }

  public void resetHistory() {
    this.undoStack.clear();
    this.redoStack.clear();
  }

  public void createHistoryEntry() {
    this.redoStack.clear();
    if (!this.undoStack.isEmpty()) {
      this.paintController.getDrawingController().getDrawing().setSaved(false);
    }
    this.undoStack.push(this.drawing.clone());
    this.paintController.getDrawingController().getDrawing().notifyObservers();
  }

  public void undo() {
    if (this.canUndo()) {
      this.paintController.getGUIController().getGuiHelper().setSelectedDrawTool(null);
      this.redoStack.push(this.undoStack.pop());
      this.drawing = undoStack.peek().clone();
      this.paintController.getDrawingController().setDrawing(this.drawing);
      this.paintController.getDrawingController().getDrawing().notifyObservers();
    }
  }

  public void redo() {
    if (this.canRedo()) {
      this.undoStack.push(this.redoStack.pop());
      this.drawing = undoStack.peek().clone();
      this.paintController.getDrawingController().setDrawing(this.drawing);
      this.paintController.getGUIController().setSelectedTool(
          this.paintController.getGUIController().getGuiHelper().getSelectedDrawTool());
      this.paintController.getDrawingController().getDrawing().notifyObservers();
    }
  }
}
