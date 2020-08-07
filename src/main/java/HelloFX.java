import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartRandomPlacementStrategy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloFX extends Application {
  Graph<String, String> g = new GraphEdgeList<>();
//... see example below
  
  SmartPlacementStrategy strategy = new SmartRandomPlacementStrategy();
  SmartGraphPanel<String, String> graphView = new SmartGraphPanel<>(g, strategy);
  @Override
  public void start(Stage stage) throws IOException {
    
    stage.setTitle("Hello");
    FXMLLoader myLoader = new FXMLLoader(getClass().getResource("main.fxml"));
    VBox myPane = myLoader.load();
    Scene myScene = new Scene(myPane);
    HBox SceneBox = (HBox) myPane.getChildren().get(1);
    SubScene subBox = (SubScene) SceneBox.getChildren().get(1);
    subBox.setRoot(graphView);
    subBox.setHeight(subBox.getHeight());
    subBox.setWidth(subBox.getWidth());
    stage.setScene(myScene);
    stage.show();
    graphView.setAutomaticLayout(true);
    graphView.init();
    
    
  }
  
  public static void main(String[] args) {
    launch();
  }
  
  
}