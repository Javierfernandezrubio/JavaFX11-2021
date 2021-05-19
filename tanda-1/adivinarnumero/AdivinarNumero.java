package adivinarnumero;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinarNumero extends Application {

  final static int INTENTOS = 10;
  private int intentos = INTENTOS;
  private int numeroRandom = 1 + (int) (Math.random()*100);
  private TextField numIntroducido = new TextField();
  private TextArea areaInformacion = new TextArea();
  private TextArea numIntentos = new TextArea();

  @Override
  public void start(Stage primaryStage) {
    crearEscenario(primaryStage);
    primaryStage.show();
  }


  private void crearEscenario(Stage primaryStage) {
    primaryStage.setTitle("Adivina el Número");

    // Elementos internos
    Label introduce = new Label("Introduce un número entre 1 y 100:");
    introduce.setAlignment(Pos.CENTER_LEFT);

    numIntentos.setMaxSize(5, 5);
    numIntentos.setText(String.valueOf(intentos)); 

    Label intentosLabel = new Label("Intentos restantes:");

    Button boton = new Button("Intentar");

    boton.setOnKeyPressed(event -> { 
      if (event.getCode() == KeyCode.ENTER)  {

      }
    });

    boton.setOnAction(event -> {
      mostrarInformacion();
      reseteo();
    });



    // Cajas internas
    HBox cajaHorizontalSuperior = new HBox(10,introduce,numIntroducido);
    cajaHorizontalSuperior.setAlignment(Pos.CENTER);

    HBox cajaHorizontalInferior = new HBox(10,intentosLabel,numIntentos,boton);
    cajaHorizontalInferior.setAlignment(Pos.CENTER);

    VBox cajaPrincipal = new VBox(5,cajaHorizontalSuperior,areaInformacion,cajaHorizontalInferior);
    cajaPrincipal.setAlignment(Pos.CENTER);
    cajaPrincipal.setPadding(new Insets(25));
    cajaPrincipal.setSpacing(10);

    // Scene
    Scene pantallaPrincipal = new Scene(cajaPrincipal, 500, 300);

    primaryStage.setScene(pantallaPrincipal);
  }


  private void reseteo() {
    if (intentos <= 0) {
      areaInformacion.setText("Se te han acabado los intentos. \nVamos a resetear el numero aleatorio"
          + " y el numero de intentos.\n"
          + "Vuelve a introducir números!");
      intentos = INTENTOS;
      numeroRandom = 1 + (int) (Math.random()*100);
      numIntroducido.clear();
      numIntentos.setText(String.valueOf(intentos));
    }
  }


  private void mostrarInformacion() {
    try {

      if (Integer.parseInt(numIntroducido.getText()) < numeroRandom) {
        areaInformacion.setText("El numero a adivinar es mayor que " + numIntroducido.getText());
        numIntentos.setText(String.valueOf(--intentos));
      } else if (Integer.parseInt(numIntroducido.getText()) > numeroRandom) {
        areaInformacion.setText("El numero a adivinar es menor que " + numIntroducido.getText());
        numIntentos.setText(String.valueOf(--intentos));
      } else {
        areaInformacion.setText("El numero a adivinar es el introducido");
      }

    } catch (NumberFormatException e) {
      areaInformacion.setText("No has introducido un numero");
    }
  }


  public static void main(String[] args) {
    launch(args);
  }
}
