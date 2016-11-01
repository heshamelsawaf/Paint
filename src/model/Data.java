package model;

import java.util.ArrayList;

public class Data {

	ArrayList<Shape> list;
	double canvasWidth, canvasHeight;
	String title;
	boolean saved;

	public Data(String title, double canvasWidth, double canvasHeight) {
		this.list = new ArrayList<Shape>();
		this.title = title;
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.saved = false;
	}

	void setTitle(String title) {
		this.title = title;
	}

	String getTitle() {
		return this.title;
	}

	void setCanvasWidth(double canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	double getCanvasWidth() {
		return this.canvasWidth;
	}

	void setCanvasHeight(double canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	double getCanvasHeight() {
		return this.canvasHeight;
	}

	void setSaved(boolean save) {
		this.saved = save;
	}

	boolean isSaved() {
		return this.saved;
	}

	void addObject(Shape shape) {
		this.list.add(shape);
	}

	ArrayList<Shape> getObjects() {
		return this.list;
	}

	void removeObject(int index) {
		this.list.remove(index);
	}

	void removeObject(Shape shape) {
		this.list.remove(this.list.indexOf(shape));
	}

	void clearAllObjects() {
		this.list.clear();
	}
}
