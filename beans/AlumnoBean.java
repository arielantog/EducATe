package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import negocio.Alumno;

@Entity
@Table(name="alumnos")
public class AlumnoBean extends PersonaBean{
	@Id
	private int Id;
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
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
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
	public Alumno pasarNegocio() {
		Alumno alumno = new Alumno(Id, getTipoDocumento(), getNroDocumento(), getNombre(), getApellido(), Puntos, NivelLietner);
		alumno.setAvatar(Avatar.pasarNegocio());
		for (EnsenianzaBean ensenianzaBean: Ensenianzas){
			alumno.agregarEnsenianza(ensenianzaBean.pasarNegocio());
		}
		return alumno;
	}

}
