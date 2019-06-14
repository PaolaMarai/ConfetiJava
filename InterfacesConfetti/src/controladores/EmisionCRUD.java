package controladores;

import entitites.Emision;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ángel Sanchez
 */
public class EmisionCRUD {

  private static EntityManagerFactory emFactory;
  private static EntityManager em;

  public static void registrarEmision() {
    em.getTransaction().begin();

    Emision proxima = new Emision();

    em.getTransaction().commit();
    em.close();
    emFactory.close();
  }

  public static Emision obtenerProximaEmision() {
    emFactory = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
    em = emFactory.createEntityManager();
    
    String queryString = "SELECT e FROM Emision e";
    Query query = em.createQuery(queryString);
    List<Emision> proximaEmision = query.getResultList();
    
    Emision proxima = null;
    for(Emision emision : proximaEmision) {
      proxima = emision;
    }
    
    return proxima;
  }
}
