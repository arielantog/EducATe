package negocio;

import java.util.*;


public class Alumno extends Persona {

	public Alumno(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		super.setId(super.getID()); 
		super.setTipoDocumento(tipoDocumento);
		super.setNroDocumento(nroDocumento);
		super.setNombre(nombre);
		super.setApellido(apellido);
		Puntos = 0;
		Ensenianzas = new HashSet<Ensenianza>();
		Avatar = new Avatar();
	}

	private Integer Puntos;
	private Collection<Ensenianza> Ensenianzas;
	private Avatar Avatar;

	public Integer agregarEnsenianza(Leccion leccion, boolean resultado) {
		Ensenianza ensenianza = buscarEnsenianza(leccion);
		if (ensenianza == null){
			ensenianza = new Ensenianza(leccion);
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
	

	/*GETTERS Y SETTERS*/
	
	public Integer getPuntos() {
		return Puntos;
	}
	public void setPuntos(Integer puntos) {
		Puntos = puntos;
	}
	public Collection<Ensenianza> getEnsenianzas() {
		return Ensenianzas;
	}
	public void setEnsenianzas(Collection<Ensenianza> ensenianzas) {
		Ensenianzas = ensenianzas;
	}
	public Avatar getAvatar() {
		return Avatar;
	}
	public void setAvatar(Avatar avatar) {
		Avatar = avatar;
	}
	public Integer getId() {
		return getId();
	}
	public void setId(Integer id) {
		setId(id);
	}
	public String getTipoDocumento() {
		return getTipoDocumento();
	}
	public void setTipoDocumento(String tipoDocumento) {
		setTipoDocumento(tipoDocumento);
	}
	public Integer getNroDocumento() {
		return getNroDocumento();
	}
	public void setNroDocumento(Integer nroDocumento) {
		setNroDocumento(nroDocumento);
	}
	public String getNombre() {
		return getNombre();
	}
	public void setNombre(String nombre) {
		setNombre(nombre);
	}
	public String getApellido() {
		return getApellido();
	}
	public void setApellido(String apellido) {
		setApellido(apellido);
	}
}