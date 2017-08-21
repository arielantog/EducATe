package controlador;

import hibernate.HibernateUtil;

import java.util.*;

import daos.AlimentoDao;
import daos.AlumnoDao;
import daos.AvatarDao;
import daos.CursoDao;
import daos.DocenteDao;
import daos.EnsenianzaDao;
import daos.HistorialAlumnoDao;
import daos.JuegoDao;
import daos.LeccionDao;
import daos.LietnerDao;
import daos.TemaDao;
import daos.TipoAvatarDao;
import dto.AlumnoDTO;
import dto.DocenteDTO;
import negocio.Alimento;
import negocio.Alumno;
import negocio.Curso;
import negocio.Docente;
import negocio.HistorialAlumno;
import negocio.Juego;
import negocio.Leccion;
import negocio.Lietner;
import negocio.Tema;
import negocio.TipoAvatar;

public class Sistema {

	public Sistema() {
		new HibernateUtil();
		alumnos = new ArrayList<Alumno>();
		docentes = new ArrayList<Docente>();
		juegos = new ArrayList<Juego>();
		temas = new ArrayList<Tema>();
		alimentos = new ArrayList<Alimento>();
		tipoAvatares = new ArrayList<TipoAvatar>();
		cargarVariablesGlobales();
		cargarLietner();
		TipoAvatarDao.getInstance().buscar(1);
		cargarTimer();
	}

	private void cargarLietner() {
		if (!LietnerDao.getInstance().existe(1)){
			//Persiste en la base
			lietner = new Lietner(1);
		}else{
			//No persiste en la base
			lietner = new Lietner();
			lietner.cargarValores();
		};
	}

	private static Sistema singleton;
	private List<Alumno> alumnos;
	private List<Docente> docentes;
	private List<Juego> juegos;
	private List<Tema> temas;
	private List<TipoAvatar> tipoAvatares;
	private List<Alimento> alimentos;
	private Lietner lietner;


	public static Sistema getInstance() {
		if (singleton == null)
			singleton = new Sistema();
		return singleton;
	}
	
	
	private void cargarVariablesGlobales() {
		AlumnoDao.getInstance().cargarVariableGlobal();
		AvatarDao.getInstance().cargarVariableGlobal();
		CursoDao.getInstance().cargarVariableGlobal();
		DocenteDao.getInstance().cargarVariableGlobal();
		JuegoDao.getInstance().cargarVariableGlobal();
		LeccionDao.getInstance().cargarVariableGlobal();
		TemaDao.getInstance().cargarVariableGlobal();
		EnsenianzaDao.getInstance().cargarVariableGlobal();
		AlimentoDao.getInstance().cargarVariableGlobal();
		TipoAvatarDao.getInstance().cargarVariableGlobal();
		HistorialAlumnoDao.getInstance().cargarVariableGlobal();
	}
	
	private void cargarTimer() {
		TimerTask timerTask = new TimerTask() 
	     { 
	         public void run()  
	         { 
	             avatarDescontarHambre();
	         }

			private void avatarDescontarHambre() {
				alumnos.clear();
				List<Alumno> alumnos = AlumnoDao.getInstance().cargarAlumnos();
				for (Alumno alumno: alumnos){
					alumno.avatarDescontarHambre();
				}
				
			} 
	     }; 
	     Timer timer = new Timer(); 
	     timer.schedule(timerTask, 0, 100000);
	}
	
	public int nuevoAlumno(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password, String mail, String usuario) {
		Alumno alumno = buscarAlumno(usuario);
		if (alumno == null){
			alumno = new Alumno(tipoDocumento, nroDocumento, nombre, apellido, password, mail, usuario);
			for (Tema tema: temas)
				for (Leccion leccion: tema.getLecciones())
					alumno.agregarEnsenianza(leccion, false);
			AlumnoDao.getInstance().actualizar(alumno.pasarBean());
			alumnos.add(alumno);
			return alumno.getId();
		}
		System.out.println("El alumno ya existe");		
		return 0;
	}
	
