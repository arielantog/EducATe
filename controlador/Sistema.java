package controlador;

import hibernate.HibernateUtil;

import java.util.*;

import negocio.Alumno;
import negocio.Curso;
import negocio.Docente;
import negocio.Juego;
import negocio.Leccion;
import negocio.Tema;

public class Sistema {

	public Sistema() {
		new HibernateUtil();
		Alumnos = new HashSet<Alumno>();
		Docentes = new HashSet<Docente>();
		Juegos = new HashSet<Juego>();
		Temas = new HashSet<Tema>();
		setLietner(calcularLietner());
	}

	private static Sistema Singleton;
	private Collection<Alumno> Alumnos;
	private Collection<Docente> Docentes;
	private Collection<Juego> Juegos;
	private Collection<Tema> Temas;
	private Integer[] Lietner;


	public static Sistema getInstance() {
		if (Singleton == null){
			Singleton = new Sistema();
			return Singleton;
		}
		return null;
	}

	public Integer nuevoAlumno(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		Alumno alumno = buscarAlumno(tipoDocumento, nroDocumento);
		if (alumno == null){
			alumno = new Alumno(tipoDocumento, nroDocumento, nombre, apellido);
			Alumnos.add(alumno);
			return alumno.getId();
		}
		return 0;
	}

	public Integer nuevoDocente(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		Docente docente = buscarDocente(tipoDocumento, nroDocumento);
		if (docente == null){
			docente = new Docente(tipoDocumento, nroDocumento, nombre, apellido);
			Docentes.add(docente);
			return docente.getId();
		}
		return 0;
	}

	public Integer nuevoTema(String descripcion) {
		Tema tema = buscarTema(descripcion);
		if (tema == null){
			tema = new Tema(descripcion);
			Temas.add(tema);
			return tema.getId();
		}
		return 0;
	}

	public Integer nuevoJuego(String nombre, Integer tema) {
		Juego juego = buscarJuego(nombre);
		if (juego == null){
			Tema tema2 = buscarTema(tema);
			juego = new Juego(nombre, tema2);
			Juegos.add(juego);
			return juego.getId();
		}
		return 0;
	}

	public Integer docenteAgregarCurso(Integer docente, String descripcion) {
		Docente docente2 = buscarDocente(docente);
		if (docente2 != null)
			return docente2.agregarCurso(descripcion);
		return 0;
	}

