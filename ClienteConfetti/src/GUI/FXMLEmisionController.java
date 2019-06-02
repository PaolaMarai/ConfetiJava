package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
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

  /**
   * Valida si la respuesta que dio el jugador es correcta.
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
  
  private void countTime() {
    Timer timer = new Timer();
    TimerTask runTime = new TimerTask() {
      double progress = 0;
      
      @Override
      public void run() {
        lbPregunta.setText("");
        firstAnswer.setText("");
        secondAnswer.setText("");
        thirdAnswer.setText("");
        Platform.runLater(() -> {
          progress += 0.001;
          remaininTime.setProgress(progress);
        }
        );
      }
    };
    
    timer.schedule(runTime, 0, 10);
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    remaininTime.setStyle("-fx-accent: black;");
    
// TODO
    lbPregunta.setText("¿Tiene Beto ganas de ir a tomar?");
    firstAnswer.setText("Sí");
    secondAnswer.setText("Quién sabe");
    thirdAnswer.setText("No");
    countTime();
  }
}
