package beans;

import javax.persistence.*;

import negocio.Avatar;

@Entity
@Table(name="avatares") 
public class AvatarBean {
	@Id
	@Column(name="avatarId")
	private Integer Id;
	@OneToOne
	@JoinColumn(name="tipoAvatarId")
	private TipoAvatarBean TipoAvatar;
	private int hambre;
	private long ultimaComida;
	
	/*GETTERS Y SETTERS*/
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public TipoAvatarBean getTipoAvatar() {
		return TipoAvatar;
	}
	public void setTipoAvatar(TipoAvatarBean tipoAvatar) {
		TipoAvatar = tipoAvatar;
	}
	public int getHambre() {
		return hambre;
	}
	public void setHambre(int hambre) {
		this.hambre = hambre;
	}
	public long getUltimaComida() {
		return ultimaComida;
	}
	public void setUltimaComida(long ultimaComida) {
		this.ultimaComida = ultimaComida;
	}
	/*NEGOCIO*/
	public Avatar pasarNegocio() {
		Avatar avatar = new Avatar(Id, hambre, ultimaComida);
		avatar.setTipoAvatar(TipoAvatar.pasarNegocio());
		return avatar;
	}


}
