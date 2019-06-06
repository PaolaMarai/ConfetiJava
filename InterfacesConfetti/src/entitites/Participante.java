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
@Table(name = "PARTICIPANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p")
    , @NamedQuery(name = "Participante.findByIdparticipante", query = "SELECT p FROM Participante p WHERE p.idparticipante = :idparticipante")
    , @NamedQuery(name = "Participante.findByIswinner", query = "SELECT p FROM Participante p WHERE p.iswinner = :iswinner")
    , @NamedQuery(name = "Participante.findByPuntaje", query = "SELECT p FROM Participante p WHERE p.puntaje = :puntaje")})
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPARTICIPANTE")
    private Integer idparticipante;
    @Basic(optional = false)
    @Column(name = "ISWINNER")
    private int iswinner;
    @Basic(optional = false)
    @Column(name = "PUNTAJE")
    private int puntaje;
    @JoinColumn(name = "IDEMISION", referencedColumnName = "IDEMISION")
    @ManyToOne(optional = false)
    private Emision idemision;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Participante() {
    }

    public Participante(Integer idparticipante) {
        this.idparticipante = idparticipante;
    }

    public Participante(Integer idparticipante, int iswinner, int puntaje) {
        this.idparticipante = idparticipante;
        this.iswinner = iswinner;
        this.puntaje = puntaje;
    }

    public Integer getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(Integer idparticipante) {
        this.idparticipante = idparticipante;
    }

    public int getIswinner() {
        return iswinner;
    }

    public void setIswinner(int iswinner) {
        this.iswinner = iswinner;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Emision getIdemision() {
        return idemision;
    }

    public void setIdemision(Emision idemision) {
        this.idemision = idemision;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparticipante != null ? idparticipante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participante)) {
            return false;
        }
        Participante other = (Participante) object;
        if ((this.idparticipante == null && other.idparticipante != null) || (this.idparticipante != null && !this.idparticipante.equals(other.idparticipante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitites.Participante[ idparticipante=" + idparticipante + " ]";
    }
    
}
