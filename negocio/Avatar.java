package negocio;

import java.rmi.RemoteException;
import java.util.Date;

import daos.AvatarDao;
import daos.TipoAvatarDao;
import dto.AvatarDTO;
import beans.AvatarBean;

public class Avatar {

	public Avatar() {
		id = ID++;
		tipoAvatar = buscarTipoAvatar(1);
		hambre = tipoAvatar.getAlimentoMax();
		ultimaComida = new Fecha().fechaActual();
		AvatarDao.getInstance().grabar(pasarBean());
	}
	public Avatar(int id, int hambre, Date ultimaComida) {
		this.id = id;
		this.hambre = hambre;
		this.ultimaComida = ultimaComida;
	}
	
	private static int ID = 1;
	private int id;
	private int hambre;
	private Date ultimaComida;
	private TipoAvatar tipoAvatar;

	private TipoAvatar buscarTipoAvatar(int id) {
		TipoAvatar tipoAvatar = TipoAvatarDao.getInstance().buscar(id);
		if (tipoAvatar == null){
			tipoAvatar = TipoAvatarDao.getInstance().buscar(1);
			if (tipoAvatar == null)
				//XXX Primer organismo si no existiese.
				return tipoAvatar = new TipoAvatar("Célula", 10,100,1000,500, null);
			return null;
		}
		return tipoAvatar;
	}
	public int alimentar(Alimento alimento) throws RemoteException {
		if (hambre != 0){
			if (hambre < tipoAvatar.getAlimentoMax())
				if (tipoAvatar.tengoAlimento(alimento)){
					this.hambre = this.hambre + alimento.getProteinas();
					if (this.hambre > tipoAvatar.getAlimentoMax())
						this.hambre = tipoAvatar.getAlimentoMax();
					this.ultimaComida = new Fecha().fechaActual();
					AvatarDao.getInstance().actualizar(pasarBean());
					return 1;
				}else{
					System.out.println("El avatar no come ese tipo de alimento.");
					throw new RemoteException("El avatar no come ese tipo de alimento.");
				}
			else{
				System.out.println("El avatar no tiene hambre.");
				throw new RemoteException("El avatar no tiene hambre.");
			}
		}else{
			System.out.println("El avatar ha muerto de hambre, comprar revivir.");
			throw new RemoteException("El avatar ha muerto de hambre, debes revivirlo.");
		}
	}
	
	public int revivir() {
		this.hambre = (int) ((float)tipoAvatar.getAlimentoMax() * 20/100);
		this.ultimaComida = new Fecha().fechaActual();
		AvatarDao.getInstance().actualizar(pasarBean());
		
		return 0;
	}
	public int evolucionar() {
		TipoAvatar tipoAvatar2 = buscarTipoAvatar(tipoAvatar.getId()+1);
		while (tipoAvatar2 == null || !tipoAvatar2.isActivo()){
			if (tipoAvatar2 == null){
				System.out.println("No existen más tipos de avatares.");
				return 0;
			}
			tipoAvatar2 = buscarTipoAvatar(tipoAvatar2.getId()+1);
		}
		tipoAvatar = tipoAvatar2;
		this.ultimaComida = new Fecha().fechaActual();
		this.hambre = (int) (this.getTipoAvatar().getAlimentoMax()*0.6);
		AvatarDao.getInstance().actualizar(pasarBean());
		return 1;
	}
	public int descontarHambre() {
		Date fechaActual = new Fecha().fechaActual();
		if (fechaActual.getTime() > ultimaComida.getTime() + tipoAvatar.getTiempoHambre()){
			hambre = hambre - 1;
			if (hambre < 0)
				hambre = 0;
			ultimaComida = fechaActual;
			AvatarDao.getInstance().actualizar(pasarBean());
		}
		return 0;
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
	public int getHambre() {
		return hambre;
	}

	public void setHambre(int hambre) {
		this.hambre = hambre;
	}
	public TipoAvatar getTipoAvatar() {
		return tipoAvatar;
	}
	public void setTipoAvatar(TipoAvatar tipoAvatar) {
		this.tipoAvatar = tipoAvatar;
	}
	public Date getUltimaComida() {
		return ultimaComida;
	}
	public void setUltimaComida(Date ultimaComida) {
		this.ultimaComida = ultimaComida;
	}
	/*BEAN*/
	public AvatarBean pasarBean() {
		AvatarBean avatarBean = new AvatarBean();
		avatarBean.setId(getId());
		avatarBean.setTipoAvatar(tipoAvatar.pasarBean());
		avatarBean.setHambre(hambre);
		avatarBean.setUltimaComida(ultimaComida);
		return avatarBean;
	}
	/*DTO*/
	public AvatarDTO pasarDTO() {
		AvatarDTO avatar = new AvatarDTO(id, hambre, ultimaComida);
		avatar.setTipoAvatarDTO(tipoAvatar.pasarDTO());
		return avatar;
	}
}