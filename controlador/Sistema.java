package controlador;

import hibernate.HibernateUtil;

import java.util.*;

import daos.AlumnoDao;
import daos.AvatarDao;
import daos.CursoDao;
import daos.DocenteDao;
import daos.ElementoAvatarDao;
import daos.JuegoDao;
import daos.LeccionDao;
import daos.TemaDao;
import negocio.Alumno;
import negocio.Curso;
import negocio.Docente;
import negocio.Juego;
import negocio.Leccion;
import negocio.Tema;

public class Sistema {

	public Sistema() {
		new HibernateUtil();
		Alumnos = new ArrayList<Alumno>();
		Docentes = new ArrayList<Docente>();
		Juegos = new ArrayList<Juego>();
		Temas = new ArrayList<Tema>();
		setLietner(calcularLietner());
		cargarVariablesGlobales();
	}

	private void cargarVariablesGlobales() {
		AlumnoDao.getInstance().cargarVariableGlobal();
		AvatarDao.getInstance().cargarVariableGlobal();
		CursoDao.getInstance().cargarVariableGlobal();
		DocenteDao.getInstance().cargarVariableGlobal();
		ElementoAvatarDao.getInstance().cargarVariableGlobal();
		JuegoDao.getInstance().cargarVariableGlobal();
		LeccionDao.getInstance().cargarVariableGlobal();
		TemaDao.getInstance().cargarVariableGlobal();
	}

	private static Sistema Singleton;
	private List<Alumno> Alumnos;
	private List<Docente> Docentes;
	private List<Juego> Juegos;
	private List<Tema> Temas;
	private Integer[] Lietner;


	public static Sistema getInstance() {
		if (Singleton == null){
			Singleton = new Sistema();
			return Singleton;
		}
		return null;
	}

	public int nuevoAlumno(String tipoDocumento, int nroDocumento, String nombre, String apellido) {
		Alumno alumno = buscarAlumno(tipoDocumento, nroDocumento);
		if (alumno == null){
			alumno = new Alumno(tipoDocumento, nroDocumento, nombre, apellido);
			for (Tema tema: Temas)
				for (Leccion leccion: tema.getLecciones())
					alumno.agregarEnsenianza(leccion, false);
			Alumnos.add(alumno);
			return alumno.getId();
		}
		System.out.println("El alumno ya existe");
		return alumno.getId();
	}

