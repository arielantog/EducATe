package negocio;

import java.util.Date;

import beans.EnsenianzaBean;

public class Ensenianza {


	public Ensenianza(Leccion leccion) {
		Id = ID++;
		Leccion = leccion;
		NivelRefuerzo = 0;
		FechaUltRepaso = new Fecha().fechaActual();
	}

	public Ensenianza(int id, int nivelRefuerzo, Date fechaUltRepaso2) {
		Id = id;
		NivelRefuerzo = nivelRefuerzo;
		FechaUltRepaso = fechaUltRepaso2;
	}

	private static Integer ID = 1;
	private static Integer NivelMax = 5;
	private Integer Id;
	private Leccion Leccion;
	private Integer NivelRefuerzo;
	private Date FechaUltRepaso;
	
	
	public Integer calcularNivelRefuerzo(boolean resultado) {
		if (resultado)
			if (getNivelRefuerzo() < NivelMax)
				NivelRefuerzo++;
			else if (getNivelRefuerzo() >= NivelMax)
				NivelRefuerzo = NivelMax;
		else
			NivelRefuerzo = 1;
		return NivelRefuerzo;
	}
	
	/*GETTERS Y SETTERS*/
	public static Integer getID() {
		return ID;
	}
	public static void setID(Integer iD) {
		ID = iD;
	}
	public static Integer getNivelMax() {
		return NivelMax;
	}
	public static void setNivelMax(Integer nivelMax) {
		NivelMax = nivelMax;
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
	public Date getFechaUltRepaso() {
		return FechaUltRepaso;
	}
	public void setFechaUltRepaso(Date fechaUltRepaso) {
		FechaUltRepaso = fechaUltRepaso;
	}
	/*BEAN*/
	public EnsenianzaBean pasarBean() {
		EnsenianzaBean ensenianzaBean = new EnsenianzaBean();
		ensenianzaBean.setId(getId());
		ensenianzaBean.setLeccion(getLeccion().pasarBean());
		ensenianzaBean.setNivelRefuerzo(getNivelRefuerzo());
		ensenianzaBean.setFechaUltRepaso(getFechaUltRepaso());
		return ensenianzaBean;
	}

	public void actualizarFechaUltRepaso() {
		this.FechaUltRepaso = new Fecha().fechaActual();
		
	}

}