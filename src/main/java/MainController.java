import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class MainController {
  @FXML SmartGraphPanel<String, String> graphPanel;
  Graph<String, String> graph;
  @FXML TextField userCommand;
  @FXML TextField fileInput;
  Alert xmlSaved = new Alert(Alert.AlertType.INFORMATION);
  String fileName;
  NodeList hostList;
  @FXML Text hostText;
  @FXML Text ipText;
  @FXML Text ports;
  @FXML Text vulns;
  LinkedList<Host> hosts;


  
  @FXML
  private void getText() {
    if (userCommand != null) {
      System.out.println(userCommand.getText());
    } else {
      System.out.println("no");
    }
  }

  private void runCommand() {
    NmapParse parser;
    if(!userCommand.equals("")) {
      try {
        Process process = Runtime.getRuntime().exec(userCommand.getText() + " -oX " + fileName);
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(
          new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
          output.append(line + "\n");
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
          System.out.println("Success!");
          System.out.println(output);
        }
        parser = new NmapParse(fileName);
        hostList = parser.getDetails();
        System.out.println(hostList.item(0).toString());
      } catch (IOException | InterruptedException | SAXException | ParserConfigurationException e) {
        e.printStackTrace();
      }
    }

  }

  @FXML
  private void generateGraph() {
    //TODO: Check if a file exists
    if (!fileInput.getText().equals("")) {
      fileName = fileInput.getText() + ".xml";
    } else {
      fileName = "NmapScan.xml";
    }
    runCommand();
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
    for(Vertex v: graph.vertices())  {
      graph.removeVertex(v);
    }
    hosts = new LinkedList<>();
    Random r = new Random();
    graph.insertVertex("localhost");
    int[] ports = new int[3];
    for(int i = 0; i < ports.length-1; i++) {
      ports[i] = r.nextInt((500-1) + 1)+1;
    }
    
    for(int i = 0; i < 10; i++) {
      hosts.add(new Host(String.valueOf(i), "192.168.1."+i, Arrays.toString(ports),"none"));
    }
    
    for(Host host : hosts) {
      graph.insertVertex(host.getHost());
    }
    for(Vertex v : graph.vertices()) {
      graph.insertEdge("localhost", v.element().toString(), v.element().toString());
    }
    graphPanel.update();
  }
  
  public void setUpGraph(Graph<String, String> graph, SmartGraphPanel<String, String> graphPanel) {
    this.graphPanel = graphPanel;
    this.graph = graph;
    graphPanel.setVertexDoubleClickAction(graphVertex -> {
      hosts.get(Integer.parseInt(graphVertex.getUnderlyingVertex().element())).displayData(hostText, ipText, ports, vulns);
    });
  }

  
}
