package numerosprimos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Primo extends Application {

  @Override
  public void start(Stage primaryStage) {
    crearEscenario(primaryStage);
    primaryStage.show();
  }

  private void crearEscenario(Stage primaryStage) {
    primaryStage.setTitle("Números Primos");

    // Elementos Internos
    Label etiqueta = new Label("Cantidad de primos a mostrar: ");
    TextField cantidad = new TextField();
    TextArea primosMostrar = new TextArea();


    Button boton = new Button("Calcular");
    boton.setOnAction(event -> {
      primosMostrar.clear();
      primosMostrar.appendText("1: 2\n");
      calcularPrimos(cantidad, primosMostrar);
    });

    // Caja horizontal interna
    HBox hBox = new HBox(5,etiqueta,cantidad);

    // Caja Vertical principal interna de Scene
    VBox vBox = new VBox();
    vBox.getChildren().add(new Label("Programa para calcular numeros primos."));
    vBox.getChildren().addAll(hBox,primosMostrar,boton);
    vBox.setAlignment(Pos.CENTER);
    vBox.setPadding(new Insets(25));
    vBox.setSpacing(10);

    // Scene
    Scene pantallaPrincipal = new Scene(vBox, 400, 300);

    primaryStage.setScene(pantallaPrincipal);
  }

  private void calcularPrimos(TextField cantidad, TextArea primosMostrar) {
    int cantidadAMostrar = 1;
    try {
      cantidadAMostrar = Integer.parseInt(cantidad.getText());
    } catch (NumberFormatException e) {
      primosMostrar.setText("No es  un numero");
    }

    if (cantidadAMostrar < 1) {
      primosMostrar.setText("0 o menor no te puedo mostrar.");
    } else {
      int cantidadMostrados = 1;
      int num = 3;
      while (cantidadMostrados < cantidadAMostrar) {
        boolean esPrimo = true;
        for (int divisor=3; divisor<=Math.sqrt(num) && esPrimo; divisor+=2) {
          if (num%divisor==0) {
            esPrimo = false;
          }
        }
        if (esPrimo) {
          cantidadMostrados++;
          primosMostrar.appendText(cantidadMostrados+": "+num + "\n");
        }
        num += 2;
      }
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
