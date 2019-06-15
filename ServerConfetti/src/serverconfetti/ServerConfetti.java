/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverconfetti;

import controladores.EmisionJpaController;
import controladores.PreguntaJpaController;
import entitites.Pregunta;
import entitites.Emision;
import interfacesconfetti.ICliente;
import interfacesconfetti.IServer;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerConfetti extends UnicastRemoteObject implements IServer {

    private final List<ICliente> clientes;
    

    private void init() throws RemoteException {
        try {
            String direccion = (InetAddress.getLocalHost()).toString();
            int puerto = 3333;
            System.out.println("Iniciando servidor en " + direccion + ":" + puerto);

            Registry registro = LocateRegistry.createRegistry(puerto);
            registro.bind("ConfettiServer", (IServer) this);

        } catch (Exception ex) {
            System.out.println("Error en " + ex.getMessage());
        }
    }

    public ServerConfetti() throws RemoteException {
        super();
        clientes = new ArrayList<>();
    }

    public static void main(String[] args) throws RemoteException {
        (new ServerConfetti()).init();
    }

    @Override
    public int registrarCallbackCliente(ICliente cliente) throws RemoteException {
        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
        }
        return clientes.indexOf(cliente);
    }

    @Override
    public void deregistrarCallbackCliente(ICliente cliente) throws RemoteException {
        clientes.remove(cliente);
    }

    @Override
    public void notificarPuntaje(int puntaje, int idCliente) throws RemoteException {
        
    }
    @Override
    public void anadirEmision(Emision nuevaEmision) throws RemoteException {
        EmisionJpaController ejm = new EmisionJpaController();
        ejm.create(nuevaEmision);
        System.out.println("Agrego");
    }

    @Override
    public void añadirPreguntas(Pregunta pregunta) throws RemoteException {
        PreguntaJpaController pjc = new PreguntaJpaController();
        pjc.create(pregunta);
    }

    @Override
    public void validarRespuesta(String respuesta) throws RemoteException {

    }

  @Override
  public void recuperarPreguntas() throws RemoteException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
