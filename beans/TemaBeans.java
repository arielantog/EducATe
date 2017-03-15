package beans;

import javax.persistence.*;


@Entity
@Table(name = "Temas")
public class TemaBeans {
	@Id
	private Integer Id;
	private String Descripcion;
	
	
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
