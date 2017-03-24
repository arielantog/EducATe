package beans;

import javax.persistence.*;

import negocio.Ensenianza;

@Entity
@Table(name="ensenianzas")
public class EnsenianzaBean {
	@Transient
	private static Integer NivelMax = 5;
	@Id
	@Column(name="ensenianzaId")
	private Integer Id;
	@OneToOne
	@JoinColumn(name="leccionId")
	private LeccionBean Leccion;
	private Integer NivelRefuerzo;
	private Integer FechaUltRepaso;
	
	/*GETTERS AND SETTERS*/
	public static Integer getNivelMax() {
		return NivelMax;
	}
	public static void setNivelMax(Integer nivelMax) {
		NivelMax = nivelMax;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public LeccionBean getLeccion() {
		return Leccion;
	}
	public void setLeccion(LeccionBean leccion) {
		Leccion = leccion;
	}
	public Integer getNivelRefuerzo() {
		return NivelRefuerzo;
	}
	public void setNivelRefuerzo(Integer nivelRefuerzo) {
		NivelRefuerzo = nivelRefuerzo;
	}
	public Integer getFechaUltRepaso() {
		return FechaUltRepaso;
	}
	public void setFechaUltRepaso(Integer fechaUltRepaso) {
		FechaUltRepaso = fechaUltRepaso;
	}
	public Ensenianza pasarNegocio() {
		Ensenianza ensenianza = new Ensenianza(Id, NivelRefuerzo, FechaUltRepaso);
		ensenianza.setLeccion(Leccion.pasarNegocio());
		return ensenianza;
	}

}
