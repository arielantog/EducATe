package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import negocio.Alumno;

@Entity
@Table(name="alumnos")
public class AlumnoBean extends PersonaBean{
	@Id
	@Column(name="alumnoId")
	private int id;
	private int puntos;
	@OneToMany
	@JoinColumn (name="alumnoId")
	private List<EnsenianzaBean> ensenianzas;
	@OneToOne
	@JoinColumn (name="avatarId")
	private AvatarBean avatar;
	private int nivelLietner;
	private boolean activo;
	
	public AlumnoBean() {
		ensenianzas = new ArrayList<EnsenianzaBean>();
	}
	/*GETTERS AND SETTERS*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public List<EnsenianzaBean> getEnsenianzas() {
		return ensenianzas;
	}
	public void setEnsenianzas(List<EnsenianzaBean> ensenianzas) {
		this.ensenianzas = ensenianzas;
	}
	public AvatarBean getAvatar() {
		return avatar;
	}
	public void setAvatar(AvatarBean avatar) {
		this.avatar = avatar;
	}
	public int getNivelLietner() {
		return nivelLietner;
	}
	public void setNivelLietner(int nivelLietner) {
		this.nivelLietner = nivelLietner;
	}
	public void agregarEnsenianza(EnsenianzaBean ensenianzaBean) {
		ensenianzas.add(ensenianzaBean);
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public Alumno pasarNegocio() {
		Alumno alumno = new Alumno(id, getTipoDocumento(), getNroDocumento(), getNombre(), getApellido(), puntos, nivelLietner, activo);
		alumno.setAvatar(avatar.pasarNegocio());
		for (EnsenianzaBean ensenianzaBean: ensenianzas){
			alumno.agregarEnsenianza(ensenianzaBean.pasarNegocio());
		}
		return alumno;
	}

}
