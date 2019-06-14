package GUI;

import RMI.Cliente;
import Utilities.PasswordUtils;
import entitites.Usuario;
import controladores.UsuarioCRUD;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JET
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btEntrar;

    @FXML
    private Label lbRegistrate;

    @FXML
    private Label lblError;

    public void loginUsuario() {
        String error = "";
        error = validarCampos();
        if (!error.isEmpty()) {
            lblError.setText(error);
        } else {
            if (autenticarCredenciales()) {
                System.out.println("Usuario Autenticado");
            } else {
                System.out.println("Credenciales incorrectas");
            }
        }
    }

    public String validarCampos() {
        String errores = "";
        if (txtUser.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            errores = errores + "Por favor rellene todos los campos\n";
        }
        return errores;
    }

    public boolean autenticarCredenciales() {
        boolean autenticado = false;
        UsuarioCRUD us = new UsuarioCRUD();
        Usuario usuarioLogin = null;
        try {
            usuarioLogin = us.buscarUsuarioPorUsuario(txtUser.getText());
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuarioLogin != null) {
            boolean match = false;
            String pass = txtPassword.getText();
            String passwordMatch = "";
            try {
                passwordMatch = PasswordUtils.hashPassword(pass);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (passwordMatch.compareTo(usuarioLogin.getClave()) == 0) {
                Cliente.sesion.setUsuario(usuarioLogin.getUsuario());
                Cliente.sesion.setPass(usuarioLogin.getClave());
                autenticado = true;
            }
        }

        return autenticado;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
