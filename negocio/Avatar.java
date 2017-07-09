package negocio;

import java.util.Date;

import daos.AvatarDao;
import daos.TipoAvatarDao;
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
		if (tipoAvatar == null)
			//Primer organismo si no existiese.
			tipoAvatar = new TipoAvatar("Célula", 10,100,1000,500);
		return tipoAvatar;
	}
	public int alimentar(Alimento alimento) {
		if (hambre != 0){
			if (tipoAvatar.tengoAlimento(alimento)){
				this.hambre = this.hambre + alimento.getProteinas();
				if (this.hambre > tipoAvatar.getAlimentoMax())
					this.hambre = tipoAvatar.getAlimentoMax();
				this.ultimaComida = new Fecha().fechaActual();
				AvatarDao.getInstance().actualizar(pasarBean());
				return 1;
			}else{
				System.out.println("El avatar no come ese tipo de alimento.");
			}
				
		}else{
			System.out.println("El avatar ha muerto de hambre, comprar revivir.");
		}
		return 0;
	}
	
	public int revivir() {
		if (hambre == 0){
			this.hambre = tipoAvatar.getAlimentoMax() * 20/100;
			this.ultimaComida = new Fecha().fechaActual();
			AvatarDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El avatar ya se encuentra vivo.");
		}
		return 0;
	}
	public int evolucionar() {
		if (hambre >= 90){
			this.hambre = 100;
			this.ultimaComida = new Fecha().fechaActual();
			tipoAvatar = buscarTipoAvatar(tipoAvatar.getId()+1);
			AvatarDao.getInstance().actualizar(pasarBean());
		}
		return 0;
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
}