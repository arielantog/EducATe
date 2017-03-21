package beans;

import javax.persistence.*;

@Entity
@Table(name="lecciones")
public class LeccionBean {
	@Id
	@Column(name="leccionId")
	private Integer Id;
	private String Descripcion;
	
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

}