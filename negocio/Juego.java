package negocio;

import java.util.*;

import beans.JuegoBean;
import beans.LeccionBean;
import daos.JuegoDao;

public class Juego {

	public Juego(String nombre, Tema tema) {
		id = ID++;
		this.nombre = nombre;
		this.tema = tema;
		lecciones = new ArrayList<Leccion>();
		activo = true;
		JuegoDao.getInstance().grabar(pasarBean());
	}
	
	public Juego(int id, String nombre, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		lecciones = new ArrayList<Leccion>();
	}

	private static int ID = 1;
	private int id;
	private String nombre;
	private Tema tema;
	private List<Leccion> lecciones;
	private boolean activo;

	public int agregarLeccion(Leccion leccion) {
		if(!tengoLeccion(leccion)){
			lecciones.add(leccion);
			JuegoDao.getInstance().actualizar(pasarBean());
			return leccion.getId();
		}
		System.out.println("Ya existe la lección en el juego");
		return leccion.getId();
	}
	
	public Leccion buscarLeccion(int leccion) {
		for (Leccion leccion2: lecciones)
			if(leccion2.getId()==leccion)
				return leccion2;
		return null;
	}
	
	private boolean tengoLeccion (Leccion leccion){
		for (Leccion leccion2: lecciones)
			if(leccion2.getId() == leccion.getId())
				return true;
		return false;
	}

	
	/*GETTERS Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
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
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public List<Leccion> getLecciones() {
		return lecciones;
	}
	public void setLecciones(List<Leccion> lecciones) {
		this.lecciones = lecciones;
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
		for (Leccion leccion: lecciones){
			LeccionBean leccionBean = leccion.pasarBean();
			juegoBean.agregarLeccion(leccionBean);
		}
		juegoBean.setActivo(activo);
		return juegoBean;
	}

	public void agregarLeccion(Leccion leccion, boolean b) {
		//Se utiliza para pasar negocio
		lecciones.add(leccion);	
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
			lecciones.remove(leccion2);
			JuegoDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("La lección no existe");
		}
		return 0;
	}

	public void eliminarLeccion(int leccion) {
		for (Leccion leccion2: lecciones){
			if (leccion2.getId() == leccion){
				lecciones.remove(leccion2);
				JuegoDao.getInstance().actualizar(pasarBean());
				return;
			}
		}
		
	}

}