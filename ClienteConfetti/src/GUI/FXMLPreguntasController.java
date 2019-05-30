/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
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
    private Button btGuardar;

    @FXML
    private Button btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
