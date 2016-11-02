package controller;

import java.util.Stack;
import model.Drawing;

public class HistoryController {

	private static HistoryController instance;
	private Stack<Drawing> undoStack;
	private Stack<Drawing> redoStack;
	private static PaintController paintController;
	private Drawing drawing;

	public HistoryController(PaintController paintCtrl) {
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
		paintController = paintCtrl;
	}

	static HistoryController getInstance() {
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

	public void createHistoryEntry(Drawing drawing) {
		try {
			this.undoStack.push(drawing.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		this.redoStack.clear();
	}

	public Drawing redo() {
		if (this.canRedo()) {
			
		}
		return drawing;
	}

	public Drawing undo() {
		if (this.canUndo()) {
			
		}
		return drawing;
	}
}
