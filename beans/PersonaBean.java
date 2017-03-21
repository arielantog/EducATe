package beans;

import javax.persistence.*;

@MappedSuperclass
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonaBean {
	@Id
	private Integer Id;
	private String TipoDocumento;
	private Integer NroDocumento;
	private String Nombre;
	private String Apellido;
	
	/*GETTERS AND SETTERS*/
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getTipoDocumento() {
		return TipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}
	public Integer getNroDocumento() {
		return NroDocumento;
	}
	public void setNroDocumento(Integer nroDocumento) {
		NroDocumento = nroDocumento;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	
}
