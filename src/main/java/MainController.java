import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
  @FXML SmartGraphPanel<String, String> graphPanel;
  Graph<String, String> graph;
  @FXML TextField userCommand;
  @FXML TextField fileName;


  public void setUpGraph(Graph<String, String> graph, SmartGraphPanel<String, String> graphPanel) {
    this.graphPanel = graphPanel;
    this.graph = graph;
  }
  
  @FXML
  private void getText() {
    if (userCommand != null) {
      System.out.println(userCommand.getText());
    } else {
      System.out.println("no");
    }
  }

  @FXML
  private void runCommand() {

  }

  @FXML
  private void generateGraph() {
    graph.insertVertex("1");
    for(int i = 2; i < 20; i++) {
      graph.insertVertex(String.valueOf(i));
    }
    for(Vertex v : graph.vertices()) {
      graph.insertEdge("1", v.element().toString(), v.element().toString());
    }
    graphPanel.update();
    System.out.println(graph);
  }
}
