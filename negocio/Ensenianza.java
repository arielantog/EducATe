package negocio;

import java.util.Date;

import beans.EnsenianzaBean;

public class Ensenianza {


	public Ensenianza(Leccion leccion) {
		id = ID++;
		this.leccion = leccion;
		nivelRefuerzo = 0;
		fechaUltRepaso = new Fecha().fechaActual();
	}

	public Ensenianza(int id, int nivelRefuerzo, Date fechaUltRepaso2) {
		this.id = id;
		this.nivelRefuerzo = nivelRefuerzo;
		fechaUltRepaso = fechaUltRepaso2;
	}

	private static int ID = 1;
	private static int NivelMax = 5;
	private int id;
	private Leccion leccion;
	private int nivelRefuerzo;
	private Date fechaUltRepaso;
	
	
	public int calcularNivelRefuerzo(boolean resultado) {
		if (resultado){
			if (getNivelRefuerzo() < NivelMax)
				nivelRefuerzo++;
			else if (getNivelRefuerzo() >= NivelMax)
				nivelRefuerzo = NivelMax;
		}else
			nivelRefuerzo = 1;
		return nivelRefuerzo;
	}
	public int actualizarFechaUltRepaso() {
		this.fechaUltRepaso = new Fecha().fechaActual();
		return 0;
	}
	
	/*GETTERS Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public static int getNivelMax() {
		return NivelMax;
	}
	public static void setNivelMax(int nivelMax) {
		NivelMax = nivelMax;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Leccion getLeccion() {
		return leccion;
	}
	public void setLeccion(Leccion leccion) {
		this.leccion = leccion;
	}
	public int getNivelRefuerzo() {
		return nivelRefuerzo;
	}
	public void setNivelRefuerzo(int nivelRefuerzo) {
		this.nivelRefuerzo = nivelRefuerzo;
	}
	public Date getFechaUltRepaso() {
		return fechaUltRepaso;
	}
	public void setFechaUltRepaso(Date fechaUltRepaso) {
		this.fechaUltRepaso = fechaUltRepaso;
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