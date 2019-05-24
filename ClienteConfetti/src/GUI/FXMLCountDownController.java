package GUI;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ángel Sanchez
 */
public class FXMLCountDownController implements Initializable {

  @FXML
  private Button btnSignOff;
  @FXML
  private Label remainingHours = new Label();
  @FXML
  private Label remainingMinutes = new Label();;
  @FXML
  private Label remainingSeconds = new Label();;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    String fecha = "2019/05/23 15:00:00";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    Date next;
    try {
      next = sdf.parse(fecha);
      
      Timer timer = new Timer();
      TimerTask tarea = new TimerTask() {

        @Override
        public void run() {

          long current = new Date().getTime();
          int now = (int) ((next.getTime() - current + 1000) / 1000);
          int seconds = (int) Math.floor(now % 60);
          int minutes = (int) Math.floor(now / 60 % 60);
          int hours = (int) Math.floor(now / 3600 % 24);
          remainingHours.setText(String.valueOf(hours));
          remainingMinutes.setText(String.valueOf(minutes));
          remainingSeconds.setText(String.valueOf(seconds));
          System.out.println(hours + ":" + minutes + ":" + seconds);

        }
      };

      timer.schedule(tarea, 0, 1000);
    } catch (ParseException ex) {
      Logger.getLogger(FXMLCountDownController.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
