package GUI;

import RMI.Cliente;
import Utilities.PasswordUtils;
import controladores.EmisionJpaController;
import entitites.Emision;
import entitites.Usuario;
import interfacesconfetti.UsuarioCRUD;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
                if(Cliente.sesion.isAdmin() == true) {
                    //ir a emisiones programadas
                } else {
                    if(isEmisionActive()) {
                        //ir a emision
                    } else {
                        //ir a contador
                    }
                    
                }
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
            usuarioLogin = Cliente.server.buscarUsuarioPorUsuarioSe(txtUser.getText());
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
                Cliente.sesion.setAdmin(true);
                autenticado = true;
                
            }
        }

        return autenticado;
    }
    
    @FXML
    public void cargarPantallaRegistro() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FXMLRegistrar.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }
    
    public void closeButtonAction() {
        
    }
    
    public boolean isEmisionActive() {
        List<Emision> listaEmision = null;
        try {
            listaEmision = Cliente.server.buscarTodasEmision();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Emision emision = null;
        for(Emision e : listaEmision) emision = e;
        if(emision.getEnemision() == 1) {
            return true;
        }
        
        return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
