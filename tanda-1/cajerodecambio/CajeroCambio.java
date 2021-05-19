package cajerodecambio;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CajeroCambio extends Application {

  @Override
  public void start(Stage primaryStage) {
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/CajeroCambio.fxml"));
    try {
      var root = fxml.<VBox>load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Cajero de Cambio Automatico");
      primaryStage.show();
    } catch (IOException e) {
      System.out.println("Error al cargar el fichero fxml");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
