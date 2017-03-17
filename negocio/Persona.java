package negocio;

public abstract class Persona {


	public Persona(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		Id = ID++;
		TipoDocumento = tipoDocumento;
		NroDocumento = nroDocumento;
		Nombre = nombre;
		Apellido = apellido;
	}

	protected static Integer ID = 0;
	private Integer Id;
	private String TipoDocumento;
	private Integer NroDocumento;
	private String Nombre;
	private String Apellido;
	
	
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