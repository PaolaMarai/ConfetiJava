package RMI;

import interfacesconfetti.IServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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

    public static IServer server;
    public static CallBackCliente callBackCliente;
    public static Sesion sesion = Sesion.getInstance();

    private void javaRMI() throws RemoteException {
        try {
            callBackCliente = new CallBackCliente();
            Registry registro = LocateRegistry.getRegistry(HOSTNAMESERVER, PORT);
            server = (IServer) registro.lookup(NAMESERVICE);
            server.registrarCallbackCliente(callBackCliente);

        } catch (NotBoundException | RemoteException ex) {
            System.err.println("Error en: " + ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/EmisionesCRUD.fxml"));


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