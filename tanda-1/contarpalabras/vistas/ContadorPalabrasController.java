package contarpalabras.vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContadorPalabrasController {
  @FXML
  private TextField cadena;
  @FXML
  private TextArea visor;

  // Event Listener on Button.onAction
  @FXML
  public void contar(ActionEvent event) {
    int contadorDePalabras = 0;
    String cad = cadena.getText();
    cad = cad.trim();

    if (cad.isEmpty()) {
      visor.setText("Has introducido una cadena vacía, no hay nada que contar");
    } else {
      for (int posicion = 0; posicion<=cad.length()-1; posicion++) {
        if (cad.charAt(posicion)==' ') {
          contadorDePalabras++;
          while (posicion<=cad.length()-1 && cad.charAt(posicion)==' ') {
            posicion++;
          }
        }
      }
      contadorDePalabras++;
    }

    visor.clear();
    visor.setText("La cadena " + "\""+cad+"\"" + " está formada por " + contadorDePalabras + " palabras.");
  }
}
