/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

/**
 * FXML Controller class
 *
 * @author Paola
 */
public class CrearEmisionController implements Initializable {

    @FXML
    private Button buttonCerrarSesion;
    @FXML
    private DatePicker dateField;
    @FXML
    private Spinner<?> finHoraField;
    @FXML
    private Spinner<?> finMinutosField;
    @FXML
    private Spinner<LocalTime> inicioHoraField;
    @FXML
    private Spinner<?> inicioMinutosField;
    @FXML
    private Spinner<?> montoField;
    @FXML
    private Button crearEminisonButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
