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
		activo = true;
		JuegoDao.getInstance().grabar(pasarBean());
	}
	
	public Juego(int id, String nombre, boolean activo) {
		Id = id;
		Nombre = nombre;
		this.activo = activo;
		Lecciones = new ArrayList<Leccion>();
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Nombre;
	private Tema Tema;
	private List<Leccion> Lecciones;
	private boolean activo;

	public Integer agregarLeccion(Leccion leccion) {
		if(!tengoLeccion(leccion)){
			Lecciones.add(leccion);
			JuegoDao.getInstance().actualizar(pasarBean());
			return leccion.getId();
		}
		System.out.println("Ya existe la lección en el juego");
		return leccion.getId();
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
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
		juegoBean.setActivo(activo);
		return juegoBean;
	}

	public void agregarLeccion(Leccion leccion, boolean b) {
		//Se utiliza para pasar negocio
		Lecciones.add(leccion);	
	}

	public void eliminar() {
		activo = false;
		JuegoDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String nombre, Tema tema) {
		setNombre(nombre);
		setTema(tema);
		activo = true;
		JuegoDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String nombre, Tema tema) {
		setNombre(nombre);
		setTema(tema);
		JuegoDao.getInstance().actualizar(pasarBean());
	}

	public int quitarLeccion(int leccion) {
		Leccion leccion2 = buscarLeccion(leccion);
		if (leccion2 != null && leccion2.isActivo()){
			Lecciones.remove(leccion2);
			JuegoDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("La lección no existe");
		}
		return 0;
	}

}