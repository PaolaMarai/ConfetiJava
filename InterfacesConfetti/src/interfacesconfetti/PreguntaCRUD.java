/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesconfetti;

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

    String queryString = "SELECT p FROM Pregunta p";
    Query query = em.createQuery(queryString);
    List<Pregunta> resultados = query.getResultList();

    return resultados;
  }

  public static void main(String[] args) {
    if (PreguntaCRUD.obtenerPreguntas().isEmpty()) {
      System.out.println("No se encontrtaron resultados");
    } else {
      List<Pregunta> preguntas = PreguntaCRUD.obtenerPreguntas();
      System.out.println(preguntas.size());
      for (Pregunta p : preguntas) {
        
        System.out.println(
                p.getIdpregunta() + " "
                + p.getPregunta() + " "
                + p.getRespuestafalsa1() + " "
                + p.getRespuestafalsa2() + " "
                + p.getRespuestafalsa3() + " "
                + p.getRespuestacorrecta()
        );
      }
    }
  }
}
