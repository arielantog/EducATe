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
import dto.AlimentoDTO;
import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.DocenteDTO;
import dto.TemaDTO;
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
		return alumno.getId();
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

	public int nuevoJuego(String nombre, int nroTema) {
		Juego juego = buscarJuego(nombre);
		if (juego == null){
			Tema tema = buscarTema(nroTema);
			if (tema != null && tema.isActivo()){
				juego = new Juego(nombre, tema);
				juegos.add(juego);
				return juego.getId();
			}else{
				System.out.println("El tema no existe");
				return 0;
			}
		}
		Tema tema = buscarTema(nroTema);
		juego.activar(nombre,tema);
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
	
	public int tipoAvatarAgregarAlimento(int nroAlimento, int nroTipoAvatar){
		Alimento alimento = buscarAlimento(nroAlimento);
		if (alimento != null){
			TipoAvatar tipoAvatar = buscarTipoAvatar(nroTipoAvatar);
			if (tipoAvatar != null)
				return tipoAvatar.agregarAlimento(alimento, true);
			else
				System.out.println("La tipo avatar no existe");
		}else
			System.out.println("El alimento no existe");
		return 0;
	}
	
	public int alumnoAlimentarAvatar(int nroAlumno, int nroAlimento){
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			Alimento alimento = buscarAlimento(nroAlimento);
			if (alimento != null){
				if(alumno.getPuntos()>= alimento.getPrecio()){
					alumno.alimentarAvatar(alimento);
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
	
	public int docenteAgregarCurso(int nroDocente, String descripcion) {
		Docente docente = buscarDocente(nroDocente);
		if (docente != null && docente.isActivo())
			return docente.agregarCurso(descripcion);
		System.out.println("El docente no existe");
		return 0;
	}
	
	public int temaAgregarLeccion(int nroTema, String descripcion) {
		Tema tema = buscarTema(nroTema);
		if (tema != null && tema.isActivo())
			return tema.agregarLeccion(descripcion);
		System.out.println("El tema no existe");
		return 0;
	}

	public int alumnoAgregarEnsenianza(int nroAlumno, int nroLeccion, boolean resultado) {
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			Leccion leccion = juegoBuscarLeccion(nroLeccion);
			if (leccion != null){
				int ensenianza = alumno.agregarEnsenianza(leccion, resultado);
				new HistorialAlumno(alumno,leccion,resultado);
				return ensenianza;
			}
			else
				System.out.println("La lección no existe");
		}else
			System.out.println("El alumno no existe");
		return 0;
	}

	public int juegoAgregarLeccion(int nroJuego, int nroLeccion) {
		Juego juego = buscarJuego(nroJuego);
		if (juego != null && juego.isActivo()){
			Leccion leccion = temaBuscarLeccion(nroLeccion);
			if (leccion != null)
				return juego.agregarLeccion(leccion);
			else
				System.out.println("La lección no existe");
		}else
			System.out.println("El juego no existe");
		return 0;
	}

	public int cursoAgregarAlumno(int nroDocente, int nroCurso, int nroAlumno) {
		Curso curso = docenteBuscarCurso(nroDocente, nroCurso);
		if (curso != null){
			Alumno alumno = buscarAlumno(nroAlumno);
			if (alumno != null && alumno.isActivo())
				return curso.agregarAlumno(alumno);
			else
				System.out.println("El alumno no existe");
		}else
			System.out.println("El curso no existe");
		return 0;
	}
	
	public int alumnoEvolucionarAvatar(int nroAlumno) {
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			alumno.evolucionarAvatar();
		}else{
			System.out.println("El alumno no existe");
		}
		return 0;
	}
	
	public int alumnoRevivirAvatar(int nroAlumno) {
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			alumno.revivirAvatar();
		}else{
			System.out.println("El alumno no existe");
		}
		return 0;
	}
	
	public int alumnoBuscarLeccion(int nroAlumno, int nroJuego){
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			Juego juego = buscarJuego(nroJuego);
			if (juego != null && juego.isActivo()){
				List<Leccion> lecciones = new ArrayList<Leccion>();
				Leccion leccion = null;
				while (leccion == null || !juego.tengoLeccion(leccion)){
					lecciones = alumno.calcularSiguienteLeccion(lietner);
					List<Leccion> lecciones2 = new ArrayList<Leccion>();
					for (Leccion leccion2:lecciones)
						if (juego.tengoLeccion(leccion2))
							lecciones2.add(leccion2);
					int aleatorio = 0;
					if (lecciones2.size()> 0){
						aleatorio = new Random(System.currentTimeMillis()).nextInt(lecciones2.size());
						return lecciones2.get(aleatorio).getId();
					}
				}
				lietner.setIteracion(0);
				return leccion.getId();
			}else
				System.out.println("El juego no existe");
		}else
			System.out.println("El alumno no existe");
		return 0;
	}
	
	public int alumnoGetNivel(int nroAlumno){
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			return alumno.getNivelEnsenianza();
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
	
	private Tema buscarTema(int nroTema) {
		for (Tema tema: temas)
			if (tema.getId() == nroTema)
				return tema;
		return TemaDao.getInstance().buscar(nroTema);
	}

	private Docente buscarDocente(String tipoDocumento, int nroDocumento) {
		for (Docente docente: docentes)
			if (docente.getTipoDocumento().equals(tipoDocumento) && docente.getNroDocumento() == nroDocumento)
				return docente;
		return DocenteDao.getInstance().buscar(tipoDocumento,nroDocumento);
	}
	
	private Docente buscarDocente(int nroDocente) {
		for (Docente docente: docentes)
			if (docente.getId() == nroDocente)
				return docente;
		return DocenteDao.getInstance().buscar(nroDocente);
	}

	private Alumno buscarAlumno(String usuario) {
		for (Alumno alumno: alumnos)
			if (alumno.getUsuario().equals(usuario))
				return alumno;
		return AlumnoDao.getInstance().buscar(usuario);
	}
	
	private Alumno buscarAlumno(int nroAlumno) {
		for (Alumno alumno: alumnos)
			if (alumno.getId() == nroAlumno)
				return alumno;
		Alumno alumno = AlumnoDao.getInstance().buscar(nroAlumno); 
		if (alumno != null){
			alumnos.add(alumno);
			return alumno;
		}
		return null;
	}
	
	private Leccion temaBuscarLeccion(int nroLeccion) {
		for (Tema tema: temas){
			Leccion leccion = tema.buscarLeccion(nroLeccion);
			if (leccion != null)
				return leccion;
		}
		Leccion leccion = TemaDao.getInstance().buscarConLeccion(nroLeccion);
		if (leccion != null){
			return leccion;
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
	
	private Juego buscarJuego(int nroJuego) {
		for (Juego juego: juegos)
			if (juego.getId() == nroJuego)
				return juego;
		return JuegoDao.getInstance().buscar(nroJuego);
	}
	
	private Curso docenteBuscarCurso(int nroDocente, int nroCurso) {
		for (Docente docente: docentes){
			if (docente.getId() == nroDocente){
				Curso curso = docente.buscarCurso(nroCurso);
				if (curso != null)
					return curso;
			}
		}
		return DocenteDao.getInstance().buscarCurso(nroDocente, nroCurso);
	}
	
	private Leccion juegoBuscarLeccion(int nroLeccion) {
		comprobarJuegos();
		for (Juego juego: juegos){
			Leccion leccion = juego.buscarLeccion(nroLeccion);
			if (leccion != null)
				return leccion;
		}
		return null;
	}
	
	private TipoAvatar buscarTipoAvatar(String nombre) {
		for(TipoAvatar tipoAvatar: tipoAvatares)
			if (tipoAvatar.getNombre().equals(nombre))
				return tipoAvatar;
		return TipoAvatarDao.getInstance().buscar(nombre);
	}
	
	private TipoAvatar buscarTipoAvatar(int nroTipoAvatar) {
		for (TipoAvatar tipoAvatar: tipoAvatares)
			if (tipoAvatar.getId() == nroTipoAvatar)
				return tipoAvatar;
		return TipoAvatarDao.getInstance().buscar(nroTipoAvatar);
	}
	
	private Alimento buscarAlimento(String nombre) {
		for(Alimento alimento: alimentos)
			if (alimento.getNombre().equals(nombre))
				return alimento;
		return AlimentoDao.getInstance().buscar(nombre);
	}
	
	private Alimento buscarAlimento(int nroAlimento) {
		for (Alimento alimento: alimentos)
			if (alimento.getId() == nroAlimento)
				return alimento;
		return AlimentoDao.getInstance().buscar(nroAlimento);
	}
	
	public int elegirJuegoSinTema(int nroAlumno){
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			Juego juego = elegirJuego(alumno);
			if (juego != null)
				return juego.getId();
		}else{
			System.out.println("El alumno no existe");
		}
		return 0;
	}

	public int elegirJuegoConTema(int nroAlumno, int nroTema){
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			Tema tema = buscarTema(nroTema);
			if (tema != null && tema.isActivo()){
				Juego juego = elegirJuego(alumno);
				if (juego != null)
					if (juego.getTema().getId() == nroTema)
						return juego.getId();
					else
						elegirJuegoConTema(nroAlumno, nroTema);
			}else{
				System.out.println("El tema no existe");
			}
		}else{
			System.out.println("El alumno no existe");
		}
		return 0;
	}
	
	private Juego elegirJuego(Alumno alumno) {
		List<Leccion> lecciones = alumno.calcularSiguienteLeccion(lietner);
		int aleatorio = new Random(System.currentTimeMillis()).nextInt(lecciones.size());
		Leccion leccion = lecciones.get(aleatorio);
		comprobarJuegos();
		for (Juego juego: juegos)
			for (Leccion leccion2: juego.getLecciones())
				if (leccion2.getId() == leccion.getId())
					return juego;
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
		
	public int agregarValorLietner(int nivel, int desde, int hasta){
		return lietner.agregarValorLietner(nivel, desde, hasta);
	}

	public int eliminarAlumno(int nroAlumno) {
		Alumno alumno = buscarAlumno(nroAlumno);
		if (alumno != null && alumno.isActivo()){
			alumno.eliminar();
			alumnos.remove(nroAlumno);
			return alumno.getId();
		}
		System.out.println("El alumno no existe");
		return 0;
	}

	public int modificarAlumno(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password, String mail, String usuario) {
		Alumno alumno = buscarAlumno(usuario);
		if (alumno != null && alumno.isActivo()){
			alumno.modificar(tipoDocumento, nroDocumento,nombre,apellido, password, mail);
			return alumno.getId();
		}
		System.out.println("El alumno no existe");
		return 0;
		
	}
	
	public int eliminarDocente(int nroDocente) {
		Docente docente = buscarDocente(nroDocente);
		if (docente != null && docente.isActivo()){
			docente.eliminar();
			docentes.remove(docente);
			return docente.getId();
		}
		System.out.println("El docente no existe");
		return 0;
	}

	public int modificarDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password, String mail) {
		Docente docente = buscarDocente(tipoDocumento, nroDocumento);
		if (docente != null && docente.isActivo()){
			docente.modificar(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
			return docente.getId();
		}
		System.out.println("El docente no existe");
		return 0;
		
	}
	
	public int modificarValorLietner(int nivel, int desde, int hasta) {
		boolean existe = lietner.existeNivel(nivel);
		if (existe){
			return lietner.modificarValorLietner(nivel,desde,hasta);
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

	public int eliminarTema(int nroTema) {
		Tema tema = buscarTema(nroTema);
		if (tema != null && tema.isActivo()){
			tema.eliminar();
			temas.remove(tema);
			return tema.getId();
		}
		System.out.println("El tema no existe");
		return 0;
	}

	public int modificarTema(int nroTema, String descripcion) {
		Tema tema2 = buscarTema(descripcion);
		if (tema2 == null){
			Tema tema = buscarTema(nroTema);
			if (tema != null && tema.isActivo()){
				tema.modificar(descripcion);
				return tema.getId();
			}
			System.out.println("El tema no existe");
		}else{
			System.out.println("Ya existe un tema con ese nombre");
		}
		return 0;
	}

	public int eliminarJuego(int nroJuego) {
		Juego juego = buscarJuego(nroJuego);
		if (juego != null && juego.isActivo()){
			juego.eliminar();
			juegos.remove(juego);
			return juego.getId();
		}
		System.out.println("El juego no existe");
		return 0;
	}

	public int modificarJuego(int nroJuego, String descripcion, int nroTema) {
		Juego juego = buscarJuego(nroJuego);
		if (juego != null && juego.isActivo()){
			Juego juego2 = buscarJuego(descripcion);
			if (juego2 == null || juego2.getId() == juego.getId()){
				Tema tema = buscarTema(nroTema);
				if (tema != null && tema.isActivo()){
					juego.modificar(descripcion, tema);
					return juego.getId();
				}else{
					System.out.println("El tema no existe");
				}
			}
			System.out.println("Ya existe un juego con esa descripcion");
		}else{
			System.out.println("El juego no existe");
		}
		return 0;
	}

	public int temaEliminarLeccion(int nroTema, int nroLeccion) {
		Tema tema = buscarTema(nroTema);
		if (tema != null && tema.isActivo()){
			tema.eliminarLeccion(nroLeccion);
			juegos.clear();
			juegos = JuegoDao.getInstance().cargarJuegos();
			for (Juego juego: juegos){
				if (juego.buscarLeccion(nroLeccion) != null)
					juego.quitarLeccion(nroLeccion);
			}
			return 0;
			
		}else{
			System.out.println("El tema no existe");
		}
		return 0;
	}

	public int temaModificarLeccion(int nroTema, int nroLeccion, String descripcion) {
		Tema tema = buscarTema(nroTema);
		if (tema != null && tema.isActivo()){
			tema.modificarLeccion(nroLeccion,descripcion);
		}else{
			System.out.println("El tema no existe");
		}
		return 0;
	}

	public int juegoQuitarLeccion(int nroJuego, int nroLeccion) {
		Juego juego2 = buscarJuego(nroJuego);
		if (juego2 != null && juego2.isActivo()){
			juego2.quitarLeccion(nroLeccion);
		}else{
			System.out.println("El juego no existe");
		}
		return 0;
		
	}

	public int eliminarAlimento(int nroAlimento) {
		Alimento alimento = buscarAlimento(nroAlimento);
		if (alimento != null && alimento.isActivo()){
			alimento.eliminar();
			alimentos.remove(alimento);
			return alimento.getId();
		}
		System.out.println("El alimento no existe");
		return 0;
	}

	public int modificarAlimento(int nroAlimento, String nombre, int proteinas, int precio,String url) {
		Alimento alimento3 = buscarAlimento(nombre);
		if (alimento3 == null){
			Alimento alimento = buscarAlimento(nroAlimento);
			if (alimento != null && alimento.isActivo()){
				alimento.modificar(nombre,proteinas,precio,url);
				return alimento.getId();
			}
			System.out.println("El alimento no existe");
		}else{
			System.out.println("Ya existe un alimento con ese nombre");
		}
		return 0;
	}

	public int eliminarTipoAvatar(int nroTipoAvatar) {
		TipoAvatar tipoAvatar = buscarTipoAvatar(nroTipoAvatar);
		if (tipoAvatar != null && tipoAvatar.isActivo()){
			tipoAvatar.eliminar();
			tipoAvatares.remove(tipoAvatar);
			return tipoAvatar.getId();
		}
		System.out.println("El tipo de avatar no existe");
		return 0;
	}

	public int modificarTipoAvatar(int nroTipoAvatar, String nombre, int alimentoMax, int tiempoHambre, int precioEvolucion, int precioRevivir, String url) {
		TipoAvatar tipoAvatar2 = buscarTipoAvatar(nombre);
		if (tipoAvatar2 == null){
			TipoAvatar tipoAvatar = buscarTipoAvatar(nroTipoAvatar);
			if (tipoAvatar != null && tipoAvatar.isActivo()){
				tipoAvatar.modificar(nombre,alimentoMax,tiempoHambre,precioEvolucion,precioRevivir, url);
				return tipoAvatar.getId();
			}
			System.out.println("El tipo de avatar no existe");
		}else{
			System.out.println("Ya existe un tipo de avatar con ese nombre");
		}
		return 0;
	}

	public int cursoQuitarAlumno(int nroDocente, int nroCurso, int nroAlumno) {
		Docente docente2 = buscarDocente(nroDocente);
		if (docente2 != null && docente2.isActivo()){
			Alumno alumno = buscarAlumno(nroAlumno);
			if (alumno != null){
			docente2.cursoQuitarAlumno(nroCurso,alumno);
			}else{
				System.out.println("El alumno no existe");
			}
		}else{
			System.out.println("El docente no existe");
		}
		return 0;
	}

	public int docenteEliminarCurso(int nroDocente, int nroCurso) {
		Docente docente = buscarDocente(nroDocente);
		if (docente != null && docente.isActivo()){
			docente.eliminarCurso(nroCurso);
			return 0;
		}
		System.out.println("El docente no existe");
		return 0;
	}

	public int docenteModificarCurso(int nroDocente, int nroCurso, String descripcion) {
		Docente docente = buscarDocente(nroDocente);
		if (docente != null && docente.isActivo()){
			docente.modificarCurso(nroCurso, descripcion);
		}else{
			System.out.println("El docente no existe");
		}
		return 0;
	}

	public void tipoAvatarQuitarAlimento(int nroTipoAvatar, int nroAlimento) {
		TipoAvatar tipoAvatar = buscarTipoAvatar(nroTipoAvatar);
		if (tipoAvatar != null && tipoAvatar.isActivo()){
			Alimento alimento = buscarAlimento(nroAlimento);
			if (alimento != null && alimento.isActivo()){
				tipoAvatar.quitarAlimento(alimento);
			}else{
				System.out.println("El alimento no existe");
			}
		}else{
			System.out.println("El tipo de avatar no existe");
		}
		
	}

	public DocenteDTO loginDocente(String tipoDocumento, int nroDocumento, String password) {
		Docente docente = buscarDocente(tipoDocumento, nroDocumento);
		if (docente != null && docente.isActivo()){
			if (docente.validarLogin(password)){
				return docente.pasarDTO();
			}else{
				System.out.println("Contraseña incorrecta");
			}
		}else{
			System.out.println("El docente no existe");
		}
		return null;
	}


	public AlumnoDTO loginAlumno(String usuario, String password) {
		Alumno alumno = buscarAlumno(usuario);
		if (alumno != null && alumno.isActivo()){
			if (alumno.validarLogin(password)){
				return alumno.pasarDTO();
			}else{
				System.out.println("Contraseña incorrecta");
			}
		}else{
			System.out.println("El alumno no existe");
		}
		return null;
	}

	
	/*** LOGICA VIEJA ***
	public List<TemaDTO> listarTemas() {
		List<TemaDTO> temas = TemaDao.getInstance().listarTemas();
		if(temas != null)
			return temas;
		return null;
	}
	
	
	public List<TemaDTO> listarTemas() {
		List<TemaDTO> temasDTO = new ArrayList<TemaDTO>();
		List<Tema> temas = TemaDao.getInstance().listarTemas();
		for(Tema tema: temas) {
			temasDTO.add(tema.pasarDto());
		}
		return temasDTO;
	}
	*/
	
	public List<TemaDTO> listarTemas() {
		List<TemaDTO> temasDTO = new ArrayList<TemaDTO>();
		List<Tema> temas = TemaDao.getInstance().listarTemas();
		for(Tema tema: temas) {
			temasDTO.add(tema.pasarDto());
		}
		return temasDTO;
	}
	
	public List<AlimentoDTO> listarAlimentos() {
		List<AlimentoDTO> alimentoDTO = new ArrayList<AlimentoDTO>();
		List<Alimento> alimentos = AlimentoDao.getInstance().listarAlimentos();
		for(Alimento alimento: alimentos) {
			alimentoDTO.add(alimento.pasarDTO());
		}
		return alimentoDTO;
	}

	public AlumnoDTO traerPerfilAlumno(String usuario) {
		Alumno alumno = AlumnoDao.getInstance().buscar(usuario);
		AlumnoDTO alumnoDTO = alumno.pasarDTO();
		return alumnoDTO;
	}

	public DocenteDTO traerPerfilDocente(String tipoDocumento, int nroDocumento) {
		Docente docente = DocenteDao.getInstance().buscar(tipoDocumento, nroDocumento);
		DocenteDTO docenteDTO = docente.pasarDTO();
		return docenteDTO;
	}

	public List<CursoDTO> listarCursosPorDocente(int docente) {
		List<CursoDTO> cursosDTO = new ArrayList<CursoDTO>();
		List<Curso> cursos = DocenteDao.getInstance().listarCursosPorDocente(docente);
		for(Curso curso : cursos){
			cursosDTO.add(curso.pasarDTO());
		}
		return cursosDTO;
	}

}