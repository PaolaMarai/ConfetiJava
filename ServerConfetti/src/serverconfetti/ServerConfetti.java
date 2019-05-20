 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverconfetti;

import interfacesconfetti.ICliente;
import interfacesconfetti.IServer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConfetti implements IServer {
    private final int PORT = 3333;
    private List<ICliente> clientes = new ArrayList<>();
  
     private void init() throws RemoteException {
    try {
      String direccion = (InetAddress.getLocalHost()).toString();
      int puerto = 3333;
      System.out.println("Iniciando servidor en " + direccion + ":" + puerto);

      Registry registro = LocateRegistry.createRegistry(puerto);
      registro.bind("Servidor", (IServer) this);

    } catch (UnknownHostException | AlreadyBoundException | RemoteException ex) {
      Logger.getLogger(ServerConfetti.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
    
    public static void main(String[] args) throws RemoteException {
          (new ServerConfetti()).init();
    }

    @Override
    public int registrarCallbackCliente(ICliente cliente) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deregistrarCallbackCliente(ICliente cliente) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificarPuntaje(int puntaje, int idCliente) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void añadirEmision() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void añadirPreguntas() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