	public Integer alumnoAgregarEnsenianza(Integer alumno, Integer leccion, boolean resultado) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null){
			Leccion leccion2 = juegoBuscarLeccion(leccion);
			if (leccion2 != null)
				return alumno2.agregarEnsenianza(leccion2, resultado);
		}
		return 0;
	}

	public Integer temaAgregarLeccion(Integer tema, String descripcion) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null)
			return tema2.agregarLeccion(descripcion);
		return 0;
	}

	public Integer juegoAgregarLeccion(Integer juego, Integer leccion) {
		Juego juego2 = buscarJuego(juego);
		if (juego2 != null){
			Leccion leccion2 = temaBuscarLeccion(leccion);
			if (leccion2 != null)
				return juego2.agregarLeccion(leccion2);
		}
		return 0;
	}

	public Integer cursoAgregarAlumno(Integer docente, Integer curso, Integer alumno) {
		Curso curso2 = docenteBuscarCurso(curso);
		if (curso2 != null){
			Alumno alumno2 = buscarAlumno(alumno);
			if (alumno2 != null)
				return curso2.agregarAlumno(alumno2);
		}
		return 0;
	}

	public Integer avatarAgregarElemento(Integer alumno, String descripcion, String tipo, String color) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null)
			return alumno2.avatarAgregarElemento(descripcion, tipo, color);
		return 0;
	}

	private Tema buscarTema(String descipcion) {
		for (Tema tema: Temas)
			if (tema.getDescripcion().equals(descipcion))
				return tema;
		return null;
	}
	
	private Tema buscarTema(Integer id) {
		for (Tema tema: Temas)
			if (tema.getId() == id)
				return tema;
		return null;
	}

	private Docente buscarDocente(String tipoDocumento, Integer nroDocumento) {
		for (Docente docente: Docentes)
			if (docente.getTipoDocumento().equals(tipoDocumento) && docente.getNroDocumento() == nroDocumento)
				return docente;
		return null;
	}
	
	private Docente buscarDocente(Integer Id) {
		for (Docente docente: Docentes)
			if (docente.getId() == Id)
				return docente;
		return null;
	}

	private Alumno buscarAlumno(String tipoDocumento, Integer nroDocumento) {
		for (Alumno alumno: Alumnos)
			if (alumno.getTipoDocumento().equals(tipoDocumento) && alumno.getNroDocumento() == nroDocumento)
				return alumno;
		return null;
	}
	
	private Alumno buscarAlumno(Integer Id) {
		for (Alumno alumno: Alumnos)
			if (alumno.getId() == Id)
				return alumno;
		return null;
	}
	
	private Leccion temaBuscarLeccion(Integer leccion) {
		for (Tema tema: Temas){
			Leccion leccion2 = tema.buscarLeccion(leccion);
			if (leccion2 != null)
				return leccion2;
		}
		return null;
	}

	private Juego buscarJuego(String nombre) {
		for(Juego juego: Juegos)
			if (juego.getNombre().equals(nombre))
				return juego;
		return null;
	}
	
	private Juego buscarJuego(Integer Id) {
		for (Juego juego: Juegos)
			if (juego.getId() == Id)
				return juego;
		return null;
	}
	
	private Curso docenteBuscarCurso(Integer curso) {
		for (Docente docente: Docentes){
			Curso curso2 = docente.buscarCurso(curso);
			if (curso2 != null)
				return curso2;
		}
			
		return null;
	}
	
	private Leccion juegoBuscarLeccion(Integer leccion) {
		for (Juego juego: Juegos){
			Leccion leccion2 = juego.buscarLeccion(leccion);
			if (leccion2 != null)
				return leccion2;
		}
		return null;
	}
	
	public Integer elegirJuego(Integer alumno){
		Alumno alumno2 = buscarAlumno(alumno);
		if(alumno2!= null){
			int leccion = alumno2.calcularSiguienteLeccion(Lietner);
			for (Juego juego: Juegos)
				for (Leccion leccion2: juego.getLecciones())
					if (leccion2.getId() == leccion)
						return juego.getId();
		}
		return null;
		
	}
	
	public Integer elegirJuego(Integer alumno, Integer tema){
		//TODO elegir juego mediante un alumno y tema
		return null;
		
	}
	
	private Integer[] calcularLietner() {
		Integer[] lietnerValores = cargarLietner();
		Integer[] lietners = new Integer[100];
		int contador = 0;
		@SuppressWarnings("unused")
		int posicion = 0;
		int cantidad = cantidadVector(lietnerValores);
		boolean noPrimero = false;
		//Agrego el 0
		for (int i = 0;i<lietnerValores[0];i++){
			lietners[i] = contador;
			posicion = i;
		}
		contador++;
		posicion++;
		
		//Por cada otro nivel que haya
		for (int i = 1;i<=cantidad;i++){
			//Copio la cadena
			int cantidadAct = cantidadVector(lietners);
			for (int j = 0;j<=cantidadAct;j++)
			{
				lietners[cantidadAct+j+1] = lietners[j];
			}
			cantidadAct = cantidadVector(lietners);
			//Agrego el 0
			if (noPrimero){
				for (int j = 0;j<lietnerValores[0];j++){
					lietners[cantidadAct+1] = 0;
					cantidadAct++;
				}
			}
			noPrimero = true;
			//Agrego el nuevo valor
			for(int j = 0;j<lietnerValores[cantidad];j++){
				lietners[cantidadAct+j+1] = contador;
			}
			contador++;
			
		}
		
		
		return lietners;
	}

	private int cantidadVector(Integer[] lietnerValores) {
		int cantidad = 0;
		int c = 0;
		while (lietnerValores[c]!= null){
			cantidad = c;
			c++;
		}
		return cantidad;
	}

	private Integer[] cargarLietner() {
		Integer[] lietnerValores = new Integer[10];
		lietnerValores[0]= 1;
		lietnerValores[1]= 1;
		lietnerValores[2]= 1;
		lietnerValores[3]= 1;
		lietnerValores[4]= 1;
		// TODO Esto se tiene que cargar desde una tabla de SQL
		return lietnerValores;
	}

	public Integer[] getLietner() {
		return Lietner;
	}

	public void setLietner(Integer[] lietner) {
		Lietner = lietner;
	}
}