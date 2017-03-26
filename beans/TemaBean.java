package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import negocio.Tema;

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
	
	
	public TemaBean() {
		lecciones = new ArrayList<LeccionBean>();
	}
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
	public void agregarLeccion(LeccionBean leccionBean) {
		lecciones.add(leccionBean);
	}
	
	public Tema pasarNegocio(){
		//No entiendo por que en Tema hay un solo constructor y por ejemplo en Alumno hay dos
		//y el segundo se utiliza para esta parte
		Tema tema = new Tema(Descripcion);
		return tema;
	}
	
}
