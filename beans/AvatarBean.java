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
	/*NEGOCIO*/
	public Avatar pasarNegocio() {
		Avatar avatar = new Avatar(Id);
		avatar.setTipoAvatar(TipoAvatar.pasarNegocio());
		return avatar;
	}


}
