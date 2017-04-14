package negocio;

import java.util.*;

import daos.AlumnoDao;
import daos.EnsenianzaDao;
import beans.AlumnoBean;
import beans.EnsenianzaBean;


public class Alumno extends Persona {

	public Alumno(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		Id = ID++;
		Puntos = 0;
		NivelLietner = 0;
		Ensenianzas = new ArrayList<Ensenianza>();
		Avatar = new Avatar();
		AlumnoDao.getInstance().grabar(pasarBean());
	}
	public Alumno(int id,String tipoDocumento, Integer nroDocumento, String nombre, String apellido, int puntos, int nivelLietner) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		Id = id;
		Puntos = puntos;
		NivelLietner = nivelLietner;
		Ensenianzas = new ArrayList<Ensenianza>();
	}

	private static Integer ID = 1;
	private Integer Id;
	private Integer Puntos;
	private List<Ensenianza> Ensenianzas;
	private Avatar Avatar;
	private int NivelLietner;

	public Integer agregarEnsenianza(Leccion leccion, boolean resultado) {
		Ensenianza ensenianza = buscarEnsenianza(leccion);
		if (ensenianza == null){
			ensenianza = new Ensenianza(leccion);
			Ensenianzas.add(ensenianza);
			EnsenianzaDao.getInstance().grabar(ensenianza.pasarBean());
		}else{
			ensenianzaCalcularNivelRefuerzo(ensenianza, resultado);
		}
		return null;
	}
	public void agregarEnsenianza(Ensenianza ensenianza) {
		Ensenianzas.add(ensenianza);
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
	/*BEAN*/
	public AlumnoBean pasarBean() {
		AlumnoBean alumnoBean = new AlumnoBean();
		alumnoBean.setId(getId());
		alumnoBean.setTipoDocumento(getTipoDocumento());
		alumnoBean.setNroDocumento(getNroDocumento());
		alumnoBean.setNombre(getNombre());
		alumnoBean.setApellido(getApellido());
		alumnoBean.setPuntos(getPuntos());
		alumnoBean.setNivelLietner(getNivelLietner());
		for (Ensenianza ensenianza: Ensenianzas){
			EnsenianzaBean ensenianzaBean = ensenianza.pasarBean();
			alumnoBean.agregarEnsenianza(ensenianzaBean);
		}
		alumnoBean.setAvatar(getAvatar().pasarBean());
		
		return alumnoBean;
	}

}