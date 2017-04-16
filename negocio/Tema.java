package negocio;

import java.util.*;

import beans.LeccionBean;
import beans.TemaBean;
import daos.LeccionDao;
import daos.TemaDao;

public class Tema {

	public Tema(String descripcion) {
		Id = ID++;
		Descripcion = descripcion;
		Lecciones = new ArrayList<Leccion>();
		activo = true;
		TemaDao.getInstance().grabar(pasarBean());
	}
	public Tema(int id, String descripcion, boolean activo) {
		Id = id;
		Descripcion = descripcion;
		Lecciones = new ArrayList<Leccion>();
		this.activo = activo;
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	private List<Leccion> Lecciones;
	private boolean activo;

	public Integer agregarLeccion(String descripcion) {
		Leccion leccion = buscarLeccion(descripcion);
		if (leccion == null){
			Leccion leccion2 = new Leccion(descripcion);
			Lecciones.add(leccion2);
			TemaDao.getInstance().actualizar(pasarBean());
			return leccion2.getId();
		}
		System.out.println("La lección ya existe");
		return leccion.getId();
	}

	public Leccion buscarLeccion(String descripcion) {
		for(Leccion leccion: Lecciones)
			if(leccion.getDescripcion().equals(descripcion))
				return leccion;
		return LeccionDao.getInstance().buscar(descripcion);
	}
	
	public Leccion buscarLeccion(Integer Id) {
		for (Leccion leccion: Lecciones)
			if (leccion.getId() == Id)
				return leccion;
		return null;
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
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
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
	public TemaBean pasarBean() {
		TemaBean temaBean = new TemaBean();
		temaBean.setId(getId());
		temaBean.setDescripcion(getDescripcion());
		temaBean.setActivo(activo);
		for (Leccion leccion: Lecciones){
			LeccionBean leccionBean = leccion.pasarBean();
			temaBean.agregarLeccion(leccionBean);
		}
		return temaBean;
	}
	public void agregarLeccion(Leccion leccion) {
		Lecciones.add(leccion);
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
	
}