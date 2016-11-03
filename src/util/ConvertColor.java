package util;

import javafx.scene.paint.Color;

public final class ConvertColor {

  public static String toHex(Color color) {
    return String.valueOf(color).replaceFirst("0x", "#").substring(0, 7);
  }

  private ConvertColor() {}
}
