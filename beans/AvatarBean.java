package beans;

import java.util.Date;

import javax.persistence.*;

import negocio.Avatar;

@Entity
@Table(name="avatares") 
public class AvatarBean {
	@Id
	@Column(name="avatarId")
	private int id;
	@OneToOne
	@JoinColumn(name="tipoAvatarId")
	private TipoAvatarBean tipoAvatar;
	private int hambre;
	private Date ultimaComida;
	
	/*GETTERS Y SETTERS*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoAvatarBean getTipoAvatar() {
		return tipoAvatar;
	}
	public void setTipoAvatar(TipoAvatarBean tipoAvatar) {
		this.tipoAvatar = tipoAvatar;
	}
	public int getHambre() {
		return hambre;
	}
	public void setHambre(int hambre) {
		this.hambre = hambre;
	}
	public Date getUltimaComida() {
		return ultimaComida;
	}
	public void setUltimaComida(Date ultimaComida2) {
		this.ultimaComida = ultimaComida2;
	}
	/*NEGOCIO*/
	public Avatar pasarNegocio() {
		Avatar avatar = new Avatar(id, hambre, ultimaComida);
		avatar.setTipoAvatar(tipoAvatar.pasarNegocio());
		return avatar;
	}


}
