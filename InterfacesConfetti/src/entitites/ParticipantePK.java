/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ángel Sanchez
 */
@Embeddable
public class ParticipantePK implements Serializable {

  @Basic(optional = false)
  @Column(name = "USUARIO_IDUSUARIO")
  private int usuarioIdusuario;
  @Basic(optional = false)
  @Column(name = "EMISION_IDEMISION")
  private int emisionIdemision;

  public ParticipantePK() {
  }

  public ParticipantePK(int usuarioIdusuario, int emisionIdemision) {
    this.usuarioIdusuario = usuarioIdusuario;
    this.emisionIdemision = emisionIdemision;
  }

  public int getUsuarioIdusuario() {
    return usuarioIdusuario;
  }

  public void setUsuarioIdusuario(int usuarioIdusuario) {
    this.usuarioIdusuario = usuarioIdusuario;
  }

  public int getEmisionIdemision() {
    return emisionIdemision;
  }

  public void setEmisionIdemision(int emisionIdemision) {
    this.emisionIdemision = emisionIdemision;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (int) usuarioIdusuario;
    hash += (int) emisionIdemision;
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ParticipantePK)) {
      return false;
    }
    ParticipantePK other = (ParticipantePK) object;
    if (this.usuarioIdusuario != other.usuarioIdusuario) {
      return false;
    }
    if (this.emisionIdemision != other.emisionIdemision) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entitites.ParticipantePK[ usuarioIdusuario=" + usuarioIdusuario + ", emisionIdemision=" + emisionIdemision + " ]";
  }
  
}
