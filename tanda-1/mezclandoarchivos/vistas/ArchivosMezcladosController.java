package mezclandoarchivos.vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

public class ArchivosMezcladosController {

  @FXML
  private Button botonArchivo2;

  @FXML
  private TextField archivoResultante;

  @FXML
  private Button botonArchivo1;

  @FXML
  private TextField fieldArchivo1;

  @FXML
  private TextField fieldArchivo2;

  @FXML
  private TextArea visor;

  private File selectedFile;


  @FXML
  public void escogerArchivo(ActionEvent event) {
    Scene scene = visor.getScene();
    Stage stage = (Stage) scene.getWindow();


    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Buscar Archivo");

    // Agregar filtros para facilitar la busqueda
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All Files", "*.*"),
        new FileChooser.ExtensionFilter("TXT", "*.txt")
        );

    selectedFile = fileChooser.showOpenDialog(stage);

    if ( ((Button) event.getSource()).getId() == botonArchivo1.getId()) {
      fieldArchivo1.setText(selectedFile.getAbsolutePath());
    } else if (((Button) event.getSource()).getId() == botonArchivo2.getId()) {
      fieldArchivo2.setText(selectedFile.getAbsolutePath());
    }
  }


  @FXML
  public void fusionarArchivos(ActionEvent event) {
    if (archivoResultante.getText().isEmpty()) {
      noIntroducido();
    } else {
      try {
        BufferedReader lectura1 = new BufferedReader(new FileReader(fieldArchivo1.getText()));
        BufferedReader lectura2 = new BufferedReader(new FileReader(fieldArchivo2.getText()));
        BufferedWriter mezcla = new BufferedWriter(new FileWriter(archivoResultante.getText()));

        mostrarPorPantalla();
        // Leemos y mostramos la primera línea por pantalla.
        String linea1 = lectura1.readLine();
        String linea2 = lectura2.readLine();

        while (linea1 != null && linea2 != null) {
          escribeMezcla(mezcla, linea1, linea2);
          linea1 = lectura1.readLine();
          linea2 = lectura2.readLine();
        }

        if ( linea1 == null ) {
          while ( linea2 != null ) {
            mezcla.write(linea2);
            mezcla.newLine();
            
            visor.appendText(linea2 + "\n");
            
            linea2 = lectura2.readLine();
          }
        } else {
          while ( linea1 != null ) {
            mezcla.write(linea1);
            mezcla.newLine();
            
            visor.appendText(linea1 + "\n");
            
            linea1 = lectura1.readLine();
          }
        }
        

        // Cerramos ficheros
        lectura1.close();
        lectura2.close();
        mezcla.close();
        
        System.out.println("Archivos mezclados correctamente en el fichero " + archivoResultante.getText());
        //visor.setText(Files.readString(Paths.get(archivoResultante.getText())));
        
      } catch (FileNotFoundException error){
        System.err.println("No se encuentra el archivo.");
        archivoNoEncontrado(error);
      } catch (IOException error) {
        System.err.println("Error de entrada/salida al manejar el fichero");
        archivoNoEncontrado(error);
      }
    }
  }
  
  private void escribeMezcla(BufferedWriter mezcla, String linea1, String linea2)
      throws IOException {
    mezcla.write(linea1);
    mezcla.newLine();
    
    visor.appendText(linea1 + "\n");
    
    mezcla.write(linea2);
    mezcla.newLine();
    
    visor.appendText(linea2 + "\n");
  }
  
  private void mostrarPorPantalla() throws IOException {
    visor.setText("Inicio del proceso de mezcla y creación de " + archivoResultante.getText());
    visor.appendText("\nA continuación el contenido del archivo resultante:\n\n");
  }

  private boolean archivoNoEncontrado(Exception error) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error!");
    alert.setHeaderText("Archivo no encontrado! " + error.getClass().getResource(null));

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }

  private boolean noIntroducido() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error!");
    alert.setHeaderText("No has introducido el archivo resultante, hazlo!");

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }

}
