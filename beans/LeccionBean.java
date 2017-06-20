package beans;

import javax.persistence.*;

import dto.LeccionDTO;
import negocio.Leccion;

@Entity
@Table(name="lecciones")
public class LeccionBean {
	@Id
	@Column(name="leccionId")
	private int id;
	private String descripcion;
	private boolean activo;
	
	/*GETTERS AND SETTERS*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public Leccion pasarNegocio() {
		Leccion leccion = new Leccion(id, descripcion, activo);
		return leccion;
	}
	/*DTO*/
	public LeccionDTO pasarDTO() {
		LeccionDTO leccion = new LeccionDTO(id, descripcion, activo);
		return leccion;
	}
	

}
