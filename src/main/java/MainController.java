import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    buildGraph();
  }

  public void buildGraph() {
    try {
      for (int temp = 0; temp < hostList.getLength(); temp++) {
        Node nNode = hostList.item(temp);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        /*if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          System.out.println("Student roll no : "
            + eElement.getAttribute("addr"));
          System.out.println("First Name : "
            + eElement
            .getElementsByTagName("firstname")
            .item(0)
            .getTextContent());
          System.out.println("Last Name : "
            + eElement
            .getElementsByTagName("lastname")
            .item(0)
            .getTextContent());
          System.out.println("Nick Name : "
            + eElement
            .getElementsByTagName("nickname")
            .item(0)
            .getTextContent());
          System.out.println("Marks : "
            + eElement
            .getElementsByTagName("marks")
            .item(0)
            .getTextContent());
        }*/
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
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
