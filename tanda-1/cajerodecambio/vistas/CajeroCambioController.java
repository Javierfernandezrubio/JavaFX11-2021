package cajerodecambio.vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CajeroCambioController {
  
  private static final int MONEDA_1 = 100;
  private static final int MONEDA_50 = 50;
  private static final int MONEDA_20 = 20;
  private static final int MONEDA_10 = 10;
  private static final int MONEDA_05 = 5;
  private static final int MONEDA_02 = 2;
  private static final int MONEDA_01 = 1;
  private static final int MONEDA_2 = 200;
  private static final int BILLETE_5 = 500;
  private static final int BILLETE_10 = 1000;
  private static final int BILLETE_20 = 2000;
  private static final int BILLETE_50 = 5000;
  private static final int BILLETE_100 = 10000;
  private static final int BILLETE_200 = 20000;
  private static final int BILLETE_500 = 50000;

  @FXML
  private TextField euros;
  @FXML
  private TextArea visor;
  @FXML
  private Button botonCalcular;

  // Euros
  private int centimosTotales;
  private int bill500 = 0;
  private int bill200 = 0;
  private int bill100 = 0;
  private int bill50 = 0;
  private int bill20 = 0;
  private int bill10 = 0;
  private int bill5 = 0;
  private int coin2 = 0;
  private int coin1 = 0;
  private int coin50 = 0;
  private int coin20 = 0;
  private int coin10 = 0;
  private int coin05 = 0;
  private int coin02 = 0;
  private int coin01 = 0;



  @FXML
  void cambiar(ActionEvent event) {
    centimosTotales = (int) (Double.parseDouble(euros.getText()) * 100);

    calcular();
    mostrarResultados();
    reset();
  }
  
  @FXML
  void cambiarIntro(KeyEvent event) {
    if (event.getCode().getCode() == 10) {
      centimosTotales = (int) (Double.parseDouble(euros.getText()) * 100);
      calcular();
      mostrarResultados();
      reset();
    }
  }


  private void reset() {
    bill500 = 0;
    bill200 = 0;
    bill100 = 0;
    bill50 = 0;
    bill20 = 0;
    bill10 = 0;
    bill5 = 0;
    coin2 = 0;
    coin1 = 0;
    coin50 = 0;
    coin20 = 0;
    coin10 = 0;
    coin05 = 0;
    coin02 = 0;
    coin01 = 0;
  }


  void calcular() {
    if (centimosTotales >= BILLETE_500) {
      bill500 = centimosTotales / BILLETE_500;
      centimosTotales = centimosTotales % BILLETE_500;
    }
    if (centimosTotales >= BILLETE_200) {
      bill200 = centimosTotales / BILLETE_200;
      centimosTotales = centimosTotales % BILLETE_200;
    }
    if (centimosTotales >= BILLETE_100) {
      bill100 = centimosTotales / BILLETE_100;
      centimosTotales = centimosTotales % BILLETE_100;
    }
    if (centimosTotales >= BILLETE_50) {
      bill50 = centimosTotales / BILLETE_50;
      centimosTotales = centimosTotales % BILLETE_50;
    }
    if (centimosTotales >= BILLETE_20) {
      bill20 = centimosTotales / BILLETE_20;
      centimosTotales = centimosTotales % BILLETE_20;
    }
    if (centimosTotales >= BILLETE_10) {
      bill10 = centimosTotales / BILLETE_10;
      centimosTotales = centimosTotales % BILLETE_10;
    }
    if (centimosTotales >= BILLETE_5) {
      bill5 = centimosTotales / BILLETE_5;
      centimosTotales = centimosTotales % BILLETE_5;
    }
    if (centimosTotales >= MONEDA_2) {
      coin2 = centimosTotales / MONEDA_2;
      centimosTotales = centimosTotales % MONEDA_2;
    }
    if (centimosTotales >= MONEDA_1) {
      coin1 = centimosTotales / MONEDA_1;
      centimosTotales = centimosTotales % MONEDA_1;
    }
    if (centimosTotales >= MONEDA_50) {
      coin50 = centimosTotales / MONEDA_50;
      centimosTotales = centimosTotales % MONEDA_50;
    }
    if (centimosTotales >= MONEDA_20) {
      coin20 = centimosTotales / MONEDA_20;
      centimosTotales = centimosTotales % MONEDA_20;
    }
    if (centimosTotales >= MONEDA_10) {
      coin10 = centimosTotales / MONEDA_10;
      centimosTotales = centimosTotales % MONEDA_10;
    }
    if (centimosTotales >= MONEDA_05) {
      coin05 = centimosTotales / MONEDA_05;
      centimosTotales = centimosTotales % MONEDA_05;
    }
    if (centimosTotales >= MONEDA_02) {
      coin02 = centimosTotales / MONEDA_02;
      centimosTotales = centimosTotales % MONEDA_02;
    }
    if (centimosTotales >= MONEDA_01) {
      coin01 = centimosTotales / MONEDA_01;
      centimosTotales = centimosTotales % MONEDA_01;
    }

  }

  private void mostrarResultados() {
    visor.clear();
    visor.appendText("Recoga su dinero cambiado:\n");
    
    if (bill500 != 0) {
      visor.appendText(bill500 + " billetes de 500 euros.\n");
    }
    if (bill200 != 0) {
      visor.appendText(bill200 + " billetes de 200 euros.\n");
    }
    if (bill100 != 0) {
      visor.appendText(bill100 + " billetes de 100 euros.\n");
    }
    if (bill50 != 0) {
      visor.appendText(bill50 + " billetes de 50 euros.\n");
    }
    if (bill20 != 0) {
      visor.appendText(bill20 + " billetes de 20 euros.\n");
    }
    if (bill10 != 0) {
      visor.appendText(bill10 + " billetes de 10 euros.\n");
    }
    if (bill5 != 0) {
      visor.appendText(bill5 + " billetes de 5 euros.\n");
    }
    if (coin2 != 0) {
      visor.appendText(coin2 + " monedas de 2 euros.\n");
    }
    if (coin1 != 0) {
      visor.appendText(coin1 + " monedas de 1 euro.\n");
    }
    if (coin50 != 0) {
      visor.appendText(coin50 + " monedas de 50 cents.\n");
    }
    if (coin20 != 0) {
      visor.appendText(coin20 + " monedas de 20 cents.\n");
    }
    if (coin10 != 0) {
      visor.appendText(coin10 + " monedas de 10 cents.\n");
    }
    if (coin05 != 0) {
      visor.appendText(coin05 + " monedas de 5 cents.\n");
    }
    if (coin02 != 0) {
      visor.appendText(coin02 + " monedas de 2 cents.\n");
    }
    if (coin01 != 0) {
      visor.appendText(coin01 + " monedas de 1 cents.\n");
    }
    
  }
  
  /*
  @FXML
  public void initialize() {
    botonCalcular.setDisable(true);

    euros.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("^[0-9]*$")) {
        System.out.println("Cantidad de euros invalida: " + euros.getText());
        euros.setText(oldValue);
      }

      botonCalcular.setDisable(euros.getText().isBlank());
    });
  }*/
}