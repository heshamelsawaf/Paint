package view.help;

import controller.PaintController;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.HomeConstants;

public class HelpStage extends Stage {

  private PaintController paintController;

  public HelpStage(PaintController paintController, Stage parentStage) {
    super();

    this.paintController = paintController;

    this.setTitle(HomeConstants.HELP_TITLE);
    this.setResizable(false);
    this.initStyle(StageStyle.UTILITY);
    this.initModality(Modality.WINDOW_MODAL);
    this.initOwner(parentStage);
    this.setScene(new HelpScene());
  }
}
