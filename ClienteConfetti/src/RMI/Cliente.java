/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import interfacesconfetti.IServer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

/**
 *
 * @author JET
 */
public class Cliente extends Application {

    private static final int PORT = 3333;
    private static final String NAMESERVICE = "ConfettiServer";
    private static final String HOSTNAMESERVER = "localhost";

    private static IServer server;
    private static CallBackCliente callBackCliente;

    private void javaRMI() throws RemoteException {
        try {
            callBackCliente = new CallBackCliente();
            Registry registro = LocateRegistry.getRegistry(HOSTNAMESERVER, PORT);
            server = (IServer) registro.lookup(NAMESERVICE);
            //server.registraCallBackCliente(callBackCliente);

        } catch (Exception ex) {
            System.err.println("Error en: " + ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/FXMLPreguntas.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Cliente() throws RemoteException {
        javaRMI();
    }

    public static void main(String[] args) {
        launch(args);
        SwingUtilities.invokeLater(() -> {
            try {
                new Cliente();
            } catch (RemoteException ex) {
                System.err.println("Error en " + ex.getMessage());
            }
        });
    }

}
