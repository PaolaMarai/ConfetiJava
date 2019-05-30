package interfacesconfetti;

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

  public static void registrarEmision(String emision) {
    em.getTransaction().begin();

    Emision fecha = new Emision();

    em.getTransaction().commit();
    em.close();
    emFactory.close();
  }

  public static String obtenerFechaSiguienteEmision() {
    emFactory = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
    em = emFactory.createEntityManager();
    
    String queryString = "SELECT e FROM Emision e";
    Query query = em.createQuery(queryString);
    List<Emision> proximaEmision = query.getResultList();
    
    String fechaHora = "";
    for(Emision emision : proximaEmision) {
      fechaHora = emision.getFecha() + " " + emision.getHorainicio();
    }
    
    return fechaHora;
  }
  
  public static int estadoEmision() {
    emFactory = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
    em = emFactory.createEntityManager();
    
    String queryString = "SELECT e FROM Emision e";
    Query query = em.createQuery(queryString);
    List<Emision> emisiones = query.getResultList();
    
    Emision encontrada = new Emision(); 
    int estado = 0;
    for(Emision nueva : emisiones) {
      estado = encontrada.getEnemision();
    }
    
    return  estado;
  }
}
