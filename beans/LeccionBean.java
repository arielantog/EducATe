package beans;

import javax.persistence.*;

import negocio.Leccion;

@Entity
@Table(name="lecciones")
public class LeccionBean {
	@Id
	@Column(name="leccionId")
	private Integer Id;
	private String Descripcion;
	private boolean activo;
	
	/*GETTERS AND SETTERS*/
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public Leccion pasarNegocio() {
		Leccion leccion = new Leccion(Id, Descripcion, activo);
		return leccion;
	}
	

}
