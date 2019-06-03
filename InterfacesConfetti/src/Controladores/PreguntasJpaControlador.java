/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Pregunta;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JET
 */
public class PreguntasJpaControlador implements Serializable {

    public void create(Pregunta pregunta) {
        EntityManager em = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
            em = emf.createEntityManager();
            
            em.getTransaction().begin();
            em.persist(pregunta);
            
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            System.out.println("Error en: " + ex.getMessage());
        } finally {
            em.close();
        }

    }

}
