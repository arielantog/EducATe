package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "temas")
public class TemaBean {
	@Id
	@Column (name="temaId")
	private Integer Id;
	private String Descripcion;
	@OneToMany
	@JoinColumn(name="temaId")
	private List <LeccionBean> lecciones;
	
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
	public List<LeccionBean> getLecciones() {
		return lecciones;
	}
	public void setLecciones(List<LeccionBean> lecciones) {
		this.lecciones = lecciones;
	}
	
}
