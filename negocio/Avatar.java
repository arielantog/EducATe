package negocio;

import daos.AvatarDao;
import daos.TipoAvatarDao;
import beans.AvatarBean;

public class Avatar {

	public Avatar() {
		Id = ID++;
		tipoAvatar = buscarTipoAvatar(1);
		hambre = 100;
		ultimaComida = new Fecha().fechaActual();
		AvatarDao.getInstance().grabar(pasarBean());
	}
	public Avatar(int id, int hambre, long ultimaComida) {
		Id = id;
		this.hambre = hambre;
		this.ultimaComida = ultimaComida;
	}
	
	private static Integer ID = 1;
	private static int revivir = 10;
	private Integer Id;
	private int hambre;
	private long ultimaComida;
	private TipoAvatar tipoAvatar;

	private TipoAvatar buscarTipoAvatar(int id) {
		TipoAvatar tipoAvatar = TipoAvatarDao.getInstance().buscar(id);
		if (tipoAvatar == null)
			//TODO Primer organismo si no existiese.
			tipoAvatar = new TipoAvatar("Célula", 10,1,100,1000,500);
		return tipoAvatar;
	}
	public int alimentar(Alimento alimento) {
		if (hambre != 0){
			if (tipoAvatar.tengoAlimento(alimento)){
				this.hambre = this.hambre + alimento.getProteinas();
				if (this.hambre > 100)
					this.hambre = 100;
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
			this.hambre = getRevivir();
			this.ultimaComida = new Fecha().fechaActual();
			AvatarDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El avatar ya se encuentra vivo.");
		}
		return 0;
	}
	public void evolucionar() {
		if (hambre >= 90){
			this.hambre = 100;
			this.ultimaComida = new Fecha().fechaActual();
			tipoAvatar = buscarTipoAvatar(tipoAvatar.getId()+1);
			AvatarDao.getInstance().actualizar(pasarBean());
		}
		
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
	public long getUltimaComida() {
		return ultimaComida;
	}
	public void setUltimaComida(long ultimaComida) {
		this.ultimaComida = ultimaComida;
	}
	public static int getRevivir() {
		return revivir;
	}
	public static void setRevivir(int revivir) {
		Avatar.revivir = revivir;
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