/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverconfetti;

import controladores.EmisionJpaController;
import controladores.PreguntaJpaController;
import controladores.exception.IllegalOrphanException;
import controladores.exception.NonexistentEntityException;
import entitites.Pregunta;
import entitites.Emision;
import entitites.Usuario;
import interfacesconfetti.ICliente;
import interfacesconfetti.IServer;
import interfacesconfetti.UsuarioCRUD;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Paola
 */

public class ServerConfetti extends UnicastRemoteObject implements IServer {

    private final List<ICliente> clientes;
    private List<Pregunta> preguntas;
    private EmisionJpaController ejc = new EmisionJpaController();
    private Emision emisionSeleccionada;


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
/**
 * 
 * @throws RemoteException 
 */
    public ServerConfetti() throws RemoteException {
        super();
        clientes = new ArrayList<>();
        
    }
/**
 * 
 * @param args
 * @throws RemoteException 
 */
    public static void main(String[] args) throws RemoteException {
        (new ServerConfetti()).init();
    }
/**
 * 
 * @param cliente
 * @return
 * @throws RemoteException 
 */
    @Override
    public int registrarCallbackCliente(ICliente cliente) throws RemoteException {
        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
            System.out.println("Cliente conectado:" + clientes.size());
            
        }
        return clientes.indexOf(cliente);
    }
/**
 * 
 * @param cliente
 * @throws RemoteException 
 */
    @Override
    public void deregistrarCallbackCliente(ICliente cliente) throws RemoteException {
        if(clientes.contains(cliente)) {
            clientes.remove(cliente);
            System.out.println("Cliente desconectado: " + clientes.size());
        }
        
    }
/**
 * 
 * @param puntaje
 * @param idCliente
 * @throws RemoteException 
 */
    @Override
    public void notificarPuntaje(int puntaje, int idCliente) throws RemoteException {

      
    }
    /**
     * 
     * @param nuevaEmision
     * @throws RemoteException 
     */

    @Override
    public void anadirEmision(Emision nuevaEmision) throws RemoteException {
        EmisionJpaController ejm = new EmisionJpaController();
        ejm.create(nuevaEmision);
        System.out.println("Agrego");
    }
/**
 * 
 * @param pregunta
 * @throws RemoteException 
 */
    @Override
    public void añadirPreguntas(Pregunta pregunta) throws RemoteException {
        PreguntaJpaController pjc = new PreguntaJpaController();
        pjc.create(pregunta);
    }
/**
 * 
 * @param respuesta
 * @throws RemoteException 
 */
    @Override
    public void validarRespuesta(String respuesta) throws RemoteException {
        
    }
/**
 * 
 * @return
 * @throws RemoteException 
 */
    @Override
    public List<Emision> obtenerEmisiones() throws RemoteException{
        EmisionJpaController ejm = new EmisionJpaController();
        List<Emision> emisiones = new ArrayList();
        emisiones = ejm.findEmisionEntities();
        return emisiones;
    }
/**
 * 
 * @param id
 * @return
 * @throws RemoteException 
 */
    @Override
    public Emision obtenerEmision(int id) throws RemoteException{
        EmisionJpaController ejm = new EmisionJpaController();
        Emision emision = ejm.findEmision(id);
        return emision;
    }
/**
 * 
 * @param id
 * @throws RemoteException 
 */
    @Override
    public void eliminarEmision(int id) throws RemoteException{
        EmisionJpaController ejm = new EmisionJpaController() ;
        try {
            try {
                ejm.destroy(id);
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(ServerConfetti.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ServerConfetti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * 
 * @param emision
 * @throws RemoteException 
 */
    @Override
    public void editarEmision(Emision emision) throws RemoteException{
        EmisionJpaController ejm = new EmisionJpaController();
        try {
            ejm.edit(emision);
            
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ServerConfetti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServerConfetti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


  @Override
  public List<Pregunta> recuperarPreguntas() throws RemoteException {
    ejc = new EmisionJpaController();
        List<Emision> listaEmison = ejc.findEmisionEntities();
        Emision proxima = null;

        for (Emision emision : listaEmison) {
            proxima = emision;
        }

        Emision emision = ejc.findEmision(proxima.getIdemision());
        preguntas = emision.getPreguntaList();
        System.out.println(preguntas.size());
        return preguntas;
    }

    public List<Emision> buscarTodasEmision() throws RemoteException {
        EmisionJpaController ejm = new EmisionJpaController();
        return ejm.findEmisionEntities();
    }

    @Override
    public boolean agregarUsuarioSe(Usuario usuario) throws RemoteException {
        UsuarioCRUD ucd = new UsuarioCRUD();
        return ucd.agregarUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorUsuarioSe(String user) throws RemoteException {
        UsuarioCRUD ucd = new UsuarioCRUD();
        return ucd.buscarUsuarioPorUsuario(user);
    }

    @Override
    public Usuario buscarUsuarioPorCorreoSe(String correo) throws RemoteException {
        UsuarioCRUD ucd = new UsuarioCRUD();
        return ucd.buscarUsuarioPorCorreo(correo);
    }

  @Override
  public Date getFecha() throws RemoteException {
    List<Emision> listaEmison = ejc.findEmisionEntities();
    Emision emision = null;
    for(Emision e : listaEmison) {
      emision = e;
    }
    
    String fechaEmision = emision.getFecha() + " " + emision.getHorainicio();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    Date fechaE = null;
    try {
      fechaE = sdf.parse(fechaEmision);
    } catch (ParseException ex) {
      Logger.getLogger(ServerConfetti.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return fechaE;
  }

    @Override
    public Usuario buscarUsuaroPorTelefonoSe(String telefono) throws RemoteException {
        UsuarioCRUD ucd = new UsuarioCRUD();
        return ucd.buscarUsuarioPorTelefono(telefono);
    }

    @Override
    public void getEmision(Emision e) throws RemoteException {
        this.emisionSeleccionada = e;
    }

    @Override
    public Emision setEmiision() throws RemoteException {
        return emisionSeleccionada;
    }

   
}
