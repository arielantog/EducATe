package negocio;

public class Leccion {

	public Leccion(String descripcion) {
		Id = ID++;
		Descripcion = descripcion;
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	
	
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

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
}