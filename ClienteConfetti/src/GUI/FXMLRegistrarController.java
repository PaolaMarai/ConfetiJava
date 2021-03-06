/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import RMI.Cliente;
import Utilities.PasswordUtils;
import entitites.Usuario;
import interfacesconfetti.UsuarioCRUD;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
  private TextField txtUsuario;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private PasswordField txtPassword1;

  @FXML
  private Button btnRegistrar;

  @FXML
  private Button btnLogin;

  @FXML
  private Label lblError;

  @FXML
  private void closeButtonAction() {
    Stage stage = (Stage) btnRegistrar.getScene().getWindow();
    stage.close();
  }

  public void registroUsuario() {
    String error;
    error = validarUsuario();
    if (!error.isEmpty()) {
      lblError.setText(error);
    } else {
      try {
        error = existeUsuario();
      } catch (RemoteException ex) {
        Logger.getLogger(FXMLRegistrarController.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (!error.isEmpty()) {
        lblError.setText(error);
      } else {

        String password = txtPassword.getText();
        String passwordAsegurada = "";
        try {
          passwordAsegurada = PasswordUtils.hashPassword(password);
        } catch (NoSuchAlgorithmException ex) {
          Logger.getLogger(FXMLRegistrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String pin = "123";

        Usuario usuarioAgregar = new Usuario();
        usuarioAgregar.setNombre(txtNombre.getText());
        usuarioAgregar.setApellido(txtApellido.getText());
        usuarioAgregar.setCorreo(txtCorreo.getText());
        usuarioAgregar.setUsuario(txtUsuario.getText());
        usuarioAgregar.setIsadmin(0);
        usuarioAgregar.setPin("123");
        usuarioAgregar.setTelefono(txtTelefono.getText());
        usuarioAgregar.setAutenticado(0);
        usuarioAgregar.setClave(passwordAsegurada);
        try {
          Cliente.server.agregarUsuarioSe(usuarioAgregar);
        } catch (RemoteException ex) {
          Logger.getLogger(FXMLRegistrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        enviarCorreoValidacion();
        closeButtonAction();
        cargarPantallaIniciarSesion();
      }
    }
  }

  public String validarUsuario() {
    String errores = "";
    if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtCorreo.getText().isEmpty()
            || txtTelefono.getText().isEmpty() || txtPassword.getText().isEmpty() || txtPassword1.getText().isEmpty() || txtUsuario.getText().isEmpty()) {

      errores = errores + "Por favor rellene todos los campos\n";

    } else {
      Pattern pattern = Pattern
              .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

      Matcher mather = pattern.matcher(txtCorreo.getText());

      String password1 = txtPassword1.getText();
      String password = txtPassword.getText();

      if (!mather.find()) {
        errores = errores + "El correo ingresado es invalido\n";
      } else if (password.compareTo(password1) != 0) {
        errores = errores + "Las contraseñas no coinciden\n";
      }
    }
    return errores;
  }

  public String existeUsuario() throws RemoteException {
    String errores = "";

    if (Cliente.server.buscarUsuarioPorUsuarioSe(txtUsuario.getText()) != null) {
      errores = errores + "El usuario ya está registrado\n";
    } else if (Cliente.server.buscarUsuarioPorCorreoSe(txtCorreo.getText()) != null) {
      errores = errores + "El correo ya está registrado\n";
    } else if (Cliente.server.buscarUsuaroPorTelefonoSe(txtTelefono.getText()) != null) {
      errores = errores + "El telefono ya está registrado\n";
    }

    return errores;
  }

  public void enviarCorreoValidacion() {

  }

  @FXML
  public void cargarPantallaIniciarSesion() {
    Stage stage = new Stage();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FXMLInicioSesion.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
      closeButtonAction();
    } catch (IOException ex) {
      Logger.getLogger(FXMLCountDownController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Initializes the controller class.
   *
   * @param url
   * @param rb
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
