package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import negocio.Leccion;
import negocio.Tema;

@Entity
@Table(name = "temas")
public class TemaBean {
	@Id
	@Column (name="temaId")
	private int id;
	private String descripcion;
	@OneToMany
	@JoinColumn(name="temaId")
	private List <LeccionBean> lecciones;
	private boolean activo;
	
	
	public TemaBean() {
		lecciones = new ArrayList<LeccionBean>();
	}
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
	public List<LeccionBean> getLecciones() {
		return lecciones;
	}
	public void setLecciones(List<LeccionBean> lecciones) {
		this.lecciones = lecciones;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/*NEGOCIO*/
	public Tema pasarNegocio(){
		/*Turco: No entiendo por que en Tema hay un solo constructor y por ejemplo en Alumno hay dos
		y el segundo se utiliza para esta parte*/
		/*Ariel: Porque el primero le asigna un Id y lo guarda como un registro nuevo.
		El segundo le asigna el Id que ya tenía y no lo guarda*/
		Tema tema = new Tema(id, descripcion, activo);
		for (LeccionBean leccionBean: lecciones)
		{
			Leccion leccion = leccionBean.pasarNegocio();
			tema.agregarLeccion(leccion);
		}
		return tema;
	}
	public void agregarLeccion(LeccionBean leccionBean) {
		lecciones.add(leccionBean);
	}
}