	public int nuevoDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido) {
		Docente docente = buscarDocente(tipoDocumento, nroDocumento);
		if (docente == null){
			docente = new Docente(tipoDocumento, nroDocumento, nombre, apellido);
			Docentes.add(docente);
			return docente.getId();
		}
		System.out.println("El docente ya existe");
		return docente.getId();
	}

	public int nuevoTema(String descripcion) {
		Tema tema = buscarTema(descripcion);
		if (tema == null){
			tema = new Tema(descripcion);
			Temas.add(tema);
			return tema.getId();
		}
		System.out.println("El tema ya existe");
		return tema.getId();
	}

	public int nuevoJuego(String nombre, int tema) {
		Juego juego = buscarJuego(nombre);
		if (juego == null){
			Tema tema2 = buscarTema(tema);
			juego = new Juego(nombre, tema2);
			Juegos.add(juego);
			return juego.getId();
		}
		System.out.println("El juego ya existe");
		return juego.getId();
	}

	public int docenteAgregarCurso(int docente, String descripcion) {
		Docente docente2 = buscarDocente(docente);
		if (docente2 != null)
			return docente2.agregarCurso(descripcion);
		System.out.println("El docente no existe");
		return 0;
	}
	
	public int temaAgregarLeccion(int tema, String descripcion) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null)
			return tema2.agregarLeccion(descripcion);
		System.out.println("El tema no existe");
		return 0;
	}

	public int alumnoAgregarEnsenianza(int alumno, int leccion, boolean resultado) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null){
			Leccion leccion2 = juegoBuscarLeccion(leccion);
			if (leccion2 != null)
				return alumno2.agregarEnsenianza(leccion2, resultado);
			else
				System.out.println("La lección no existe");
		}else
			System.out.println("El alumno no existe");
		return 0;
	}

	public int juegoAgregarLeccion(int juego, int leccion) {
		Juego juego2 = buscarJuego(juego);
		if (juego2 != null){
			Leccion leccion2 = temaBuscarLeccion(leccion);
			if (leccion2 != null)
				return juego2.agregarLeccion(leccion2);
			else
				System.out.println("La lección no existe");
		}else
			System.out.println("El juego no existe");
		return 0;
	}

	public int cursoAgregarAlumno(int docente, int curso, int alumno) {
		Curso curso2 = docenteBuscarCurso(docente, curso);
		if (curso2 != null){
			Alumno alumno2 = buscarAlumno(alumno);
			if (alumno2 != null)
				return curso2.agregarAlumno(alumno2);
			else
				System.out.println("El alumno no existe");
		}else
			System.out.println("El curso no existe");
		return 0;
	}

	public int avatarAgregarElemento(int alumno, String descripcion, String tipo, String color) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null)
			return alumno2.avatarAgregarElemento(descripcion, tipo, color);
		System.out.println("El alumno no existe");
		return 0;
	}

	private Tema buscarTema(String descipcion) {
		for (Tema tema: Temas)
			if (tema.getDescripcion().equals(descipcion))
				return tema;
		return TemaDao.getInstance().buscar(descipcion);
	}
	
	private Tema buscarTema(int id) {
		for (Tema tema: Temas)
			if (tema.getId() == id)
				return tema;
		return TemaDao.getInstance().buscar(id);
	}

	private Docente buscarDocente(String tipoDocumento, int nroDocumento) {
		for (Docente docente: Docentes)
			if (docente.getTipoDocumento().equals(tipoDocumento) && docente.getNroDocumento() == nroDocumento)
				return docente;
		return DocenteDao.getInstance().buscar(tipoDocumento,nroDocumento);
	}
	
	private Docente buscarDocente(int Id) {
		for (Docente docente: Docentes)
			if (docente.getId() == Id)
				return docente;
		return DocenteDao.getInstance().buscar(Id);
	}

	private Alumno buscarAlumno(String tipoDocumento, int nroDocumento) {
		for (Alumno alumno: Alumnos)
			if (alumno.getTipoDocumento().equals(tipoDocumento) && alumno.getNroDocumento() == nroDocumento)
				return alumno;
		return AlumnoDao.getInstance().buscar(tipoDocumento, nroDocumento);
	}
	
	private Alumno buscarAlumno(int Id) {
		for (Alumno alumno: Alumnos)
			if (alumno.getId() == Id)
				return alumno;
		return AlumnoDao.getInstance().buscar(Id);
	}
	
	private Leccion temaBuscarLeccion(int leccion) {
		for (Tema tema: Temas){
			Leccion leccion2 = tema.buscarLeccion(leccion);
			if (leccion2 != null)
				return leccion2;
		}
		Leccion leccion2 = TemaDao.getInstance().buscarConLeccion(leccion);
		if (leccion2 != null){
			return leccion2;
		}
		System.out.println("No existe Tema con esa Lección");
		return null;
	}

	private Juego buscarJuego(String nombre) {
		for(Juego juego: Juegos)
			if (juego.getNombre().equals(nombre))
				return juego;
		return JuegoDao.getInstance().buscar(nombre);
	}
	
	private Juego buscarJuego(int Id) {
		for (Juego juego: Juegos)
			if (juego.getId() == Id)
				return juego;
		return JuegoDao.getInstance().buscar(Id);
	}
	
	private Curso docenteBuscarCurso(int docente, int curso) {
		for (Docente docente2: Docentes){
			if (docente2.getId() == docente){
				Curso curso2 = docente2.buscarCurso(curso);
				if (curso2 != null)
					return curso2;
			}
		}
		return DocenteDao.getInstance().buscarCurso(docente, curso);
	}
	
	private Leccion juegoBuscarLeccion(int leccion) {
		for (Juego juego: Juegos){
			Leccion leccion2 = juego.buscarLeccion(leccion);
			if (leccion2 != null)
				return leccion2;
		}
		return null;
	}
	
	public int elegirJuegoSinTema(int alumno){
		Juego juego = elegirJuego(alumno);
		if (juego != null)
			return juego.getId();
		return 0;
	}

	private Juego elegirJuego(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if(alumno2!= null){
			int leccion = alumno2.calcularSiguienteLeccion(Lietner);
			for (Juego juego: Juegos)
				for (Leccion leccion2: juego.getLecciones())
					if (leccion2.getId() == leccion)
						return juego;
		}else
			System.out.println("El alumno no existe");
		return null;
	}
	
	public int elegirJuegoConTema(int alumno, int tema){
		Juego juego = elegirJuego(alumno);
		if (juego != null)
			if (juego.getTema().getId() == tema)
				return juego.getId();
			else
				elegirJuegoConTema(alumno, tema);
		System.out.println("El alumno no existe");
		return 0;
		
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

	public void setLietner(Integer[] integers) {
		Lietner = integers;
	}
}