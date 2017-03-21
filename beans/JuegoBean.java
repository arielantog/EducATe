package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name="juegos")
public class JuegoBean {
	@Id
	@Column (name="juegoId")
	private Integer Id;
	private String Nombre;
	@OneToOne
	@JoinColumn(name="temaId")
	private TemaBean Tema;
	@OneToMany
	@JoinColumn(name="leccionId")
	private List<LeccionBean> Lecciones;
	
	/*GETTERS AND SETTERS*/
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public TemaBean getTema() {
		return Tema;
	}
	public void setTema(TemaBean tema) {
		Tema = tema;
	}
	public List<LeccionBean> getLecciones() {
		return Lecciones;
	}
	public void setLecciones(List<LeccionBean> lecciones) {
		Lecciones = lecciones;
	}

}
