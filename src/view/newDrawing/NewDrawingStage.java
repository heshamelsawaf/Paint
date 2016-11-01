package view.newDrawing;

import controller.PaintController;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.HomeConstants;

public class NewDrawingStage extends Stage {

  private PaintController paintController;
  private NewDrawingScene newDrawingScene;

  public NewDrawingStage(PaintController paintController, Stage parentStage) {
    super();

    this.paintController = paintController;
    this.newDrawingScene = new NewDrawingScene(paintController);
    this.setTitle(HomeConstants.NEW_PROJECT_STAGE_TITLE);
    this.setResizable(false);
    this.initStyle(StageStyle.UTILITY);
    this.initModality(Modality.WINDOW_MODAL);
    this.initOwner(parentStage);

    this.setScene(this.newDrawingScene);
  }
}
