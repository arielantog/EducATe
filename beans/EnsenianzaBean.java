package beans;

import java.util.Date;

import javax.persistence.*;

import dto.EnsenianzaDTO;
import negocio.Ensenianza;

@Entity
@Table(name="ensenianzas")
public class EnsenianzaBean {
	@Transient
	private static int NivelMax = 5;
	@Id
	@Column(name="ensenianzaId")
	private int id;
	@OneToOne
	@JoinColumn(name="leccionId")
	private LeccionBean leccion;
	private int nivelRefuerzo;
	private Date fechaUltRepaso;
	
	/*GETTERS AND SETTERS*/
	public static int getNivelMax() {
		return NivelMax;
	}
	public static void setNivelMax(int nivelMax) {
		NivelMax = nivelMax;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LeccionBean getLeccion() {
		return leccion;
	}
	public void setLeccion(LeccionBean leccion) {
		this.leccion = leccion;
	}
	public int getNivelRefuerzo() {
		return nivelRefuerzo;
	}
	public void setNivelRefuerzo(int nivelRefuerzo) {
		this.nivelRefuerzo = nivelRefuerzo;
	}
	public Date getFechaUltRepaso() {
		return fechaUltRepaso;
	}
	public void setFechaUltRepaso(Date date) {
		fechaUltRepaso = date;
	}
	public Ensenianza pasarNegocio() {
		Ensenianza ensenianza = new Ensenianza(id, nivelRefuerzo, fechaUltRepaso);
		ensenianza.setLeccion(leccion.pasarNegocio());
		return ensenianza;
	}
	
	/*DTO*/
	public EnsenianzaDTO pasarDTO() {
		EnsenianzaDTO ensenianza = new EnsenianzaDTO(id, nivelRefuerzo, fechaUltRepaso);
		ensenianza.setLeccion(leccion.pasarDTO());
		return ensenianza;
	}

}