	public int activarAlumno(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password, String mail, String usuario) {
		Alumno alumno = buscarAlumno(usuario);
		if (alumno == null){
			alumno = new Alumno(tipoDocumento, nroDocumento, nombre, apellido, password, mail, usuario);
			for (Tema tema: temas)
				for (Leccion leccion: tema.getLecciones())
					alumno.agregarEnsenianza(leccion, false);
			AlumnoDao.getInstance().actualizar(alumno.pasarBean());
			alumnos.add(alumno);
			return alumno.getId();
		}
		alumno.activar(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
		return 0;
	}

	public int nuevoDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password, String mail) {
		Docente docente = buscarDocente(tipoDocumento, nroDocumento);
		if (docente == null){
			docente = new Docente(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
			docentes.add(docente);
			return docente.getId();
		}
		docente.activar(nombre,apellido);
		return docente.getId();
	}

	public int nuevoTema(String descripcion) {
		Tema tema = buscarTema(descripcion);
		if (tema == null){
			tema = new Tema(descripcion);
			temas.add(tema);
			return tema.getId();
		}
		tema.activar(descripcion);
		return tema.getId();
	}

	public int nuevoJuego(String nombre, int tema) {
		Juego juego = buscarJuego(nombre);
		if (juego == null){
			Tema tema2 = buscarTema(tema);
			if (tema2 != null && tema2.isActivo()){
				juego = new Juego(nombre, tema2);
				juegos.add(juego);
				return juego.getId();
			}else{
				System.out.println("El tema no existe");
				return 0;
			}
		}
		Tema tema2 = buscarTema(tema);
		juego.activar(nombre,tema2);
		return juego.getId();
	}
	
	public int nuevoTipoAvatar(String nombre, int alimentoMax, int tiempoHambre, int precioEvolucion, int precioRevivir, String url){
		TipoAvatar tipoAvatar = buscarTipoAvatar(nombre);
		if (tipoAvatar == null){
			tipoAvatar = new TipoAvatar(nombre, alimentoMax, tiempoHambre, precioEvolucion, precioRevivir, url);
			tipoAvatares.add(tipoAvatar);
			return tipoAvatar.getId();
		}
		tipoAvatar.activar(nombre,alimentoMax,tiempoHambre,precioEvolucion,precioRevivir, url);
		return tipoAvatar.getId();
	}
	
	public int nuevoAlimento(String nombre, int proteinas, int precio, String url){
		Alimento alimento = buscarAlimento(nombre);
		if (alimento == null){
			alimento = new Alimento(nombre, proteinas, precio,url);
			alimentos.add(alimento);
			return alimento.getId();
		}
		alimento.activar(nombre,proteinas,precio,url);
		return alimento.getId();
	}
	
	public int tipoAvatarAgregarAlimento(int alimento, int tipoAvatar){
		Alimento alimento2 = buscarAlimento(alimento);
		if (alimento2 != null){
			TipoAvatar tipoAvatar2 = buscarTipoAvatar(tipoAvatar);
			if (tipoAvatar2 != null)
				return tipoAvatar2.agregarAlimento(alimento2, true);
			else
				System.out.println("La tipo avatar no existe");
		}else
			System.out.println("El alimento no existe");
		return 0;
	}
	
	public int alumnoAlimentarAvatar(int alumno, int alimento){
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			Alimento alimento2 = buscarAlimento(alimento);
			if (alimento2 != null){
				if(alumno2.getPuntos()>= alimento2.getPrecio()){
					alumno2.alimentarAvatar(alimento2);
				}else{
					System.out.println("No hay suficientes puntos"); 
				}
			}else{
				System.out.println("No existe el alimento");
			}
		}else{
			System.out.println("No existe el alumno");
		}
		return 0;
	}
	
	public int docenteAgregarCurso(int docente, String descripcion) {
		Docente docente2 = buscarDocente(docente);
		if (docente2 != null && docente2.isActivo())
			return docente2.agregarCurso(descripcion);
		System.out.println("El docente no existe");
		return 0;
	}
	
