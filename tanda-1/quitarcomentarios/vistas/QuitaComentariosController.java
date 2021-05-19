package quitarcomentarios.vistas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QuitaComentariosController {
  @FXML
  private TextField fieldArchivo;
  @FXML
  private Button btnBuscar;
  @FXML
  private Button btnEliminar;
  @FXML
  private TextArea visor;

  private File selectedFile;

  // Event Listener on Button[#btnBuscar].onAction
  @FXML
  public void buscarArchivo(ActionEvent event) {
    Scene scene = visor.getScene();
    Stage stage = (Stage) scene.getWindow();


    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Buscar Archivo");

    // Agregar filtros para facilitar la busqueda
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Java Archivos", "*.java")
        );

    selectedFile = fileChooser.showOpenDialog(stage);

    fieldArchivo.setText(selectedFile.getAbsolutePath());
  }

  // Event Listener on Button[#btnEliminar].onAction
  @FXML
  public void eliminarComentarios(ActionEvent event) {
    if (fieldArchivo.getText().isEmpty()) {
      noIntroducido();
    } else {
      try {
        BufferedReader lectura = new BufferedReader(new FileReader(fieldArchivo.getText()));

        // necesaria para detectar bloques de comentarios /* ...*/
        boolean estoyEnBloqueComentario = false;
        // Leo cada línea del fichero origen y si no es un comentario la escribo en el destino.
        String linea = lectura.readLine();
        while (linea != null) {
          // ¿Bloque /*....*/
          boolean escribeLinea = true;
          if (estoyEnBloqueComentario) {
            if (linea.trim().endsWith("*/")) {
              estoyEnBloqueComentario = false;
            }
          } else if (linea.trim().startsWith("/*")) {
            estoyEnBloqueComentario = true;
          } else {

            if (linea.contains("//")) { // ¿hay comentario en la línea?
              // quitar de línea el comentario //
              int dondeEmpiezaBarra2 = linea.indexOf("//");
              linea = linea.substring(0, dondeEmpiezaBarra2);
              // si la línea contiene solo espacios o está vacía, no la escribo.
              if (linea.trim().equals("")) {
                escribeLinea = false;
              }
            }
            // escribimos línea sin comentarios
            if (escribeLinea && !estoyEnBloqueComentario) {
              visor.appendText(linea);
              visor.appendText("\n"); // añade salto de línea
            }
          }

          // Leemos nueva línea
          linea = lectura.readLine();
        }
        System.out.println("Archivo sin comentarios creado.");
        // Cerramos los archivos.
        lectura.close();

      } catch (FileNotFoundException e) {
        System.err.println("No se encuentra el archivo.");
        archivoNoEncontrado(e);
      } catch (IOException e) {
        System.err.println("Error de entrada/salida al manejar el fichero");
        archivoNoEncontrado(e);
      }
    }
  }

  private boolean archivoNoEncontrado(Exception e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error!");
    alert.setHeaderText("Archivo no encontrado! " + e.getClass().getResource(null));

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }

  private boolean noIntroducido() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error!");
    alert.setHeaderText("No has escogido un archivo, hazlo!");

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }



  private boolean confirmaSalir() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmación");
    alert.setHeaderText("La aplicación va a terminar ¿seguro?");

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }
}
