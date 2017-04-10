package negocio;

import java.util.Calendar;
import java.util.GregorianCalendar;

import beans.EnsenianzaBean;

public class Ensenianza {


	public Ensenianza(Leccion leccion) {
		Id = ID++;
		Leccion = leccion;
		NivelRefuerzo = 0;
		FechaUltRepaso = fechaActual();
	}

	public Ensenianza(int id, int nivelRefuerzo, int fechaUltRepaso) {
		Id = id;
		NivelRefuerzo = 0;
		FechaUltRepaso = fechaActual();
	}

	private static Integer ID = 1;
	private static Integer NivelMax = 5;
	private Integer Id;
	private Leccion Leccion;
	private Integer NivelRefuerzo;
	private Integer FechaUltRepaso;
	
	
	public Integer calcularNivelRefuerzo(boolean resultado) {
		if (resultado)
			if (getNivelRefuerzo() < NivelMax)
				NivelRefuerzo++;
			else if (getNivelRefuerzo() < NivelMax)
				NivelRefuerzo = NivelMax;
		else
			NivelRefuerzo = 1;
		return NivelRefuerzo;
	}
	
	private Integer fechaActual() {
		Calendar fecha = new GregorianCalendar();
		Integer fecha2 = fecha.get(Calendar.YEAR) * 10000 +
						 fecha.get(Calendar.MONTH) * 100 + 
						 fecha.get(Calendar.DAY_OF_MONTH);
		return fecha2;
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
	public Integer getFechaUltRepaso() {
		return FechaUltRepaso;
	}
	public void setFechaUltRepaso(Integer fechaUltRepaso) {
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
}