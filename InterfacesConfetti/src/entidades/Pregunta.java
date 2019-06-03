/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JET
 */
@Entity
@Table(name = "PREGUNTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p")
    , @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta")
    , @NamedQuery(name = "Pregunta.findByPregunta", query = "SELECT p FROM Pregunta p WHERE p.pregunta = :pregunta")
    , @NamedQuery(name = "Pregunta.findByRespuestaFalsa1", query = "SELECT p FROM Pregunta p WHERE p.respuestaFalsa1 = :respuestaFalsa1")
    , @NamedQuery(name = "Pregunta.findByRespuestaFalsa2", query = "SELECT p FROM Pregunta p WHERE p.respuestaFalsa2 = :respuestaFalsa2")
    , @NamedQuery(name = "Pregunta.findByRespuestaFalsa3", query = "SELECT p FROM Pregunta p WHERE p.respuestaFalsa3 = :respuestaFalsa3")
    , @NamedQuery(name = "Pregunta.findByRespuestaCorrecta", query = "SELECT p FROM Pregunta p WHERE p.respuestaCorrecta = :respuestaCorrecta")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPregunta")
    private Integer idPregunta;
    @Basic(optional = false)
    @Column(name = "pregunta")
    private String pregunta;
    @Basic(optional = false)
    @Column(name = "respuestaFalsa1")
    private String respuestaFalsa1;
    @Basic(optional = false)
    @Column(name = "respuestaFalsa2")
    private String respuestaFalsa2;
    @Basic(optional = false)
    @Column(name = "respuestaFalsa3")
    private String respuestaFalsa3;
    @Basic(optional = false)
    @Column(name = "respuestaCorrecta")
    private String respuestaCorrecta;

    public Pregunta() {
    }

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(Integer idPregunta, String pregunta, String respuestaFalsa1, String respuestaFalsa2, String respuestaFalsa3, String respuestaCorrecta) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.respuestaFalsa1 = respuestaFalsa1;
        this.respuestaFalsa2 = respuestaFalsa2;
        this.respuestaFalsa3 = respuestaFalsa3;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaFalsa1() {
        return respuestaFalsa1;
    }

    public void setRespuestaFalsa1(String respuestaFalsa1) {
        this.respuestaFalsa1 = respuestaFalsa1;
    }

    public String getRespuestaFalsa2() {
        return respuestaFalsa2;
    }

    public void setRespuestaFalsa2(String respuestaFalsa2) {
        this.respuestaFalsa2 = respuestaFalsa2;
    }

    public String getRespuestaFalsa3() {
        return respuestaFalsa3;
    }

    public void setRespuestaFalsa3(String respuestaFalsa3) {
        this.respuestaFalsa3 = respuestaFalsa3;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pregunta[ idPregunta=" + idPregunta + " ]";
    }
    
}
