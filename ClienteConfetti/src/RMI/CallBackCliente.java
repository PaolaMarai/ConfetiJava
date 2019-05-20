/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import interfacesconfetti.ICliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author JET
 */
public class CallBackCliente extends UnicastRemoteObject implements ICliente {

    public CallBackCliente() throws RemoteException {
        super();
    }

    @Override
    public void iniciaProcesamiento() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
