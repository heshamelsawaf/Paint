package controller;

import java.util.Stack;
import model.Drawing;

public class HistoryController {

	private static HistoryController instance;
	private Stack<Drawing> undoStack;
	private Stack<Drawing> redoStack;
	private PaintController paintController;
	private Drawing drawing;

	public HistoryController(PaintController paintCtrl) {
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
		this.paintController = paintCtrl;
	}

	static HistoryController getInstance(PaintController paintController) {
		if (instance == null) {
			instance = new HistoryController(paintController);
		}
		return instance;
	}

	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
	}

	public boolean canUndo() {
		return !this.undoStack.isEmpty();
	}

	public boolean canRedo() {
		return !this.redoStack.isEmpty();
	}

	public void resetHistory() {
		this.undoStack.clear();
		this.redoStack.clear();
	}

	public void createHistoryEntry() {
		try {
			this.drawing = this.paintController.getDrawingController().getDrawing().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		this.undoStack.push(this.drawing);
		this.redoStack.clear();
		this.paintController.getGUIController().getGuiHelper().notifyObservers();
	}

	public void undo() {
		if (this.canUndo()) {
			this.paintController.getGUIController().getGuiHelper().setSelectedDrawTool(null);
			this.redoStack.push(this.undoStack.pop());
			try {
				this.drawing = undoStack.peek().clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			this.paintController.getDrawingController().setDrawing(this.drawing);
			this.paintController.getGUIController().getGuiHelper().notifyObservers();
		}
	}

	public void redo() {
		if (this.canRedo()) {
			this.undoStack.push(this.redoStack.pop());
			try {
				this.drawing = undoStack.peek().clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			this.paintController.getDrawingController().setDrawing(this.drawing);
			this.paintController.getGUIController().setSelectedTool(this.paintController.
					getGUIController().getGuiHelper().getSelectedDrawTool());
			this.undoStack.push(this.drawing);
			this.paintController.getGUIController().getGuiHelper().notifyObservers();
		}
	}
}
