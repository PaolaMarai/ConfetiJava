/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paola
 */
@Entity
@Table(name = "PREGUNTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p")
    , @NamedQuery(name = "Pregunta.findByIdpregunta", query = "SELECT p FROM Pregunta p WHERE p.idpregunta = :idpregunta")
    , @NamedQuery(name = "Pregunta.findByPregunta", query = "SELECT p FROM Pregunta p WHERE p.pregunta = :pregunta")
    , @NamedQuery(name = "Pregunta.findByRespuestafalsa1", query = "SELECT p FROM Pregunta p WHERE p.respuestafalsa1 = :respuestafalsa1")
    , @NamedQuery(name = "Pregunta.findByRespuestafalsa2", query = "SELECT p FROM Pregunta p WHERE p.respuestafalsa2 = :respuestafalsa2")
    , @NamedQuery(name = "Pregunta.findByRespuestafalsa3", query = "SELECT p FROM Pregunta p WHERE p.respuestafalsa3 = :respuestafalsa3")
    , @NamedQuery(name = "Pregunta.findByRespuestacorrecta", query = "SELECT p FROM Pregunta p WHERE p.respuestacorrecta = :respuestacorrecta")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPREGUNTA")
    private Integer idpregunta;
    @Basic(optional = false)
    @Column(name = "PREGUNTA")
    private String pregunta;
    @Basic(optional = false)
    @Column(name = "RESPUESTAFALSA1")
    private String respuestafalsa1;
    @Basic(optional = false)
    @Column(name = "RESPUESTAFALSA2")
    private String respuestafalsa2;
    @Basic(optional = false)
    @Column(name = "RESPUESTAFALSA3")
    private String respuestafalsa3;
    @Basic(optional = false)
    @Column(name = "RESPUESTACORRECTA")
    private String respuestacorrecta;
    @JoinColumn(name = "IDEMISION", referencedColumnName = "IDEMISION")
    @ManyToOne(optional = false)
    private Emision idemision;

    public Pregunta() {
    }

    public Pregunta(Integer idpregunta) {
        this.idpregunta = idpregunta;
    }

    public Pregunta(Integer idpregunta, String pregunta, String respuestafalsa1, String respuestafalsa2, String respuestafalsa3, String respuestacorrecta) {
        this.idpregunta = idpregunta;
        this.pregunta = pregunta;
        this.respuestafalsa1 = respuestafalsa1;
        this.respuestafalsa2 = respuestafalsa2;
        this.respuestafalsa3 = respuestafalsa3;
        this.respuestacorrecta = respuestacorrecta;
    }

    public Integer getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Integer idpregunta) {
        this.idpregunta = idpregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestafalsa1() {
        return respuestafalsa1;
    }

    public void setRespuestafalsa1(String respuestafalsa1) {
        this.respuestafalsa1 = respuestafalsa1;
    }

    public String getRespuestafalsa2() {
        return respuestafalsa2;
    }

    public void setRespuestafalsa2(String respuestafalsa2) {
        this.respuestafalsa2 = respuestafalsa2;
    }

    public String getRespuestafalsa3() {
        return respuestafalsa3;
    }

    public void setRespuestafalsa3(String respuestafalsa3) {
        this.respuestafalsa3 = respuestafalsa3;
    }

    public String getRespuestacorrecta() {
        return respuestacorrecta;
    }

    public void setRespuestacorrecta(String respuestacorrecta) {
        this.respuestacorrecta = respuestacorrecta;
    }

    public Emision getIdemision() {
        return idemision;
    }

    public void setIdemision(Emision idemision) {
        this.idemision = idemision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpregunta != null ? idpregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idpregunta == null && other.idpregunta != null) || (this.idpregunta != null && !this.idpregunta.equals(other.idpregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitites.Pregunta[ idpregunta=" + idpregunta + " ]";
    }
    
}
