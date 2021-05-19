package quitarcomentarios;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QuitaComentarios extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/QuitaComentarios.fxml"));
      var root = fxml.<Pane>load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Tu borrador de comentarios favorito");
      primaryStage.show();
      
      primaryStage.setOnCloseRequest(event -> {
        if (confirmaSalir()) {
          primaryStage.close();
        }
      });
    
    } catch (IOException e) {
      System.out.println("Error al cargar el fichero fxml");
      e.printStackTrace();
    }
  }

  private boolean confirmaSalir() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmación");
    alert.setHeaderText("La aplicación va a terminar ¿seguro?");

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
