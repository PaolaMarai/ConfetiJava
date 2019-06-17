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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Paola
 */
public class EmisionesCRUDController implements Initializable {

    @FXML
    private Button buttonCerrarSesion;
    @FXML
    private TableView<Emision> tablaEmisiones;
    @FXML
    private TableColumn<Emision, String> columnFecha;
    @FXML
    private TableColumn<Emision, String> columnInicio;
    @FXML
    private TableColumn<Emision, String> columnFin;
    @FXML
    private Button buttonNuevo;
    @FXML
    private Button buttonBorrar;
    @FXML
    private Button buttonEditar;
    @FXML
    private TableColumn<Emision, Integer> idEmision;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.idEmision.setCellValueFactory(new PropertyValueFactory<>("idemision"));
            this.columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            this.columnInicio.setCellValueFactory(new PropertyValueFactory<>("horainicio"));
            this.columnFin.setCellValueFactory(new PropertyValueFactory<>("horafin"));
            this.llenarTabla();
        } catch (RemoteException ex) {
            Logger.getLogger(EmisionesCRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        this.abrirVentana("FXMLInicioSesion.fxml");
        try {
            Cliente.server.deregistrarCallbackCliente(Cliente.callBackCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(CrearEmisionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cerrarVentana(buttonCerrarSesion);
    }

    @FXML
    private void nuevaEmision(ActionEvent event) throws IOException {
        this.abrirVentana("CrearEmision.fxml");
        this.cerrarVentana(buttonNuevo);
    }

    @FXML
    private void remover(ActionEvent event) throws RemoteException {
        Emision e = this.tablaEmisiones.getSelectionModel().getSelectedItem();
        if(e != null){
            Cliente.server.eliminarEmision(e.getIdemision());
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el personal que desea editar");
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        
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
    
    private void llenarTabla() throws RemoteException{
        List<Emision> emisiones;
        emisiones = Cliente.server.obtenerEmisiones();
        for(Emision e: emisiones){
            tablaEmisiones.getItems().add(e);
        }
    }
}
