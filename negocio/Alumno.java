package negocio;

import java.util.*;

import daos.AlumnoDao;
import daos.EnsenianzaDao;
import beans.AlumnoBean;
import beans.EnsenianzaBean;


public class Alumno extends Persona {

	public Alumno(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password, String mail, String usuario) {
		super(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
		id = ID++;
		this.usuario = usuario;
		puntos = 0;
		nivelEnsenianza = 0;
		activo = true;
		ensenianzas = new ArrayList<Ensenianza>();
		avatar = new Avatar();
		nivelEnsenianza = 0;
		AlumnoDao.getInstance().grabar(pasarBean());
	}
	public Alumno(int id,String tipoDocumento, int nroDocumento, String nombre, String apellido, String usuario, String password, String mail, int puntos, boolean activo) {
		super(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
		this.id = id;
		this.usuario = usuario;
		this.puntos = puntos;
		this.activo = activo;
		ensenianzas = new ArrayList<Ensenianza>();
		this.nivelEnsenianza = 0;
	}

	private static int ID = 1;
	private static final int PuntosRespuestaCorrecta = 100;
	private static final int PuntosRespuestaIncorrecta = 20;
	private int id;
	private int puntos;
	private List<Ensenianza> ensenianzas;
	private Avatar avatar;
	private boolean activo;
	private String usuario;
	private int nivelEnsenianza;
	
	public int agregarEnsenianza(Leccion leccion, boolean resultado) {
		Ensenianza ensenianza = buscarEnsenianza(leccion);
		if (ensenianza == null){
			ensenianza = new Ensenianza(leccion);
			ensenianzas.add(ensenianza);
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
	private int calcularPuntos(boolean resultado) {
		if (resultado)
			puntos = puntos + PuntosRespuestaCorrecta;
		else
			puntos = puntos + PuntosRespuestaIncorrecta;
		return puntos;
	}
	public void agregarEnsenianza(Ensenianza ensenianza) {
		ensenianzas.add(ensenianza);
	}

	private Ensenianza buscarEnsenianza(Leccion leccion) {
		for (Ensenianza ensenianza: ensenianzas)
			if (ensenianza.getLeccion().getId() == leccion.getId())
				return ensenianza;
		return null;
	}
	
	private int calcularNivelSiguiente(Lietner lietner){
		int nivel = lietner.getNivel();
		return nivel;
	}
	public Leccion calcularSiguienteLeccion(Lietner lietner){
		List<Leccion> lecciones = new ArrayList<Leccion>();
		int nivel = calcularNivelSiguiente(lietner);
		for (Ensenianza ensenianza: ensenianzas){
			if (ensenianza.getNivelRefuerzo() == nivel){
				
				lecciones.add(ensenianza.getLeccion());
			}
		}
		if (lecciones.size() > 0){
			lietner.setIteracion(0);
			setNivelEnsenianza(nivel);
			int aleatorio = new Random(System.currentTimeMillis()).nextInt(lecciones.size());
			return lecciones.get(aleatorio);
		}
		
		/*Se utiliza para que no genere siempre números random del 0 al 99.
		 * A medida que se avanza y sólo quedan niveles de valor 4 o 5,
		 * al hacer muchas iteraciones, pincha.
		 * Por lo que en cada iteración, se acorta la brecha del random
		 * así los mayores niveles tienen una mayor probabilidad.
		 */
		lietner.setIteracion(lietner.getIteracion()+1);
		return calcularSiguienteLeccion(lietner);
	}
	public int alimentarAvatar(Alimento alimento) {
		if (avatar.alimentar(alimento) == 1){
			puntos = puntos - alimento.getPrecio();
			AlumnoDao.getInstance().actualizar(pasarBean());
		}
		return 0;
	}
	public int evolucionarAvatar() {
		if (getPuntos() >= avatar.getTipoAvatar().getPrecioEvolucion()){
			if (avatar.getHambre() >= 90){
				puntos = puntos - avatar.getTipoAvatar().getPrecioEvolucion();
				avatar.evolucionar();
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
		if (avatar.getHambre() == 0){
			if (getPuntos() >= avatar.getTipoAvatar().getPrecioRevivir()){
				avatar.revivir();
				puntos = puntos - avatar.getTipoAvatar().getPrecioRevivir();
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
	public void avatarDescontarHambre() {
		avatar.descontarHambre();	
	}

	/*GETTERS Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public List<Ensenianza> getEnsenianzas() {
		return ensenianzas;
	}
	public void setEnsenianzas(List<Ensenianza> ensenianzas) {
		this.ensenianzas = ensenianzas;
	}
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getNivelEnsenianza() {
		return nivelEnsenianza;
	}
	public void setNivelEnsenianza(int nivelEnsenianza) {
		this.nivelEnsenianza = nivelEnsenianza;
	}

	/*BEAN*/
	public AlumnoBean pasarBean() {
		AlumnoBean alumnoBean = new AlumnoBean();
		alumnoBean.setId(getId());
		alumnoBean.setTipoDocumento(getTipoDocumento());
		alumnoBean.setNroDocumento(getNroDocumento());
		alumnoBean.setNombre(getNombre());
		alumnoBean.setApellido(getApellido());
		alumnoBean.setPassword(getPassword());
		alumnoBean.setMail(getMail());
		alumnoBean.setUsuario(getUsuario());
		alumnoBean.setPuntos(getPuntos());
		//alumnoBean.setNivelLietner(getNivelLietner());
		alumnoBean.setActivo(activo);
		for (Ensenianza ensenianza: ensenianzas){
			EnsenianzaBean ensenianzaBean = ensenianza.pasarBean();
			alumnoBean.agregarEnsenianza(ensenianzaBean);
		}
		alumnoBean.setAvatar(getAvatar().pasarBean());
		
		return alumnoBean;
	}

}