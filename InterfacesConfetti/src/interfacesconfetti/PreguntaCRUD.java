package interfacesconfetti;

import entitites.Emision;
import entitites.Pregunta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ángel Sanchez
 */
public class PreguntaCRUD {

  private static EntityManagerFactory emFactory;
  private static EntityManager em;

  public static List<Pregunta> obtenerPreguntas() {
    emFactory = Persistence.createEntityManagerFactory("InterfacesConfettiPU");
    em = emFactory.createEntityManager();

    Emision proximaEmision = EmisionCRUD.obtenerProximaEmision();
    String queryString = "SELECT p FROM Pregunta p WHERE p.idemision = :idemision";
    Query query = em.createQuery(queryString);
    query.setParameter("idemision", proximaEmision.getIdemision());
    List<Pregunta> resultados = query.getResultList();
    System.out.println(resultados.getClass());
    return resultados;
  }

  public static void main(String[] args) {
    if (PreguntaCRUD.obtenerPreguntas().isEmpty()) {
      System.out.println("No se encontrtaron resultados");
    } else {
      List<Pregunta> preguntas = PreguntaCRUD.obtenerPreguntas();
      System.out.println(preguntas.size());
      for (int i = 0; i< preguntas.size(); i++) {        
        System.out.println(
                preguntas.get(i).getIdpregunta() + " "
                + preguntas.get(i).getPregunta() + " "
                + preguntas.get(i).getRespuestafalsa1() + " "
                + preguntas.get(i).getRespuestafalsa2() + " "
                + preguntas.get(i).getRespuestafalsa3() + " "
                + preguntas.get(i).getRespuestacorrecta()
        );
      }
    }
  }
}
