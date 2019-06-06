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
import javafx.scene.control.SpinnerValueFactory;

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
    private Spinner<Integer> finHoraField;
    @FXML
    private Spinner<Integer> finMinutosField;
    @FXML
    private Spinner<Integer> inicioHoraField;
    @FXML
    private Spinner<Integer> inicioMinutosField;
    @FXML
    private Spinner<Integer> montoField;
    @FXML
    private Button crearEminisonButton;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       SpinnerValueFactory<Integer> valueFactoryHoras = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24, 15);
       SpinnerValueFactory<Integer> valueFactoryMin = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59, 00);
       SpinnerValueFactory<Integer> valueFactoryHoras2= 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24, 13);
       SpinnerValueFactory<Integer> valueFactoryMin2= 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59, 30);
       SpinnerValueFactory<Integer> valueFactoryMonto= 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000, 50000);
       
       this.inicioHoraField.setValueFactory(valueFactoryHoras);
       this.finHoraField.setValueFactory(valueFactoryHoras2);
       this.inicioMinutosField.setValueFactory(valueFactoryMin);
       this.finMinutosField.setValueFactory(valueFactoryMin2);
       this.montoField.setValueFactory(valueFactoryMonto);
       
    }  
    
}
