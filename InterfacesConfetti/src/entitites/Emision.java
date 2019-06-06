/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paola
 */
@Entity
@Table(name = "EMISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emision.findAll", query = "SELECT e FROM Emision e")
    , @NamedQuery(name = "Emision.findByIdemision", query = "SELECT e FROM Emision e WHERE e.idemision = :idemision")
    , @NamedQuery(name = "Emision.findByFecha", query = "SELECT e FROM Emision e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Emision.findByHorainicio", query = "SELECT e FROM Emision e WHERE e.horainicio = :horainicio")
    , @NamedQuery(name = "Emision.findByHorafin", query = "SELECT e FROM Emision e WHERE e.horafin = :horafin")
    , @NamedQuery(name = "Emision.findByEnemision", query = "SELECT e FROM Emision e WHERE e.enemision = :enemision")})
public class Emision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEMISION")
    private Integer idemision;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "HORAINICIO")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Basic(optional = false)
    @Column(name = "HORAFIN")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @Basic(optional = false)
    @Column(name = "ENEMISION")
    private int enemision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idemision")
    private Collection<Pregunta> preguntaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idemision")
    private Collection<Participante> participanteCollection;

    public Emision() {
    }

    public Emision(Integer idemision) {
        this.idemision = idemision;
    }

    public Emision(Integer idemision, Date fecha, Date horainicio, Date horafin, int enemision) {
        this.idemision = idemision;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.enemision = enemision;
    }

    public Integer getIdemision() {
        return idemision;
    }

    public void setIdemision(Integer idemision) {
        this.idemision = idemision;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public int getEnemision() {
        return enemision;
    }

    public void setEnemision(int enemision) {
        this.enemision = enemision;
    }

    @XmlTransient
    public Collection<Pregunta> getPreguntaCollection() {
        return preguntaCollection;
    }

    public void setPreguntaCollection(Collection<Pregunta> preguntaCollection) {
        this.preguntaCollection = preguntaCollection;
    }

    @XmlTransient
    public Collection<Participante> getParticipanteCollection() {
        return participanteCollection;
    }

    public void setParticipanteCollection(Collection<Participante> participanteCollection) {
        this.participanteCollection = participanteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemision != null ? idemision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emision)) {
            return false;
        }
        Emision other = (Emision) object;
        if ((this.idemision == null && other.idemision != null) || (this.idemision != null && !this.idemision.equals(other.idemision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitites.Emision[ idemision=" + idemision + " ]";
    }
    
}
