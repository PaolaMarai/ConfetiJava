package GUI;

import controladores.EmisionCRUD;
import controladores.EmisionJpaController;
import controladores.PreguntaJpaController;
import entitites.Emision;
import entitites.Pregunta;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ángel Sanchez
 */
public class FXMLEmisionController implements Initializable {

  @FXML
  private ImageView profilePic;
  @FXML
  private Button btnSignOff;
  @FXML
  private Button firstAnswer;
  @FXML
  private Button secondAnswer;
  @FXML
  private Button thirdAnswer;
  @FXML
  private ProgressBar remaininTime;
  @FXML
  private Label lbPregunta;
  @FXML
  private Label numeroPregunta;

  private List<Pregunta> preguntas;
  private final EmisionJpaController ejc = new EmisionJpaController();

  /**
   * Valida si la respuesta que dió el jugador es correcta.
   *
   * @param answer Respuesta del jugador.
   * @param correctAnswer Respuesta guardada en la base de datos considerada correcta.
   * @return True si el jugador contesta correctamente.
   */
  public boolean isCorrect(String answer, String correctAnswer) {
    if (answer.equals(correctAnswer)) {
      return true;
    }
    return false;
  }

  @FXML
  public void cargarPantallaIniciarSesion() {
    Stage stage = new Stage();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FXMLInicioSesion.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
      closeButtonAction();
    } catch (IOException ex) {
      Logger.getLogger(FXMLCountDownController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  private void closeButtonAction() {
    Stage stage = (Stage) btnSignOff.getScene().getWindow();
    stage.close();
  }

  @FXML
  private void clickFirst() {
    secondAnswer.setDisable(true);
    thirdAnswer.setDisable(true);
    String answer = firstAnswer.getText();
    System.out.println(answer);
  }

  @FXML
  private void clickSecond() {
    firstAnswer.setDisable(true);
    thirdAnswer.setDisable(true);
    String answer = secondAnswer.getText();
    System.out.println(answer);
  }

  @FXML
  private void clickThird() {
    firstAnswer.setDisable(true);
    secondAnswer.setDisable(true);
    String answer = thirdAnswer.getText();
    System.out.println(answer);
  }

  private void disableButtons() {
    firstAnswer.setDisable(true);
    secondAnswer.setDisable(true);
    thirdAnswer.setDisable(true);
  }

  private void enableButtons() {
    firstAnswer.setDisable(false);
    secondAnswer.setDisable(false);
    thirdAnswer.setDisable(false);
  }

  private void startGame() {
    Thread thread = new Thread(() -> {
      int numeroPregunta = 1;
      for (Pregunta p : preguntas) {
        enableButtons();
        try {
          setQuestion(p, numeroPregunta);
          setRemainingTime(numeroPregunta);
          Thread.sleep(5000);
        } catch (InterruptedException ex) {
          Logger.getLogger(FXMLEmisionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        numeroPregunta++;
      }
      Platform.runLater(() -> {
        lbPregunta.setText("El juego ha terminado.");
      });
    });
    thread.start();
  }

  private void setQuestion(Pregunta p, int numeroPregunta) {
    Platform.runLater(() -> {
      this.numeroPregunta.setText(String.valueOf(numeroPregunta));
      lbPregunta.setText(p.getPregunta());
      firstAnswer.setText(p.getRespuestafalsa1());
      secondAnswer.setText(p.getRespuestafalsa2());
      thirdAnswer.setText(p.getRespuestafalsa3());
    });
  }

  private void setRemainingTime(int numeroPregunta) {
    double progress = 0;
    for (int i = 0; i <= 1000; i++) {
      try {
        remaininTime.setProgress(progress);
        progress += .001;
        Thread.sleep(10);
      } catch (InterruptedException ex) {
        Logger.getLogger(FXMLEmisionController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    disableButtons();
    if (numeroPregunta < 10) {
      Platform.runLater(() -> {
        lbPregunta.setText("Cambiando a siguiente pregunta, espera un momento...");
      });
    }
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    remaininTime.setStyle("-fx-accent: black;");
    
    List<Emision> listaEmison = ejc.findEmisionEntities();
    Emision proxima = null;
    
    for(Emision emision : listaEmison) {
      proxima = emision;
    }
    
    Emision emision = ejc.findEmision(proxima.getIdemision());
    preguntas = emision.getPreguntaList();
    startGame();

  }
}
