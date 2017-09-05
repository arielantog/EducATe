package negocio;

import daos.LeccionDao;
import dto.LeccionDTO;
import beans.LeccionBean;

public class Leccion {

	public Leccion(String descripcion) {
		id = ID++;
		this.descripcion = descripcion;
		activo = true;
		LeccionDao.getInstance().grabar(pasarBean());
	}
	public Leccion(int id, String descripcion, boolean activo) {
		this.id = id;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	private static int ID = 1;
	private int id;
	private String descripcion;
	private boolean activo;
	
	public void eliminar() {
		activo = false;
		LeccionDao.getInstance().actualizar(pasarBean());
		
	}
	public void modificar(String descripcion) {
		this.descripcion = descripcion;
		LeccionDao.getInstance().actualizar(pasarBean());
	}
	public void activar(String descripcion) {
		activo = true;		
		this.descripcion = descripcion;
		LeccionDao.getInstance().actualizar(pasarBean());
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
	/*DTO*/
	public LeccionDTO pasarDTO() {
		LeccionDTO leccion = new LeccionDTO(id, descripcion, activo);
		return leccion;
	}
	
}