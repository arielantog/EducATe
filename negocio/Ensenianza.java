package negocio;

public class Ensenianza {


	public Ensenianza(Leccion leccion) {
		Id = ID++;
		Leccion = leccion;
		NivelRefuerzo = 0;
		FechaUltRepaso = fechaActual();
	}

	private static Integer ID = 0;
	private Integer Id;
	private Leccion Leccion;
	private Integer NivelRefuerzo;
	private Integer FechaUltRepaso;
	
	
	public Integer calcularNivelRefuerzo(boolean resultado) {
		if (resultado)
			NivelRefuerzo++;
		else
			NivelRefuerzo = 1;
		return NivelRefuerzo;
	}
	
	private Integer fechaActual() {
		// TODO Auto-generated method stub
		return null;
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
	public Leccion getLeccion() {
		return Leccion;
	}
	public void setLeccion(Leccion leccion) {
		Leccion = leccion;
	}
	public Integer getNivelRefuerzo() {
		return NivelRefuerzo;
	}
	public void setNivelRefuerzo(Integer nivelRefuerzo) {
		NivelRefuerzo = nivelRefuerzo;
	}
	public Integer getFechaUltRepaso() {
		return FechaUltRepaso;
	}
	public void setFechaUltRepaso(Integer fechaUltRepaso) {
		FechaUltRepaso = fechaUltRepaso;
	}
}