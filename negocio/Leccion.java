package negocio;

import daos.LeccionDao;
import beans.LeccionBean;

public class Leccion {

	public Leccion(String descripcion) {
		Id = ID++;
		Descripcion = descripcion;
		LeccionDao.getInstance().grabar(pasarBean());
	}
	public Leccion(int id, String descripcion) {
		Id = id;
		Descripcion = descripcion;
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	
	
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
	/*BEAN*/
	public LeccionBean pasarBean() {
		LeccionBean leccionBean = new LeccionBean();
		leccionBean.setId(getId());
		leccionBean.setDescripcion(getDescripcion());
		return leccionBean;
	}
}