package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public final class ConvertShape {

  public static String transformsToString(Node shape) {
    String transforms = "";
    for (Transform transform : shape.getTransforms()) {
      StringBuilder toBeAppended = new StringBuilder();
      if (!transforms.isEmpty()) {
        toBeAppended.append(" ");
      }
      if (transform instanceof Rotate) {
        Rotate rotate = (Rotate) transform;
        toBeAppended.append("rotate(");
        toBeAppended.append(rotate.getAngle());
        toBeAppended.append(" ");
        toBeAppended.append(rotate.getPivotX());
        toBeAppended.append(" ");
        toBeAppended.append(rotate.getPivotY());
        toBeAppended.append(")");
      }
      transforms += toBeAppended.toString();
    }
    return transforms;
  }

  public static List<Transform> transformsFromString(String transforms) {
    transforms = transforms.toLowerCase().trim();
    List<Transform> transformsList = new ArrayList<>();
    String[] xmlTransforms = transforms.split("\\)");
    for (String s : xmlTransforms) {
      s = s.trim();
      Transform transform = null;
      if (s.startsWith("rotate")) {
        transform = new Rotate();
        String[] propers = s.split(" ");
        ((Rotate) transform).setAngle(
            Double.parseDouble(propers[0].replaceAll("rotate", "").replaceAll("\\(", "").trim()));
        ((Rotate) transform).setPivotX(Double.parseDouble(propers[1].trim()));
        ((Rotate) transform).setPivotY(Double.parseDouble(propers[2].trim()));
      }
      if (transform != null) {
        transformsList.add(transform);
      }
    }
    return transformsList;
  }

  private ConvertShape() {}
}
