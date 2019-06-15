/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exception.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitites.Emision;
import entitites.Pregunta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JET
 */
public class PreguntaJpaController implements Serializable {

    private List<Pregunta> listaPreguntas;
    
    public PreguntaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pregunta pregunta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emision idemision = pregunta.getIdemision();
            if (idemision != null) {
                idemision = em.getReference(idemision.getClass(), idemision.getIdemision());
                pregunta.setIdemision(idemision);
            }
            em.persist(pregunta);
            if (idemision != null) {
                idemision.getPreguntaList().add(pregunta);
                idemision = em.merge(idemision);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pregunta pregunta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta persistentPregunta = em.find(Pregunta.class, pregunta.getIdpregunta());
            Emision idemisionOld = persistentPregunta.getIdemision();
            Emision idemisionNew = pregunta.getIdemision();
            if (idemisionNew != null) {
                idemisionNew = em.getReference(idemisionNew.getClass(), idemisionNew.getIdemision());
                pregunta.setIdemision(idemisionNew);
            }
            pregunta = em.merge(pregunta);
            if (idemisionOld != null && !idemisionOld.equals(idemisionNew)) {
                idemisionOld.getPreguntaList().remove(pregunta);
                idemisionOld = em.merge(idemisionOld);
            }
            if (idemisionNew != null && !idemisionNew.equals(idemisionOld)) {
                idemisionNew.getPreguntaList().add(pregunta);
                idemisionNew = em.merge(idemisionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pregunta.getIdpregunta();
                if (findPregunta(id) == null) {
                    throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta pregunta;
            try {
                pregunta = em.getReference(Pregunta.class, id);
                pregunta.getIdpregunta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.", enfe);
            }
            Emision idemision = pregunta.getIdemision();
            if (idemision != null) {
                idemision.getPreguntaList().remove(pregunta);
                idemision = em.merge(idemision);
            }
            em.remove(pregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pregunta> findPreguntaEntities() {
        return findPreguntaEntities(true, -1, -1);
    }

    public List<Pregunta> findPreguntaEntities(int maxResults, int firstResult) {
        return findPreguntaEntities(false, maxResults, firstResult);
    }

    private List<Pregunta> findPreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pregunta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pregunta findPregunta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pregunta> rt = cq.from(Pregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    

    private List<Pregunta> listaPreguntasEmision;
    
    public List<Pregunta> findPreguntasForEmision(int idEmision) {
        
        EntityManager em;
        em = emf.createEntityManager();

        String query = "SELECT p FROM Pregunta p WHERE p.idEmision = :idEmision";
        Query q = em.createQuery(query);
        q.setParameter("idCliente", idEmision); 
           
        try {
            listaPreguntasEmision = q.getResultList();

        } catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
        
        
        listaPreguntasEmision = q.getResultList();
        
        return listaPreguntasEmision;
    }
}
