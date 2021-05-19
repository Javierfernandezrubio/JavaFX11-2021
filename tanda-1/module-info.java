module primerasaplicaciones {
  requires transitive javafx.graphics;  
  requires javafx.controls;  
  requires javafx.fxml;
  requires javafx.base;
  
  opens numerosprimos to javafx.fxml;
  opens adivinarnumero to javafx.fxml;
  opens contarpalabras to javafx.fxml;
  opens contarpalabras.vistas to javafx.fxml;
  opens calculorectangulo to javafx.fxml;
  opens cajerodecambio to javafx.fxml;
  opens cajerodecambio.vistas to javafx.fxml;
  opens mezclandoarchivos to javafx.fxml;
  opens mezclandoarchivos.vistas to javafx.fxml;
  opens quitarcomentarios to javafx.fxml;
  opens quitarcomentarios.vistas to javafx.fxml;

  
  exports numerosprimos;
  exports adivinarnumero;
  exports contarpalabras;
  exports contarpalabras.vistas;
  exports calculorectangulo;
  exports cajerodecambio;
  exports cajerodecambio.vistas;
  exports mezclandoarchivos;
  exports mezclandoarchivos.vistas;
  exports quitarcomentarios;
  exports quitarcomentarios.vistas;
  
}
