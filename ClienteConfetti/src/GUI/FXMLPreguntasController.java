/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controladores.PreguntasJpaControlador;
import entidades.Pregunta;
import entidades.Pregunta_;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        Pregunta pregunta = new Pregunta();
       
        pregunta.setPregunta("Prueba");
        pregunta.setRespuestaCorrecta("uno");
        pregunta.setRespuestaFalsa1("dos");
        pregunta.setRespuestaFalsa2("tres"); 
        pregunta.setRespuestaFalsa3("cuatro");
        
        PreguntasJpaControlador pjpc = new PreguntasJpaControlador();
        pjpc.create(pregunta);
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
        
        guardarPregunta();
    }

}
