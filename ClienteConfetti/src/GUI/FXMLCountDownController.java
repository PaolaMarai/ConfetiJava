package GUI;

import entitites.Emision;
import interfacesconfetti.EmisionCRUD;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ángel Sanchez
 */
public class FXMLCountDownController implements Initializable {

  @FXML
  private Button btnSignOff;
  @FXML
  private ImageView profilePic;
  @FXML
  private Label remainingHours;
  @FXML
  private Label remainingMinutes;
  @FXML
  private Label remainingSeconds;

  @FXML
  public void cargarPantallaEmision() {
    Stage stage = new Stage();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FXMLEmision.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
    } catch (IOException ex) {
      Logger.getLogger(FXMLCountDownController.class.getName()).log(Level.SEVERE, null, ex);
    }
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

  private void doCountDown(Date proxima) {
    Timer timer = new Timer();

    TimerTask tarea = new TimerTask() {
      @Override
      public void run() {
        long current = new Date().getTime();
        int now = (int) ((proxima.getTime() - current + 1000) / 1000);
        int seconds = (int) Math.floor(now % 60);
        int minutes = (int) Math.floor(now / 60 % 60);
        int hours = (int) Math.floor(now / 3600 % 24);
        Platform.runLater(() -> {
          if (hours == 0 && minutes == 0 && seconds == -1) {
            timer.cancel();
            cargarPantallaEmision();
            closeButtonAction();
          } else {
            remainingHours.setText(String.valueOf(hours));
            remainingMinutes.setText(String.valueOf(minutes));
            remainingSeconds.setText(String.valueOf(seconds));
          }
        }
        );
      }
    };
    timer.schedule(tarea, 0, 1000);
  }

  /**
   * Valida si hay una transmión actual para saber qué ventana mostrar cuando un usuario inicia
   * sesión.
   *
   * @return El estado de la transmisión.
   */
  public boolean enEmision() {
    Emision emision = EmisionCRUD.obtenerProximaEmision();
    
    if (emision.getEnemision() == 1) {
      return true;
    }
    return false;
  }

  private Date formatearFecha() {
    Emision proxima = EmisionCRUD.obtenerProximaEmision();
    
    String fechaHora = proxima.getFecha() + " " + proxima.getHorainicio();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    Date fecha;
    try {
      fecha = sdf.parse(fechaHora);
      return fecha;
    } catch (ParseException ex) {
      Logger.getLogger(FXMLCountDownController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    Date proximaEmision = formatearFecha();
    doCountDown(proximaEmision);

  }
}
