package negocio;

import java.util.*;


public class Alumno extends Persona {

	public Alumno(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		super.setId(super.getID());
		Puntos = 0;
		NivelLietner = 0;
		Ensenianzas = new ArrayList<Ensenianza>();
		Avatar = new Avatar();
	}

	private Integer Puntos;
	private List<Ensenianza> Ensenianzas;
	private Avatar Avatar;
	private int NivelLietner;

	public Integer agregarEnsenianza(Leccion leccion, boolean resultado) {
		Ensenianza ensenianza = buscarEnsenianza(leccion);
		if (ensenianza == null){
			ensenianza = new Ensenianza(leccion);
			Ensenianzas.add(ensenianza);
		}else{
			ensenianzaCalcularNivelRefuerzo(ensenianza, resultado);
		}
		return null;
	}


	public Integer avatarAgregarElemento(String descripcion, String tipo, String color) {
		return Avatar.agregarElemento(descripcion, tipo, color);
	}

	private Ensenianza buscarEnsenianza(Leccion leccion) {
		for (Ensenianza ensenianza: Ensenianzas)
			if (ensenianza.getLeccion().getId() == leccion.getId())
				return ensenianza;
		return null;
	}

	private Integer ensenianzaCalcularNivelRefuerzo(Ensenianza ensenianza, boolean resultado){
		return ensenianza.calcularNivelRefuerzo(resultado);
	}
	
	private Integer calcularNivelSiguiente(Integer[] lietner){
		this.setNivelLietner(getNivelLietner()+1);
		Integer nuevoNivel = lietner[this.getNivelLietner()];
		if (nuevoNivel == null){
			this.setNivelLietner(0);
			return 0;
		}
		return nuevoNivel;
	}
	public Integer calcularSiguienteLeccion(Integer[] lietner){
		Integer nivel = calcularNivelSiguiente(lietner);
		for (Ensenianza ensenianza: Ensenianzas){
			if (ensenianza.getNivelRefuerzo() == nivel)
				return ensenianza.getLeccion().getId();
		}
		return calcularSiguienteLeccion(lietner);
	}
	

	/*GETTERS Y SETTERS*/
	
	public Integer getPuntos() {
		return Puntos;
	}
	public void setPuntos(Integer puntos) {
		Puntos = puntos;
	}
	public List<Ensenianza> getEnsenianzas() {
		return Ensenianzas;
	}
	public void setEnsenianzas(List<Ensenianza> ensenianzas) {
		Ensenianzas = ensenianzas;
	}
	public Avatar getAvatar() {
		return Avatar;
	}
	public void setAvatar(Avatar avatar) {
		Avatar = avatar;
	}
	public int getNivelLietner() {
		return NivelLietner;
	}
	public void setNivelLietner(int nivelLietner) {
		NivelLietner = nivelLietner;
	}
}