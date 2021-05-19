package contarpalabras;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContarPalabras extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/ContadorPalabras.fxml"));
    try {
      var root = fxml.<VBox>load();
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle("¡Tu contador de palabras!");
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
