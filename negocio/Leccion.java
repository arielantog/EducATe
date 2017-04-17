package negocio;

import daos.LeccionDao;
import beans.LeccionBean;

public class Leccion {

	public Leccion(String descripcion) {
		Id = ID++;
		Descripcion = descripcion;
		activo = true;
		LeccionDao.getInstance().grabar(pasarBean());
	}
	public Leccion(int id, String descripcion, boolean activo) {
		Id = id;
		Descripcion = descripcion;
		this.activo = activo;
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	private boolean activo;
	
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*BEAN*/
	public LeccionBean pasarBean() {
		LeccionBean leccionBean = new LeccionBean();
		leccionBean.setId(getId());
		leccionBean.setDescripcion(getDescripcion());
		leccionBean.setActivo(activo);
		return leccionBean;
	}
	public void eliminar() {
		activo = false;
		LeccionDao.getInstance().actualizar(pasarBean());
		
	}
	public void modificar(String descripcion) {
		Descripcion = descripcion;
		LeccionDao.getInstance().actualizar(pasarBean());
	}
	public void activar(String descripcion) {
		activo = true;		
		this.Descripcion = descripcion;
		LeccionDao.getInstance().actualizar(pasarBean());
	}
	
}