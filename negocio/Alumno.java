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
		activo = true;
		Ensenianzas = new ArrayList<Ensenianza>();
		Avatar = new Avatar();
		AlumnoDao.getInstance().grabar(pasarBean());
	}
	public Alumno(int id,String tipoDocumento, Integer nroDocumento, String nombre, String apellido, int puntos, int nivelLietner, boolean activo) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		Id = id;
		Puntos = puntos;
		NivelLietner = nivelLietner;
		this.activo = activo;
		Ensenianzas = new ArrayList<Ensenianza>();
	}

	private static Integer ID = 1;
	private static final int PuntosCorrecta = 100;
	private static final int PuntosIncorrecta = 20;
	private Integer Id;
	private Integer Puntos;
	private List<Ensenianza> Ensenianzas;
	private Avatar Avatar;
	private int NivelLietner;
	private boolean activo;

	
	public Integer agregarEnsenianza(Leccion leccion, boolean resultado) {
		Ensenianza ensenianza = buscarEnsenianza(leccion);
		if (ensenianza == null){
			ensenianza = new Ensenianza(leccion);
			Ensenianzas.add(ensenianza);
			EnsenianzaDao.getInstance().grabar(ensenianza.pasarBean());
			return ensenianza.getId();
		}else{
			ensenianza.calcularNivelRefuerzo(resultado);
			ensenianza.actualizarFechaUltRepaso();
			EnsenianzaDao.getInstance().actualizar(ensenianza.pasarBean());
			calcularPuntos(resultado);
			AlumnoDao.getInstance().actualizar(pasarBean());
		}
		return 0;
	}
	private void calcularPuntos(boolean resultado) {
		if (resultado)
			Puntos = Puntos + PuntosCorrecta;
		else
			Puntos = Puntos + PuntosIncorrecta;
		
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
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
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
		alumnoBean.setActivo(activo);
		for (Ensenianza ensenianza: Ensenianzas){
			EnsenianzaBean ensenianzaBean = ensenianza.pasarBean();
			alumnoBean.agregarEnsenianza(ensenianzaBean);
		}
		alumnoBean.setAvatar(getAvatar().pasarBean());
		
		return alumnoBean;
	}
	
	public void alimentarAvatar(Alimento alimento) {
		if (Avatar.alimentar(alimento) == 1){
			Puntos = Puntos - alimento.getPrecio();
			AlumnoDao.getInstance().actualizar(pasarBean());
		}
	}
	public int evolucionarAvatar() {
		if (getPuntos() >= Avatar.getTipoAvatar().getPrecioEvolucion()){
			if (Avatar.getHambre() >= 90){
				Puntos = Puntos - Avatar.getTipoAvatar().getPrecioEvolucion();
				Avatar.evolucionar();
				AlumnoDao.getInstance().actualizar(pasarBean());
			}else{
				System.out.println("El avatar no puede evolucionar si tiene hambre.");
			}
		}else{
			System.out.println("Se necesitan más puntos para evolucionar.");
		}
		return 0;
	}
	public int revivirAvatar() {
		if (Avatar.getHambre() == 0){
			if (getPuntos() >= Avatar.getTipoAvatar().getPrecioRevivir()){
				Avatar.revivir();
				Puntos = Puntos - Avatar.getTipoAvatar().getPrecioRevivir();
				AlumnoDao.getInstance().actualizar(pasarBean());
			}else{
				System.out.println("Se necesitan más puntos para revivir.");
			}
		}else {
			System.out.println("El avatar se encuentra vivo.");
		}
		return 0;
	}
	public void eliminar() {
		activo = false;
		AlumnoDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String nombre, String apellido) {
		setNombre(nombre);
		setApellido(apellido);
		activo = true;
		AlumnoDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String nombre, String apellido) {
		setNombre(nombre);
		setApellido(apellido);
		AlumnoDao.getInstance().actualizar(pasarBean());
	}

}