/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesconfetti;

import entitites.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author marai
 */
public interface IServer extends Remote{
    public int registrarCallbackCliente(ICliente cliente)throws RemoteException;
    public void deregistrarCallbackCliente(ICliente cliente)throws RemoteException;
    public void notificarPuntaje(int puntaje, int idCliente)throws RemoteException;
    public void anadirEmision()throws RemoteException;
    public void anadirPreguntas()throws RemoteException;
    
    //CRUD Usuario
    public List<Usuario> buscarTodosUsuario()throws RemoteException;
    public Usuario buscarUsuarioPorUsuario(String user)throws RemoteException;
    public Usuario buscarUsuarioPorCorreo(String correo)throws RemoteException;
    public Usuario buscarUsuarioPorTelefono(String telefono)throws RemoteException;
    public boolean agregarUsuario(Usuario usuario)throws RemoteException;
    //Termina CRUD Usuario
    
    //Abrir y cerrar EMF
    public void openEMF()throws RemoteException;
    public void closeEMF()throws RemoteException;
    //Termina
}
