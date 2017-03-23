package negocio;

import java.util.*;

import beans.LeccionBean;
import beans.TemaBean;
import daos.TemaDao;

public class Tema {

	public Tema(String descripcion) {
		Id = ID++;
		Descripcion = descripcion;
		Lecciones = new ArrayList<Leccion>();
		TemaDao.getInstance().grabar(pasarBean());
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	private List<Leccion> Lecciones;

	public Integer agregarLeccion(String descripcion) {
		Leccion leccion = buscarLeccion(descripcion);
		if (leccion == null){
			Leccion leccion2 = new Leccion(descripcion);
			Lecciones.add(leccion2);
			TemaDao.getInstance().actualizar(pasarBean());
			return leccion2.getId();
		}
		return 0;
	}

	public Leccion buscarLeccion(String descripcion) {
		for(Leccion leccion: Lecciones)
			if(leccion.getDescripcion().equals(descripcion))
				return leccion;
		return null;
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
	/*BEAN*/
	public TemaBean pasarBean() {
		TemaBean temaBean = new TemaBean();
		temaBean.setId(getId());
		temaBean.setDescripcion(getDescripcion());
		for (Leccion leccion: Lecciones){
			LeccionBean leccionBean = leccion.pasarBean();
			temaBean.agregarLeccion(leccionBean);
		}
		return temaBean;
	}
	
}