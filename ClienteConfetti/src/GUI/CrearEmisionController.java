/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import RMI.Cliente;
import entitites.Emision;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paola
 */
public class CrearEmisionController implements Initializable {

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
     * Se inicia la pantalla de crear emision.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactoryHoras
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24, 15);
        SpinnerValueFactory<Integer> valueFactoryMin
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59, 00);
        SpinnerValueFactory<Integer> valueFactoryHoras2
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 24, 13);
        SpinnerValueFactory<Integer> valueFactoryMin2
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59, 30);
        SpinnerValueFactory<Integer> valueFactoryMonto
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000, 50000);

        this.inicioHoraField.setValueFactory(valueFactoryHoras);
        this.finHoraField.setValueFactory(valueFactoryHoras2);
        this.inicioMinutosField.setValueFactory(valueFactoryMin);
        this.finMinutosField.setValueFactory(valueFactoryMin2);
        this.montoField.setValueFactory(valueFactoryMonto);
        this.montoField.setEditable(true);
        this.finMinutosField.setEditable(true);
        this.inicioMinutosField.setEditable(true);
    }

    @FXML
    private void crearEmision(ActionEvent event) throws RemoteException, IOException {
        Emision emision = new Emision();
        String date = this.dateField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String inicio = this.inicioHoraField.getValue() + ":" + this.inicioMinutosField.getValue() + ":00";
        String fin = this.finHoraField.getValue() + ":" + this.finMinutosField.getValue() + ":00";
        System.out.println(inicio);

        emision.setFecha(date);
        emision.setFechafin(date);
        emision.setHorafin(fin);
        emision.setHorainicio(inicio);
        emision.setEnemision(0);

        Cliente.server.anadirEmision(emision);
        this.abrirVentana("EmisionesCRUD.fxml");
        this.cerrarVentana(crearEminisonButton);
    }

    private void abrirVentana(String ventana) throws IOException {
        Stage stage = new Stage();
        Parent pane = FXMLLoader.load(getClass().getResource(ventana));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void cerrarVentana(Button boton) {
        Stage stage = (Stage) boton.getScene().getWindow();
        stage.close();
    }
}
