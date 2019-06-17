/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import RMI.Cliente;
import controladores.*;
import entitites.Emision;
import entitites.Pregunta;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLPreguntasController implements Initializable {

    @FXML
    private TextField txtPregunta;

    @FXML
    private TextField txtRespuesta1;

    @FXML
    private TextField txtRespuesta2;

    @FXML
    private TextField txtRespuesta3;

    @FXML
    private TextField txtEmision;

    @FXML
    private Button btGuardar;

    @FXML
    private Button btCancelar;

    private final EmisionJpaController ejc = new EmisionJpaController();
    private final List<Emision> emisiones = ejc.findEmisionEntities();
    private Emision emision = new Emision();

    /**
     * Initializes the controller class.
     */
    public void tamanioCampo(TextField textField, int tam) {
        textField.setOnKeyTyped(event -> {
            int maxCaracter = tam;
            if (textField.getText().length() > maxCaracter) {
                event.consume();
            }
        });
    }

    public void tipoTextoStringNumerico(TextField textField) {
        textField.textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("[\\w\\s]")) {
                        textField.setText(newValue.replaceAll("[^\\w\\s]", ""));
                    }
                });
    }

    @FXML
    public void guardarPregunta() {
        if (validarCampos() != true) {
            try {
                Pregunta pregunta = new Pregunta();

                pregunta.setIdemision(Cliente.server.setEmiision());
                pregunta.setPregunta("¿" + txtPregunta.getText() + "?");
                pregunta.setRespuestacorrecta(txtRespuesta1.getText());
                pregunta.setRespuestafalsa1(txtRespuesta2.getText());
                pregunta.setRespuestafalsa2(txtRespuesta3.getText());
                pregunta.setRespuestafalsa3("Ninguno");

                Cliente.server.añadirPreguntas(pregunta);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText("Guardado");
                alert.setContentText("Pregunta guardada");
                alert.showAndWait();
                
                txtPregunta.setText("");
                txtRespuesta1.setText("");
                txtRespuesta2.setText("");
                txtRespuesta3.setText("");

            } catch (Exception ex) {
                Logger.getLogger(FXMLPreguntasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No entro");
        }

    }

    private boolean validarCampos() {

        boolean vacio = false;
        if (txtPregunta.equals("") || txtRespuesta2.equals("") || txtRespuesta1.equals("") || txtRespuesta3.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("VACIO");
            alert.setHeaderText("CAMPOS VACIOS");
            alert.setContentText("Ingrese datos en todos los campos");
            alert.showAndWait();
            return vacio = true;
        }

        return vacio;
    }

    @FXML
    public void cargarPantallaEmision() {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/EmisionesCRUD.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            closeButtonAction();
        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tamanioCampo(txtPregunta, 150);
        tamanioCampo(txtRespuesta1, 150);
        tamanioCampo(txtRespuesta2, 150);
        tamanioCampo(txtRespuesta3, 150);

        tipoTextoStringNumerico(txtPregunta);
        tipoTextoStringNumerico(txtRespuesta1);
        tipoTextoStringNumerico(txtRespuesta2);
        tipoTextoStringNumerico(txtRespuesta3);

        txtEmision.setDisable(true);

        List<Emision> emisionL = ejc.findEmisionEntities();
        Emision emision1 = emisionL.get(0);

        try {
            txtEmision.setText(Integer.toString(Cliente.server.setEmiision().getIdemision()));
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLPreguntasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
