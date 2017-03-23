package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="alumnos")
public class AlumnoBean extends PersonaBean{
	private Integer Puntos;
	@OneToMany
	@JoinColumn (name="alumnoId")
	private List<EnsenianzaBean> Ensenianzas;
	@OneToOne
	@JoinColumn (name="avatarId")
	private AvatarBean Avatar;
	private int NivelLietner;
	
	public AlumnoBean() {
		Ensenianzas = new ArrayList<EnsenianzaBean>();
	}
	/*GETTERS AND SETTERS*/
	public Integer getPuntos() {
		return Puntos;
	}
	public void setPuntos(Integer puntos) {
		Puntos = puntos;
	}
	public List<EnsenianzaBean> getEnsenianzas() {
		return Ensenianzas;
	}
	public void setEnsenianzas(List<EnsenianzaBean> ensenianzas) {
		Ensenianzas = ensenianzas;
	}
	public AvatarBean getAvatar() {
		return Avatar;
	}
	public void setAvatar(AvatarBean avatar) {
		Avatar = avatar;
	}
	public int getNivelLietner() {
		return NivelLietner;
	}
	public void setNivelLietner(int nivelLietner) {
		NivelLietner = nivelLietner;
	}
	public void agregarEnsenianza(EnsenianzaBean ensenianzaBean) {
		Ensenianzas.add(ensenianzaBean);
	}

}
