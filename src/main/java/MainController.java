import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.SubScene;

public class MainController {
  
  @FXML SubScene graphScene;
  SmartGraphPanel<String, String> graphPanel;
  Graph<String, String> graph;
  
  public void setGraph(Graph<String, String> graph) {
    this.graph = graph;
  }
  
  @FXML
  private void generateGraph() {
    graphPanel = (SmartGraphPanel<String, String>) graphScene.getRoot();
    System.out.println(graph);
  }
}
