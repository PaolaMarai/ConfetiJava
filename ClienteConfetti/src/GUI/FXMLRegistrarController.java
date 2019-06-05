/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 *
 * @author Beto Lafarc
 */
public class FXMLRegistrarController implements Initializable {

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtApellido;
    
    @FXML
    private TextField txtCorreo;
    
    @FXML
    private TextField txtTelefono;
    
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private PasswordField txtPassword1;
    
    @FXML
    private Button btnRegistrar; 
    
    @FXML
    private Label lbIniciaSesion;
    
    @FXML
    public void registrarUsuarioNuevo() {
        String errores = validarUsuario();
        System.out.println(errores);
    }
    
    public String validarUsuario() {
        String errores = "";
        if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtCorreo.getText().isEmpty()
                || txtTelefono.getText().isEmpty() || txtPassword.getText().isEmpty() || txtPassword1.getText().isEmpty()) {
            
            errores = errores + "Por favor rellene todos los campos\n";
            
        } else {
            Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            
            Matcher mather = pattern.matcher(txtCorreo.getText());
 
            if (!mather.find()) {
                errores =  errores + "El correo ingresado es invalido\n";
            } else {
                
            }
        }
        
        
        return errores;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pattern pattern = Pattern.compile(".{0,25}");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        txtTelefono.setTextFormatter(formatter);
    }    
    
}
