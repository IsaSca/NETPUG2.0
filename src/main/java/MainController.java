import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.SubScene;

import javax.annotation.Resources;
import java.net.URL;


public class MainController {
  
  @FXML SubScene graphScene;
  SmartGraphPanel<String, String> graphPanel;
  
  @FXML
  private void generateGraph() {
    System.out.println(graphScene.getRoot());
  }
  
  @FXML
  protected void initialize(URL location, Resources resources) {
    graphPanel = (SmartGraphPanel<String, String>) graphScene.getRoot();
  }
  
}
