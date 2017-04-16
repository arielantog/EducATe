package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import negocio.Juego;

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
	@JoinColumn(name="juegoId")
	private List<LeccionBean> Lecciones;
	private boolean activo;
	
	public JuegoBean() {
		Lecciones = new ArrayList<LeccionBean>();
	}
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	/*NEGOCIO*/
	public Juego pasarNegocio() {
		Juego juego = new Juego(Id, Nombre, activo);
		juego.setTema(Tema.pasarNegocio());
		for(LeccionBean lecciones: Lecciones){
			juego.agregarLeccion(lecciones.pasarNegocio(),true);
		}
		return juego;
	}
	public void agregarLeccion(LeccionBean leccionBean) {
		Lecciones.add(leccionBean);
	}
}
