package negocio;

import java.util.*;

import beans.LeccionBean;
import beans.TemaBean;
import daos.LeccionDao;
import daos.TemaDao;

public class Tema {

	public Tema(String descripcion) {
		id = ID++;
		this.descripcion = descripcion;
		lecciones = new ArrayList<Leccion>();
		activo = true;
		TemaDao.getInstance().grabar(pasarBean());
	}
	public Tema(int id, String descripcion, boolean activo) {
		this.id = id;
		this.descripcion = descripcion;
		lecciones = new ArrayList<Leccion>();
		this.activo = activo;
	}

	private static int ID = 1;
	private int id;
	private String descripcion;
	private List<Leccion> lecciones;
	private boolean activo;

	public int agregarLeccion(String descripcion) {
		Leccion leccion = buscarLeccion(descripcion);
		if (leccion == null){
			Leccion leccion2 = new Leccion(descripcion);
			lecciones.add(leccion2);
			TemaDao.getInstance().actualizar(pasarBean());
			return leccion2.getId();
		}else{
			leccion.activar(descripcion);
			lecciones.add(leccion);
			TemaDao.getInstance().actualizar(pasarBean());
		}
		return leccion.getId();
	}

	public Leccion buscarLeccion(String descripcion) {
		for(Leccion leccion: lecciones)
			if(leccion.getDescripcion().equals(descripcion))
				return leccion;
		return LeccionDao.getInstance().buscar(descripcion);
	}
	
	public Leccion buscarLeccion(int Id) {
		for (Leccion leccion: lecciones)
			if (leccion.getId() == Id)
				return leccion;
		return LeccionDao.getInstance().buscar(this.getId(),Id);
	}
	public int eliminarLeccion(int leccion) {
		Leccion leccion2 = buscarLeccion(leccion);
		if (leccion2 != null && leccion2.isActivo()){
			lecciones.remove(leccion2);
			leccion2.eliminar();
			TemaDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("La leccion no existe");
		}
		return 0;
	}
	public int modificarLeccion(int nroLeccion, String descripcion) {
		Leccion leccion = buscarLeccion(nroLeccion);
		if (leccion != null && leccion.isActivo()){
			Leccion leccion2 = buscarLeccion(descripcion);
			if (leccion2 == null || leccion2.getId() == nroLeccion){
				leccion.modificar(descripcion);
				TemaDao.getInstance().actualizar(pasarBean());
			}else{
				System.out.println("Ya existe una lección con esta descripción");
			}
		}else{
			System.out.println("La lección no existe");
		}
		return 0;
	}

	public void agregarLeccion(Leccion leccion) {
		lecciones.add(leccion);
	}
	
	public void eliminar() {
		activo = false;
		TemaDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String descripcion) {
		setDescripcion(descripcion);
		activo = true;
		TemaDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String descripcion) {
		setDescripcion(descripcion);
		TemaDao.getInstance().actualizar(pasarBean());
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public TemaBean pasarBean() {
		TemaBean temaBean = new TemaBean();
		temaBean.setId(getId());
		temaBean.setDescripcion(getDescripcion());
		temaBean.setActivo(activo);
		for (Leccion leccion: lecciones){
			LeccionBean leccionBean = leccion.pasarBean();
			temaBean.agregarLeccion(leccionBean);
		}
		return temaBean;
	}
	
}