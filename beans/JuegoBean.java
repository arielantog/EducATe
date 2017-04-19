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
	private int id;
	private String nombre;
	@OneToOne
	@JoinColumn(name="temaId")
	private TemaBean tema;
	@OneToMany
	@JoinColumn(name="juegoId")
	private List<LeccionBean> lecciones;
	private boolean activo;
	
	public JuegoBean() {
		lecciones = new ArrayList<LeccionBean>();
	}
	/*GETTERS AND SETTERS*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TemaBean getTema() {
		return tema;
	}
	public void setTema(TemaBean tema) {
		this.tema = tema;
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
	public Juego pasarNegocio() {
		Juego juego = new Juego(id, nombre, activo);
		juego.setTema(tema.pasarNegocio());
		for(LeccionBean lecciones: lecciones){
			juego.agregarLeccion(lecciones.pasarNegocio(),true);
		}
		return juego;
	}
	public void agregarLeccion(LeccionBean leccionBean) {
		lecciones.add(leccionBean);
	}
}
