import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.SubScene;


public class MainController {
  
  @FXML SubScene graphScene;
  //SmartGraphPanel<String, String> graphPanel= (SmartGraphPanel<String, String>) graphScene.getRoot();
  
  @FXML
  private void generateGraph() {
    System.out.println(graphScene.getRoot());
  }
  
}
