package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import negocio.Avatar;

@Entity
@Table(name="avatares") 
public class AvatarBean {
	@Id
	@Column(name="avatarId")
	private Integer Id;
	@OneToMany
	@JoinColumn(name="avatarId") 
	private List<ElementoAvatarBean> ElementosAvatar;
	
	public AvatarBean() {
		ElementosAvatar = new ArrayList<ElementoAvatarBean>();
	}
	/*GETTERS Y SETTERS*/
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public List<ElementoAvatarBean> getElementosAvatar() {
		return ElementosAvatar;
	}
	public void setElementosAvatar(List<ElementoAvatarBean> elementosAvatar) {
		ElementosAvatar = elementosAvatar;
	}
	public void agregarElementoAvatar(ElementoAvatarBean elementoAvatarBean) {
		ElementosAvatar.add(elementoAvatarBean);
	}
	public Avatar pasarNegocio() {
		Avatar avatar = new Avatar(Id);
		for (ElementoAvatarBean elementoAvatarBean: ElementosAvatar){
			avatar.agregarElemento(elementoAvatarBean.pasarNegocio());
		}
		return avatar;
	}


}
