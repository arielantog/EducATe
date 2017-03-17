package negocio;

import java.util.*;

public class Avatar {

	public Avatar() {
		Id = ID++;
		ElementosAvatar = new ArrayList<ElementoAvatar>();
	}

	private static Integer ID = 0;
	private Integer Id;
	private List<ElementoAvatar> ElementosAvatar;

	public Integer agregarElemento(String descripcion, String tipo, String color) {
		ElementoAvatar elementoAvatar= buscarElemento(tipo);
		if (elementoAvatar == null){
			elementoAvatar = new ElementoAvatar(descripcion, tipo, color);
			ElementosAvatar.add(elementoAvatar);
			return elementoAvatar.getId();
		}		
		return 0;
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
}