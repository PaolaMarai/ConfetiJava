/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesconfetti;

import entitites.Usuario;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Beto Lafarc
 */
public class UsuarioCRUD {
    //CRUD Usuario

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction trans;

    private String queryString = "";
    private TypedQuery<Usuario> usuarioQuery;

    public List<Usuario> buscarTodosUsuario() throws RemoteException {
        List<Usuario> listaUsuarios = null;
        openEMF();
        
        try {
            trans.begin();
            usuarioQuery = em.createNamedQuery("Usuario.findAll", Usuario.class);
            listaUsuarios = usuarioQuery.getResultList();
            trans.commit();
        } catch (NoResultException | RollbackException ex) {
            listaUsuarios = null;
            System.out.println("No se pudo acceder al recurso solicitado");
        }
        
        closeEMF();
        return listaUsuarios;
    }

    public Usuario buscarUsuarioPorUsuario(String user) throws RemoteException {
        Usuario usuario = null;
        openEMF();
        try {
            trans.begin();
            usuarioQuery = em.createNamedQuery("Usuario.findByUsuario", Usuario.class);
            usuarioQuery.setParameter("usuario", user);
            usuario = usuarioQuery.getSingleResult();
            trans.commit();
        } catch (NoResultException | RollbackException ex) {
            usuario = null;
            System.out.println("No se pudo acceder al recurso solicitado");
        }
        closeEMF();
        return usuario;
    }

    public Usuario buscarUsuarioPorCorreo(String correo) throws RemoteException {
        Usuario usuario = null;
        openEMF();
        try {
            trans.begin();
            usuarioQuery = em.createNamedQuery("Usuario.findByCorreo", Usuario.class);
            usuarioQuery.setParameter("correo", correo);
            usuario = usuarioQuery.getSingleResult();
            trans.commit();
        } catch (NoResultException | RollbackException ex) {
            usuario = null;
            System.out.println("No se pudo acceder al recurso solicitado");
        }
        closeEMF();
        return usuario;
    }

    public Usuario buscarUsuarioPorTelefono(String telefono) throws RemoteException {
        Usuario usuario = null;
        openEMF();
        try {
            trans.begin();
            usuarioQuery = em.createNamedQuery("Usuario.findByTelefono", Usuario.class);
            usuarioQuery.setParameter("telefono", telefono);
            usuario = usuarioQuery.getSingleResult();
            trans.commit();
        } catch (NoResultException | RollbackException ex) {
            usuario = null;
            System.out.println("No se pudo acceder al recurso solicitado");
        }
        
        closeEMF();
        return usuario;
    }

    public boolean agregarUsuario(Usuario usuario) throws RemoteException {
        boolean agregado = false;
        openEMF();
        try {
            trans.begin();
            em.persist(usuario);
            agregado = true;
            trans.commit();
        } catch (EntityExistsException | RollbackException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeEMF();
        }
        return agregado;
    }
    
    public Usuario buscarCredenciales(String usuario, String pass) throws RemoteException {
        Usuario usuarioLogin = null;
        openEMF();
        try {
            trans.begin();
            usuarioQuery = em.createNamedQuery("Usuario.findByUsuarioClave", Usuario.class);
            usuarioQuery.setParameter("usuario", usuario);
            usuarioQuery.setParameter("clave", pass);
            usuarioLogin = usuarioQuery.getSingleResult();
            trans.commit();
        } catch (NoResultException | RollbackException ex) {
            usuario = null;
            System.out.println("No se pudo acceder al recurso solicitado");
        } finally {
            closeEMF();
        }
        return usuarioLogin;        
    }

    //Termina CRUD Usuario
    //Abrir y cerrar EMF
    public void openEMF() throws RemoteException {
        this.emf = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
        this.em = emf.createEntityManager();
        this.trans = em.getTransaction();
    }

    public void closeEMF() throws RemoteException {
        this.em.close();
        this.emf.close();
    }

    //Termina
}
