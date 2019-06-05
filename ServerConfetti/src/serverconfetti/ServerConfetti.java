/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverconfetti;

import entitites.Usuario;
import interfacesconfetti.ICliente;
import interfacesconfetti.IServer;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ServerConfetti extends UnicastRemoteObject implements IServer {

    private final List<ICliente> clientes;
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction trans;
    
    private String queryString = "";
    private TypedQuery<Usuario> usuarioQuery;

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

    public ServerConfetti() throws RemoteException{
        super();
        clientes = new ArrayList<>();
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
    public void anadirEmision() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void anadirPreguntas() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //CRUD Usuario
    
    @Override
    public List<Usuario> buscarTodosUsuario() throws RemoteException {
        List<Usuario> listaUsuarios;
        openEMF();
        trans.begin();
        usuarioQuery = em.createNamedQuery("Usuario.findAll", Usuario.class);
        listaUsuarios = usuarioQuery.getResultList();
        trans.commit();
        closeEMF();
        return listaUsuarios;
    }
    
    @Override
    public Usuario buscarUsuarioPorUsuario(String user) throws RemoteException {
        Usuario usuario;
        openEMF();
        trans.begin();
        queryString = "SELECT u FROM Usuario u WHERE u.usuario = :username";
        usuarioQuery = em.createQuery(queryString, Usuario.class);
        usuarioQuery.setParameter("username", user);
        usuario = usuarioQuery.getSingleResult();
        trans.commit();
        closeEMF();
        return usuario;
    }
    
    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) throws RemoteException {
        Usuario usuario;
        openEMF();
        trans.begin();
        queryString = "SELECT u FROM Usuario u WHERE u.correo = :correo";
        usuarioQuery = em.createQuery(queryString, Usuario.class);
        usuarioQuery.setParameter("correo", correo);
        usuario = usuarioQuery.getSingleResult();
        trans.commit();
        closeEMF();   
        return usuario;
    }
    
    @Override
    public Usuario buscarUsuarioPorTelefono(String telefono) throws RemoteException {
        Usuario usuario;
        openEMF();
        trans.begin();
        queryString = "SELECT u FROM Usuario u WHERE u.telefono = :telefono";
        usuarioQuery = em.createQuery(queryString, Usuario.class);
        usuarioQuery.setParameter("telefono", telefono);
        usuario = usuarioQuery.getSingleResult();
        trans.commit();
        closeEMF();   
        return usuario;
    }
    
    @Override
    public boolean agregarUsuario(Usuario usuario) throws RemoteException {
        boolean agregado;
        openEMF();
        trans.begin();
        em.persist(usuario);
        agregado = true;
        trans.commit();
        closeEMF();
        return agregado;  
    }
    
    //Termina CRUD Usuario
    
    //Abrir y cerrar EMF
    @Override
    public void openEMF() throws RemoteException {
        this.emf = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
        this.em = emf.createEntityManager();
        this.trans = em.getTransaction();
    }
    
    @Override
    public void closeEMF() throws RemoteException {
        this.em.close();
        this.emf.close();
    }
    
    //Termina
}
