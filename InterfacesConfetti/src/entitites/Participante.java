/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitites;

import entitites.Emision;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    , @NamedQuery(name = "Participante.findByUsuarioIdusuario", query = "SELECT p FROM Participante p WHERE p.participantePK.usuarioIdusuario = :usuarioIdusuario")
    , @NamedQuery(name = "Participante.findByEmisionIdemision", query = "SELECT p FROM Participante p WHERE p.participantePK.emisionIdemision = :emisionIdemision")
    , @NamedQuery(name = "Participante.findByIswinner", query = "SELECT p FROM Participante p WHERE p.iswinner = :iswinner")
    , @NamedQuery(name = "Participante.findByPuntaje", query = "SELECT p FROM Participante p WHERE p.puntaje = :puntaje")})
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipantePK participantePK;
    @Basic(optional = false)
    @Column(name = "ISWINNER")
    private int iswinner;
    @Basic(optional = false)
    @Column(name = "PUNTAJE")
    private int puntaje;
    @JoinColumn(name = "EMISION_IDEMISION", referencedColumnName = "IDEMISION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Emision emision;
    @JoinColumn(name = "USUARIO_IDUSUARIO", referencedColumnName = "IDUSUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Participante() {
    }

    public Participante(ParticipantePK participantePK) {
        this.participantePK = participantePK;
    }

    public Participante(ParticipantePK participantePK, int iswinner, int puntaje) {
        this.participantePK = participantePK;
        this.iswinner = iswinner;
        this.puntaje = puntaje;
    }

    public Participante(int usuarioIdusuario, int emisionIdemision) {
        this.participantePK = new ParticipantePK(usuarioIdusuario, emisionIdemision);
    }

    public ParticipantePK getParticipantePK() {
        return participantePK;
    }

    public void setParticipantePK(ParticipantePK participantePK) {
        this.participantePK = participantePK;
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

    public Emision getEmision() {
        return emision;
    }

    public void setEmision(Emision emision) {
        this.emision = emision;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participantePK != null ? participantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participante)) {
            return false;
        }
        Participante other = (Participante) object;
        if ((this.participantePK == null && other.participantePK != null) || (this.participantePK != null && !this.participantePK.equals(other.participantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Participante[ participantePK=" + participantePK + " ]";
    }
    
}
