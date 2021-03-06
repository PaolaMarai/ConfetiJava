/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesconfetti;

import entitites.Pregunta;
import entitites.Emision;
import entitites.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marai
 */

public interface IServer extends Remote {

    public int registrarCallbackCliente(ICliente cliente) throws RemoteException;

    public void deregistrarCallbackCliente(ICliente cliente) throws RemoteException;

    public void notificarPuntaje(int puntaje, int idCliente) throws RemoteException;

    public void añadirPreguntas(Pregunta pregunta) throws RemoteException;

    public void validarRespuesta(String respuesta) throws RemoteException;

    public void anadirEmision(Emision nuevaEmision) throws RemoteException;

    public List<Emision> obtenerEmisiones() throws RemoteException;
    
    public Emision obtenerEmision(int id) throws RemoteException;
    
    public void eliminarEmision(int id) throws RemoteException;
    
    public void editarEmision(Emision emision) throws RemoteException;
    
    public List<Pregunta> recuperarPreguntas() throws RemoteException;

    
    public List<Emision> buscarTodasEmision() throws RemoteException;
    
    //Peticiones de usuario
    public boolean agregarUsuarioSe(Usuario usuario) throws RemoteException;
    
    public Usuario buscarUsuarioPorUsuarioSe(String user) throws RemoteException;
    
    public Usuario buscarUsuarioPorCorreoSe(String correo) throws RemoteException;
    
    public Usuario buscarUsuaroPorTelefonoSe(String telefono) throws RemoteException;
    //Terminan peticiones de usuario
    
    public Date getFecha() throws RemoteException;
    
    public void getEmision(Emision e) throws RemoteException;
    
    public Emision setEmiision() throws RemoteException;

}
