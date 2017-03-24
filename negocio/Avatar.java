package negocio;

import java.util.*;

import daos.AvatarDao;
import beans.AvatarBean;
import beans.ElementoAvatarBean;

public class Avatar {

	public Avatar() {
		Id = ID++;
		ElementosAvatar = new ArrayList<ElementoAvatar>();
		AvatarDao.getInstance().grabar(pasarBean());
	}
	public Avatar(int id) {
		Id = id;
		ElementosAvatar = new ArrayList<ElementoAvatar>();
	}

	private static Integer ID = 1;
	private Integer Id;
	private List<ElementoAvatar> ElementosAvatar;

	public Integer agregarElemento(String descripcion, String tipo, String color) {
		ElementoAvatar elementoAvatar= buscarElemento(tipo);
		if (elementoAvatar == null){
			elementoAvatar = new ElementoAvatar(descripcion, tipo, color);
			ElementosAvatar.add(elementoAvatar);
			AvatarDao.getInstance().actualizar(pasarBean());
			return elementoAvatar.getId();
		}		
		return 0;
	}
	public void agregarElemento(ElementoAvatar elementoAvatar) {
		ElementosAvatar.add(elementoAvatar);
		
	}

	private ElementoAvatar buscarElemento(String tipo) {
		for (ElementoAvatar elementoAvatar: ElementosAvatar)
			if (elementoAvatar.getTipo().equals(tipo))
				return elementoAvatar;
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
	/*BEAN*/
	public AvatarBean pasarBean() {
		AvatarBean avatarBean = new AvatarBean();
		avatarBean.setId(getId());
		for (ElementoAvatar elementoAvatar: ElementosAvatar){
			ElementoAvatarBean elementoAvatarBean = elementoAvatar.pasarBean();
			avatarBean.agregarElementoAvatar(elementoAvatarBean);
		}
		return avatarBean;
	}
}