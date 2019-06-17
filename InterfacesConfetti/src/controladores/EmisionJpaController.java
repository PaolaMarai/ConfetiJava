/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import entitites.Emision;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitites.Pregunta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Paola
 */
public class EmisionJpaController implements Serializable {
/**
 * 
 */
    public EmisionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/**
 * 
 * @param emision 
 */
    public void create(Emision emision) {
        if (emision.getPreguntaList() == null) {
            emision.setPreguntaList(new ArrayList<Pregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : emision.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getIdpregunta());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            emision.setPreguntaList(attachedPreguntaList);
            em.persist(emision);
            for (Pregunta preguntaListPregunta : emision.getPreguntaList()) {
                Emision oldIdemisionOfPreguntaListPregunta = preguntaListPregunta.getIdemision();
                preguntaListPregunta.setIdemision(emision);
                preguntaListPregunta = em.merge(preguntaListPregunta);
                if (oldIdemisionOfPreguntaListPregunta != null) {
                    oldIdemisionOfPreguntaListPregunta.getPreguntaList().remove(preguntaListPregunta);
                    oldIdemisionOfPreguntaListPregunta = em.merge(oldIdemisionOfPreguntaListPregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
/**
 * 
 * @param emision
 * @throws IllegalOrphanException
 * @throws NonexistentEntityException
 * @throws Exception 
 */
    public void edit(Emision emision) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emision persistentEmision = em.find(Emision.class, emision.getIdemision());
            List<Pregunta> preguntaListOld = persistentEmision.getPreguntaList();
            List<Pregunta> preguntaListNew = emision.getPreguntaList();
            List<String> illegalOrphanMessages = null;
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pregunta " + preguntaListOldPregunta + " since its idemision field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getIdpregunta());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            emision.setPreguntaList(preguntaListNew);
            emision = em.merge(emision);
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    Emision oldIdemisionOfPreguntaListNewPregunta = preguntaListNewPregunta.getIdemision();
                    preguntaListNewPregunta.setIdemision(emision);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                    if (oldIdemisionOfPreguntaListNewPregunta != null && !oldIdemisionOfPreguntaListNewPregunta.equals(emision)) {
                        oldIdemisionOfPreguntaListNewPregunta.getPreguntaList().remove(preguntaListNewPregunta);
                        oldIdemisionOfPreguntaListNewPregunta = em.merge(oldIdemisionOfPreguntaListNewPregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = emision.getIdemision();
                if (findEmision(id) == null) {
                    throw new NonexistentEntityException("The emision with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
/**
 * 
 * @param id
 * @throws IllegalOrphanException
 * @throws NonexistentEntityException 
 */
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emision emision;
            try {
                emision = em.getReference(Emision.class, id);
                emision.getIdemision();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emision with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pregunta> preguntaListOrphanCheck = emision.getPreguntaList();
            for (Pregunta preguntaListOrphanCheckPregunta : preguntaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Emision (" + emision + ") cannot be destroyed since the Pregunta " + preguntaListOrphanCheckPregunta + " in its preguntaList field has a non-nullable idemision field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(emision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
/**
 * 
 * @return Lista de emisiones
 */
    public List<Emision> findEmisionEntities() {
        return findEmisionEntities(true, -1, -1);
    }

    public List<Emision> findEmisionEntities(int maxResults, int firstResult) {
        return findEmisionEntities(false, maxResults, firstResult);
    }

    private List<Emision> findEmisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emision.class));
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
/**
 * 
 * @param id
 * @return 
 */
    public Emision findEmision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emision.class, id);
        } finally {
            em.close();
        }
    }
/**
 * 
 * @return 
 */
    public int getEmisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emision> rt = cq.from(Emision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
