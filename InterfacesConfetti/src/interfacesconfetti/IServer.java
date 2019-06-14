/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesconfetti;

import entitites.Pregunta;

import entitites.Emision;

import java.rmi.Remote;
import java.rmi.RemoteException;

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

}
