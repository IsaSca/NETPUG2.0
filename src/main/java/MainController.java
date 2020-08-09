import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.SubScene;

public class MainController {
  
  @FXML SubScene graphScene;
  SmartGraphPanel<String, String> graphPanel;
  Graph<String, String> graph;
  
  public void setUpGraph(Graph<String, String> graph, SmartGraphPanel<String, String> graphPanel) {
    this.graphPanel = graphPanel;
    this.graph = graph;
  }
  
  
  
  @FXML
  private void generateGraph() {
    System.out.println(graph + "mmmm");
  }
}
