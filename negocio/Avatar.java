package negocio;

import daos.AvatarDao;
import daos.TipoAvatarDao;
import beans.AvatarBean;

public class Avatar {

	public Avatar() {
		Id = ID++;
		tipoAvatar = buscarTipoAvatar(1);
		AvatarDao.getInstance().grabar(pasarBean());
	}
	
	private TipoAvatar buscarTipoAvatar(int id) {
		TipoAvatar tipoAvatar = TipoAvatarDao.getInstance().buscar(id);
		if (tipoAvatar == null)
			//TODO Primer organismo si no existiese.
			tipoAvatar = new TipoAvatar("Célula", 10,1,100);
		return tipoAvatar;
	}
	public Avatar(int id) {
		Id = id;

	}

	private static Integer ID = 1;
	private Integer Id;
	private TipoAvatar tipoAvatar;

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
	public TipoAvatar getTipoAvatar() {
		return tipoAvatar;
	}
	public void setTipoAvatar(TipoAvatar tipoAvatar) {
		this.tipoAvatar = tipoAvatar;
	}
	/*BEAN*/
	public AvatarBean pasarBean() {
		AvatarBean avatarBean = new AvatarBean();
		avatarBean.setId(getId());
		avatarBean.setTipoAvatar(tipoAvatar.pasarBean());
		return avatarBean;
	}
}