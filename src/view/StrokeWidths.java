package view;

public enum StrokeWidths {

  NONE(0), THIN(1), MEDIUM(3), THICK(5), VERY_THICK(8);

  private final int strokeWidth;

  StrokeWidths(int strokeWidth) {
    this.strokeWidth = strokeWidth;
  }

  public int getStrokeWidthAsInt() {
    return strokeWidth;
  }

  public static StrokeWidths fromInteger(int strokeWidth) {
    switch (strokeWidth) {
      case 0:
        return NONE;
      case 1:
        return THIN;
      case 3:
        return MEDIUM;
      case 5:
        return THICK;
      case 8:
        return VERY_THICK;
      default:
        return MEDIUM;
    }
  }
}
