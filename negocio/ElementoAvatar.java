package negocio;

import daos.ElementoAvatarDao;
import beans.ElementoAvatarBean;

public class ElementoAvatar {

	public ElementoAvatar(String descripcion, String tipo, String color) {
		Id = ID++;
		Descripcion = descripcion;
		Tipo = tipo;
		Color = color;
		ElementoAvatarDao.getInstance().grabar(pasarBean());
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	private String Tipo;
	private String Color;
	
	
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
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	/*BEAN*/
	public ElementoAvatarBean pasarBean() {
		ElementoAvatarBean elementoAvatarBean = new ElementoAvatarBean();
		elementoAvatarBean.setId(getId());
		elementoAvatarBean.setDescripcion(getDescripcion());
		elementoAvatarBean.setTipo(getTipo());
		elementoAvatarBean.setColor(getColor());
		return elementoAvatarBean;
	}
}