import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class MainController {
  @FXML SmartGraphPanel<String, String> graphPanel;
  Graph<String, String> graph;
  @FXML TextField userCommand;
  @FXML TextField fileInput;
  Alert xmlSaved = new Alert(Alert.AlertType.INFORMATION);
  String fileName;


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
    //TODO: Check if a file exists
    if (!fileInput.getText().equals("")) {
      fileName = fileInput.getText() + ".xml";
    } else {
      fileName = "NmapScan.xml";
    }

    showSaved();
    testGraph();
  }

  public void showSaved() {
    xmlSaved.setTitle("Nmap Scan saved to: " + fileName);
    xmlSaved.setHeaderText("Scan saved");
    xmlSaved.setContentText("");
    xmlSaved.show();
  }

  public void testGraph() {
    graph.insertVertex("1");
    for(int i = 2; i < 10; i++) {
      graph.insertVertex(String.valueOf(i));
    }
    for(Vertex v : graph.vertices()) {
      graph.insertEdge("1", v.element().toString(), v.element().toString());
    }
    graphPanel.update();
  }
}
