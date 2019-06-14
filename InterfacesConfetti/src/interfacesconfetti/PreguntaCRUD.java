package interfacesconfetti;

import controladores.EmisionCRUD;
import entitites.Emision;
import entitites.Pregunta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ángel Sanchez
 */
public class PreguntaCRUD {

  private static EntityManagerFactory emFactory;
  private static EntityManager em;

  public static List<Pregunta> obtenerPreguntas() {

    Emision emision = EmisionCRUD.obtenerProximaEmision();
    List<Pregunta> resultados = (List<Pregunta>)emision.getPreguntaList();
    
    return resultados;
  }
}
