package beans;

import javax.persistence.*;

@Entity
@Table(name="elementosAvatar")
public class ElementoAvatarBean {
	@Id
	@Column(name="elementoAvatarId")
	private Integer Id;
	private String Descripcion;
	private String Tipo;
	private String Color;
	
	/*GETTERS Y SETTERS*/
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
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}

}