	public int temaAgregarLeccion(int tema, String descripcion) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null && tema2.isActivo())
			return tema2.agregarLeccion(descripcion);
		System.out.println("El tema no existe");
		return 0;
	}

	public int alumnoAgregarEnsenianza(int alumno, int leccion, boolean resultado) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			Leccion leccion2 = juegoBuscarLeccion(leccion);
			if (leccion2 != null){
				int ensenianza = alumno2.agregarEnsenianza(leccion2, resultado);
				new HistorialAlumno(alumno2,leccion2,resultado);
				return ensenianza;
			}
			else
				System.out.println("La lección no existe");
		}else
			System.out.println("El alumno no existe");
		return 0;
	}

	public int juegoAgregarLeccion(int juego, int leccion) {
		Juego juego2 = buscarJuego(juego);
		if (juego2 != null && juego2.isActivo()){
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
			if (alumno2 != null && alumno2.isActivo())
				return curso2.agregarAlumno(alumno2);
			else
				System.out.println("El alumno no existe");
		}else
			System.out.println("El curso no existe");
		return 0;
	}
	
	public int alumnoEvolucionarAvatar(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			alumno2.evolucionarAvatar();
		}else{
			System.out.println("El alumno no existe");
		}
		return 0;
	}
	
	public int alumnoRevivirAvatar(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			alumno2.revivirAvatar();
		}else{
			System.out.println("El alumno no existe");
		}
		return 0;
	}
	
	public int alumnoBuscarLeccion(int alumno, int juego){
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			Juego juego2 = buscarJuego(juego);
			if (juego2 != null && juego2.isActivo()){
				Leccion leccion = null;
				while (leccion == null || !juego2.tengoLeccion(leccion)){
					leccion = alumno2.calcularSiguienteLeccion(lietner);
				}
				return leccion.getId();
			}else
				System.out.println("El juego no existe");
		}else
			System.out.println("El alumno no existe");
		return 0;
	}
	
	public int alumnoGetNivel(int alumno){
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			return alumno2.getNivelEnsenianza();
		}else
			System.out.println("El alumno no existe");
		return 0;
	}

	private Tema buscarTema(String descipcion) {
		for (Tema tema: temas)
			if (tema.getDescripcion().equals(descipcion))
				return tema;
		return TemaDao.getInstance().buscar(descipcion);
	}
	
	private Tema buscarTema(int id) {
		for (Tema tema: temas)
			if (tema.getId() == id)
				return tema;
		return TemaDao.getInstance().buscar(id);
	}

	private Docente buscarDocente(String tipoDocumento, int nroDocumento) {
		for (Docente docente: docentes)
			if (docente.getTipoDocumento().equals(tipoDocumento) && docente.getNroDocumento() == nroDocumento)
				return docente;
		return DocenteDao.getInstance().buscar(tipoDocumento,nroDocumento);
	}
	
	private Docente buscarDocente(int Id) {
		for (Docente docente: docentes)
			if (docente.getId() == Id)
				return docente;
		return DocenteDao.getInstance().buscar(Id);
	}

	public Alumno buscarAlumno(String tipoDocumento, int nroDocumento) {
		for (Alumno alumno: alumnos)
			if (alumno.getTipoDocumento().equals(tipoDocumento) && alumno.getNroDocumento() == nroDocumento)
				return alumno;
		return AlumnoDao.getInstance().buscar(tipoDocumento, nroDocumento);
	}
	
	private Alumno buscarAlumno(String usuario) {
		for (Alumno alumno: alumnos)
			if (alumno.getUsuario().equals(usuario))
				return alumno;
		return AlumnoDao.getInstance().buscar(usuario);
	}
	
	private Alumno buscarAlumno(int Id) {
		for (Alumno alumno: alumnos)
			if (alumno.getId() == Id)
				return alumno;
		Alumno alumno = AlumnoDao.getInstance().buscar(Id); 
		if (alumno != null){
			alumnos.add(alumno);
			return alumno;
		}
		return null;
	}
	
	private Leccion temaBuscarLeccion(int leccion) {
		for (Tema tema: temas){
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
		for(Juego juego: juegos)
			if (juego.getNombre().equals(nombre))
				return juego;
		return JuegoDao.getInstance().buscar(nombre);
	}
	
	private Juego buscarJuego(int Id) {
		for (Juego juego: juegos)
			if (juego.getId() == Id)
				return juego;
		return JuegoDao.getInstance().buscar(Id);
	}
	
	private Curso docenteBuscarCurso(int docente, int curso) {
		for (Docente docente2: docentes){
			if (docente2.getId() == docente){
				Curso curso2 = docente2.buscarCurso(curso);
				if (curso2 != null)
					return curso2;
			}
		}
		return DocenteDao.getInstance().buscarCurso(docente, curso);
	}
	
	private Leccion juegoBuscarLeccion(int leccion) {
		comprobarJuegos();
		for (Juego juego: juegos){
			Leccion leccion2 = juego.buscarLeccion(leccion);
			if (leccion2 != null)
				return leccion2;
		}
		return null;
	}
	
	private TipoAvatar buscarTipoAvatar(String nombre) {
		for(TipoAvatar tipoAvatar: tipoAvatares)
			if (tipoAvatar.getNombre().equals(nombre))
				return tipoAvatar;
		return TipoAvatarDao.getInstance().buscar(nombre);
	}
	
	private TipoAvatar buscarTipoAvatar(int Id) {
		for (TipoAvatar tipoAvatar: tipoAvatares)
			if (tipoAvatar.getId() == Id)
				return tipoAvatar;
		return TipoAvatarDao.getInstance().buscar(Id);
	}
	
	private Alimento buscarAlimento(String nombre) {
		for(Alimento alimento: alimentos)
			if (alimento.getNombre().equals(nombre))
				return alimento;
		return AlimentoDao.getInstance().buscar(nombre);
	}
	
	private Alimento buscarAlimento(int Id) {
		for (Alimento alimento: alimentos)
			if (alimento.getId() == Id)
				return alimento;
		return AlimentoDao.getInstance().buscar(Id);
	}
	
	public int elegirJuegoSinTema(int alumno){
		Juego juego = elegirJuego(alumno);
		if (juego != null)
			return juego.getId();
		return 0;
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
	
	private Juego elegirJuego(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if(alumno2!= null && alumno2.isActivo()){
			Leccion leccion = alumno2.calcularSiguienteLeccion(lietner);
			comprobarJuegos();
			for (Juego juego: juegos)
				for (Leccion leccion2: juego.getLecciones())
					if (leccion2.getId() == leccion.getId())
						return juego;
		}else
			System.out.println("El alumno no existe");
		return null;
	}

	private void comprobarJuegos() {
		//Comprueba que todos los juegos estén cargados en memoria
		int cantidadJuegos = JuegoDao.getInstance().cantidadJuegos();
		if (juegos.size() < cantidadJuegos){
			List<Juego> juegos2 = JuegoDao.getInstance().cargarJuegos();
			boolean existe = false;
			for (Juego juego2: juegos2){
				for (Juego juego: juegos){
					if (juego.getId() == juego2.getId())
						existe = true;
				
				}
				if(existe != true)
					juegos.add(juego2);
				existe = false;
			}
		}
	}
	
	/*private Integer[] calcularLietner() {
		return new Lietner().calcularLietner();
	}*/
	
	public int agregarValorLietner(int nivel, int desde, int hasta){
		return lietner.agregarValorLietner(nivel, desde, hasta);
	}

	public int eliminarAlumno(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			alumno2.eliminar();
			alumnos.remove(alumno);
			return alumno2.getId();
		}
		System.out.println("El alumno no existe");
		return 0;
	}

	public int modificarAlumno(String tipoDocumento, int nroDocumento, String nombre, String apellido) {
		Alumno alumno = buscarAlumno(tipoDocumento, nroDocumento);
		if (alumno != null && alumno.isActivo()){
			alumno.modificar(nombre,apellido);
			return alumno.getId();
		}
		System.out.println("El alumno no existe");
		return 0;
		
	}
	
	public int eliminarDocente(int docente) {
		Docente docente2 = buscarDocente(docente);
		if (docente2 != null && docente2.isActivo()){
			docente2.eliminar();
			docentes.remove(docente2);
			return docente2.getId();
		}
		System.out.println("El docente no existe");
		return 0;
	}

	public int modificarDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido) {
		Docente docente = buscarDocente(tipoDocumento, nroDocumento);
		if (docente != null && docente.isActivo()){
			docente.modificar(nombre,apellido);
			return docente.getId();
		}
		System.out.println("El docente no existe");
		return 0;
		
	}
	
	public int modificarValorLietner(int nivel, int desde, int hasta) {
		boolean existe = lietner.existeNivel(nivel);
		if (existe){
			return lietner.modificarValor(nivel,desde,hasta);
		}
		System.out.println("El nivel Lietner no existe");
		return 0;
		
	}

	public int eliminarValorLietner(int nivel) {
		boolean existe = lietner.existeNivel(nivel);
		if (existe){
			return lietner.eliminarValorLietner(nivel);
		}
		return 0;
	}

	public int eliminarTema(int tema) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null && tema2.isActivo()){
			tema2.eliminar();
			temas.remove(tema2);
			return tema2.getId();
		}
		System.out.println("El tema no existe");
		return 0;
	}

	public int modificarTema(int tema, String descripcion) {
		Tema tema2 = buscarTema(tema);
		Tema tema3 = buscarTema(descripcion);
		if (tema3 == null){
			if (tema2 != null && tema2.isActivo()){
				tema2.modificar(descripcion);
				return tema2.getId();
			}
			System.out.println("El tema no existe");
		}else{
			System.out.println("Ya existe un tema con ese nombre");
		}
		return 0;
	}

	public int eliminarJuego(int juego) {
		Juego juego2 = buscarJuego(juego);
		if (juego2 != null && juego2.isActivo()){
			juego2.eliminar();
			juegos.remove(juego2);
			return juego2.getId();
		}
		System.out.println("El juego no existe");
		return 0;
	}

	public int modificarJuego(int juego, String descripcion, int tema) {
		Juego juego2 = buscarJuego(juego);
		Juego juego3 = buscarJuego(descripcion);
		if (juego3 == null || juego3.getId() == juego2.getId()){
			if (juego2 != null && juego2.isActivo()){
				Tema tema2 = buscarTema(tema);
				if (tema2 != null && tema2.isActivo()){
					juego2.modificar(descripcion, tema2);
					return juego2.getId();
				}else{
					System.out.println("El tema no existe");
				}
			}
			System.out.println("El juego no existe");
		}else{
			System.out.println("Ya existe un juego con esa descripcion");
		}
		return 0;
	}

	public int temaEliminarLeccion(int tema, int leccion) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null && tema2.isActivo()){
			tema2.eliminarLeccion(leccion);
			juegos.clear();
			juegos = JuegoDao.getInstance().cargarJuegos();
			for (Juego juego: juegos){
				if (juego.buscarLeccion(leccion) != null)
					juego.quitarLeccion(leccion);
			}
			return 0;
			
		}else{
			System.out.println("El tema no existe");
		}
		return 0;
	}

	public int temaModificarLeccion(int tema, int leccion, String descripcion) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null && tema2.isActivo()){
			tema2.modificarLeccion(leccion,descripcion);
		}else{
			System.out.println("El tema no existe");
		}
		return 0;
	}

	public int juegoQuitarLeccion(int juego, int leccion) {
		Juego juego2 = buscarJuego(juego);
		if (juego2 != null && juego2.isActivo()){
			juego2.quitarLeccion(leccion);
		}else{
			System.out.println("El juego no existe");
		}
		return 0;
		
	}

	public int eliminarAlimento(int alimento) {
		Alimento alimento2 = buscarAlimento(alimento);
		if (alimento2 != null && alimento2.isActivo()){
			alimento2.eliminar();
			alimentos.remove(alimento2);
			return alimento2.getId();
		}
		System.out.println("El alimento no existe");
		return 0;
	}

	public int modificarAlimento(int alimento, String nombre, int proteinas, int precio,String url) {
		Alimento alimento2 = buscarAlimento(alimento);
		Alimento alimento3 = buscarAlimento(nombre);
		if (alimento3 == null){
			if (alimento2 != null && alimento2.isActivo()){
				alimento2.modificar(nombre,proteinas,precio,url);
				return alimento2.getId();
			}
			System.out.println("El alimento no existe");
		}else{
			System.out.println("Ya existe un alimento con ese nombre");
		}
		return 0;
	}

	public int eliminarTipoAvatar(int tipoAvatar) {
		TipoAvatar tipoAvatar2 = buscarTipoAvatar(tipoAvatar);
		if (tipoAvatar2 != null && tipoAvatar2.isActivo()){
			tipoAvatar2.eliminar();
			tipoAvatares.remove(tipoAvatar2);
			return tipoAvatar2.getId();
		}
		System.out.println("El tipo de avatar no existe");
		return 0;
	}

	public int modificarTipoAvatar(int tipoAvatar, String nombre, int alimentoMax, int tiempoHambre, int precioEvolucion, int precioRevivir, String url) {
		TipoAvatar tipoAvatar2 = buscarTipoAvatar(tipoAvatar);
		TipoAvatar tipoAvatar3 = buscarTipoAvatar(nombre);
		if (tipoAvatar3 == null){
			if (tipoAvatar2 != null && tipoAvatar2.isActivo()){
				tipoAvatar2.modificar(nombre,alimentoMax,tiempoHambre,precioEvolucion,precioRevivir, url);
				return tipoAvatar2.getId();
			}
			System.out.println("El tipo de avatar no existe");
		}else{
			System.out.println("Ya existe un tipo de avatar con ese nombre");
		}
		return 0;
	}

	public int cursoQuitarAlumno(int docente, int curso, int alumno) {
		Docente docente2 = buscarDocente(docente);
		if (docente2 != null && docente2.isActivo()){
			docente2.cursoQuitarAlumno(curso,alumno);
		}else{
			System.out.println("El docente no existe");
		}
		return 0;
	}

	public int docenteEliminarCurso(int docente, int curso) {
		Docente docente2 = buscarDocente(docente);
		if (docente2 != null && docente2.isActivo()){
			docente2.eliminarCurso(curso);
			return 0;
		}
		System.out.println("El docente no existe");
		return 0;
	}

	public int docenteModificarCurso(int docente, int curso, String descripcion) {
		Docente docente2 = buscarDocente(docente);
		if (docente2 != null && docente2.isActivo()){
			docente2.modificarCurso(curso, descripcion);
		}else{
			System.out.println("El docente no existe");
		}
		return 0;
	}

	public void tipoAvatarQuitarAlimento(int tipoAvatar, int alimento) {
		TipoAvatar tipoAvatar2 = buscarTipoAvatar(tipoAvatar);
		if (tipoAvatar2 != null && tipoAvatar2.isActivo()){
			tipoAvatar2.quitarAlimento(alimento);
		}else{
			System.out.println("El tipo de avatar no existe");
		}
		
	}

	public DocenteDTO loginDocente(String tipoDocumento, int nroDocumento, String password) {
		DocenteDTO docente = DocenteDao.getInstance().loginDocente(tipoDocumento, nroDocumento, password);
		if(docente != null)
			return docente;
		return null;
	}


	public AlumnoDTO loginAlumno(String usuario, String password) {
		AlumnoDTO alumno = AlumnoDao.getInstance().loginAlumno(usuario, password);
		if(alumno != null)
			return alumno;
		return null;
	}


}