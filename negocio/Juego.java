package negocio;

import java.util.*;

import beans.JuegoBean;
import beans.LeccionBean;
import daos.JuegoDao;

public class Juego {

	public Juego(String nombre, Tema tema) {
		Id = ID++;
		Nombre = nombre;
		Tema = tema;
		Lecciones = new ArrayList<Leccion>();
		JuegoDao.getInstance().grabar(pasarBean());
	}
	
	//Para el DAO
	public Juego() {
		super();

	}

	private static Integer ID = 1;
	private Integer Id;
	private String Nombre;
	private Tema Tema;
	private List<Leccion> Lecciones;

	public Integer agregarLeccion(Leccion leccion) {
		if(!tengoLeccion(leccion)){
			Lecciones.add(leccion);
			JuegoDao.getInstance().actualizar(pasarBean());
			return leccion.getId();
		}
		return null;
	}
	
	public Leccion buscarLeccion(Integer leccion) {
		for (Leccion leccion2: Lecciones)
			if(leccion2.getId()==leccion)
				return leccion2;
		return null;
	}
	
	private boolean tengoLeccion (Leccion leccion){
		for (Leccion leccion2: Lecciones)
			if(leccion2.getId() == leccion.getId())
				return true;
		return false;
	}

	
	/*GETTERS Y SETTERS*/
	public static Integer getID() {
		return ID;
	}
	public static void setID(Integer iD) {
		ID = iD;
	}
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
	public Tema getTema() {
		return Tema;
	}
	public void setTema(Tema tema) {
		Tema = tema;
	}
	public List<Leccion> getLecciones() {
		return Lecciones;
	}
	public void setLecciones(List<Leccion> lecciones) {
		Lecciones = lecciones;
	}
	/*BEAN*/
	public JuegoBean pasarBean() {
		JuegoBean juegoBean = new JuegoBean();
		juegoBean.setId(getId());
		juegoBean.setNombre(getNombre());
		juegoBean.setTema(getTema().pasarBean());
		for (Leccion leccion: Lecciones){
			LeccionBean leccionBean = leccion.pasarBean();
			juegoBean.agregarLeccion(leccionBean);
		}
		return juegoBean;
	}
}