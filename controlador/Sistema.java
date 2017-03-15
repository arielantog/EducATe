package controlador;

import java.util.*;

import negocio.Alumno;
import negocio.Curso;
import negocio.Docente;
import negocio.Juego;
import negocio.Leccion;
import negocio.Tema;

public class Sistema {

	public Sistema() {
		Alumnos = new HashSet<Alumno>();
		Docentes = new HashSet<Docente>();
		Juegos = new HashSet<Juego>();
		Temas = new HashSet<Tema>();
	}

	private static Sistema Singleton;
	private Collection<Alumno> Alumnos;
	private Collection<Docente> Docentes;
	private Collection<Juego> Juegos;
	private Collection<Tema> Temas;


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
	
}