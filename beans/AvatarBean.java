package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="avatares") 
public class AvatarBean {
	@Id
	@Column(name="avatarId")
	private Integer Id;
	@OneToMany
	@JoinColumn(name="avatarId") 
	private List<ElementoAvatarBean> ElementosAvatar;
	
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

}
