package model;

public class Circle extends Ellipse {

	public Circle() {}

	public Circle(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius, radius);
	}

	@Override
	public void setCenterX(double centerX) {
		super.setCenterX(centerX);
	}

	@Override
	public double getCenterX() {
		return super.getCenterX();
	}

	@Override
	public void setCenterY(double centerY) {
		super.setCenterY(centerY);
	}

	@Override
	public double getCenterY() {
		return super.getCenterY();
	}

	public void setRadius(double radius) {
		super.setCenterX(radius);
		super.setCenterY(radius);
	}

	public double getRadius() {
		return super.getCenterX();
	}
}
