package controlador;

import hibernate.HibernateUtil;

import java.util.*;

import beans.LietnerBean;
import daos.AlimentoDao;
import daos.AlumnoDao;
import daos.AvatarDao;
import daos.CursoDao;
import daos.DocenteDao;
import daos.EnsenianzaDao;
import daos.JuegoDao;
import daos.LeccionDao;
import daos.LietnerDao;
import daos.TemaDao;
import daos.TipoAvatarDao;
import negocio.Alimento;
import negocio.Alumno;
import negocio.Curso;
import negocio.Docente;
import negocio.Juego;
import negocio.Leccion;
import negocio.Tema;
import negocio.TipoAvatar;

public class Sistema {

	public Sistema() {
		new HibernateUtil();
		Alumnos = new ArrayList<Alumno>();
		Docentes = new ArrayList<Docente>();
		Juegos = new ArrayList<Juego>();
		Temas = new ArrayList<Tema>();
		Alimentos = new ArrayList<Alimento>();
		TipoAvatares = new ArrayList<TipoAvatar>();
		cargarVariablesGlobales();
		setLietner(calcularLietner());
		TipoAvatarDao.getInstance().buscar(1);
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
	}

	private static Sistema Singleton;
	private List<Alumno> Alumnos;
	private List<Docente> Docentes;
	private List<Juego> Juegos;
	private List<Tema> Temas;
	private List<TipoAvatar> TipoAvatares;
	private List<Alimento> Alimentos;
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
			AlumnoDao.getInstance().actualizar(alumno.pasarBean());
			Alumnos.add(alumno);
			return alumno.getId();
		}
		alumno.activar(nombre,apellido);
		return alumno.getId();
	}

	public int nuevoDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido) {
		Docente docente = buscarDocente(tipoDocumento, nroDocumento);
		if (docente == null){
			docente = new Docente(tipoDocumento, nroDocumento, nombre, apellido);
			Docentes.add(docente);
			return docente.getId();
		}
		docente.activar(nombre,apellido);
		return docente.getId();
	}

	public int nuevoTema(String descripcion) {
		Tema tema = buscarTema(descripcion);
		if (tema == null){
			tema = new Tema(descripcion);
			Temas.add(tema);
			return tema.getId();
		}
		tema.activar(descripcion);
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
	
	public int nuevoTipoAvatar(String nombre, int alimentoMax, int nivel, int tiempoHambre, int precioEvolucion, int precioRevivir){
		TipoAvatar tipoAvatar = buscarTipoAvatar(nombre);
		if (tipoAvatar == null){
			tipoAvatar = new TipoAvatar(nombre, alimentoMax, nivel, tiempoHambre, precioEvolucion, precioRevivir);
			TipoAvatares.add(tipoAvatar);
			return tipoAvatar.getId();
		}
		System.out.println("El tipoAvatar ya existe");
		return tipoAvatar.getId();
	}
	
	public int nuevoAlimento(String nombre, int proteinas, int precio){
		Alimento alimento = buscarAlimento(nombre);
		if (alimento == null){
			alimento = new Alimento(nombre, proteinas, precio);
			Alimentos.add(alimento);
			return alimento.getId();
		}
		System.out.println("El alimento ya existe");
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
	
	private TipoAvatar buscarTipoAvatar(String nombre) {
		for(TipoAvatar tipoAvatar: TipoAvatares)
			if (tipoAvatar.getNombre().equals(nombre))
				return tipoAvatar;
		return TipoAvatarDao.getInstance().buscar(nombre);
	}
	
	private TipoAvatar buscarTipoAvatar(int Id) {
		for (TipoAvatar tipoAvatar: TipoAvatares)
			if (tipoAvatar.getId() == Id)
				return tipoAvatar;
		return TipoAvatarDao.getInstance().buscar(Id);
	}
	
	private Alimento buscarAlimento(String nombre) {
		for(Alimento alimento: Alimentos)
			if (alimento.getNombre().equals(nombre))
				return alimento;
		return AlimentoDao.getInstance().buscar(nombre);
	}
	
	private Alimento buscarAlimento(int Id) {
		for (Alimento alimento: Alimentos)
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
			int leccion = alumno2.calcularSiguienteLeccion(Lietner);
			comprobarJuegos();
			for (Juego juego: Juegos)
				for (Leccion leccion2: juego.getLecciones())
					if (leccion2.getId() == leccion)
						return juego;
		}else
			System.out.println("El alumno no existe");
		return null;
	}

	private void comprobarJuegos() {
		int cantidadJuegos = JuegoDao.getInstance().cantidadJuegos();
		if (Juegos.size() < cantidadJuegos){
			List<Juego> juegos = JuegoDao.getInstance().cargarJuegos();
			boolean existe = false;
			for (Juego juego2: juegos){
				for (Juego juego: Juegos){
					if (juego.getId() == juego2.getId())
						existe = true;
				
				}
				if(existe != true)
					Juegos.add(juego2);
				existe = false;
			}
		}
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
		lietnerValores = LietnerDao.getInstance().cargarValores();
		if (lietnerValores[0] == null){
			//Default
			lietnerValores[0] = 1;
			lietnerValores[1] = 1;
			lietnerValores[2] = 1;
			lietnerValores[3] = 1;
			lietnerValores[4] = 1;
		}
		return lietnerValores;
	}

	public Integer[] getLietner() {
		return Lietner;
	}

	public void setLietner(Integer[] integers) {
		Lietner = integers;
	}
	
	public int agregarValorLietner(int pos, int valor){
		negocio.Lietner lietner = new negocio.Lietner(pos, valor);
		LietnerBean lietnerBean = lietner.pasarBean();
		boolean existe = LietnerDao.getInstance().existe(pos);
		if (existe)
			LietnerDao.getInstance().actualizar(lietnerBean);
		else
			LietnerDao.getInstance().grabar(lietnerBean);
		Lietner = LietnerDao.getInstance().cargarValores();
		return lietner.getId();
	}

	public int eliminarAlumno(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null && alumno2.isActivo()){
			alumno2.eliminar();
			Alumnos.remove(alumno);
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
			Docentes.remove(docente2);
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

	public int eliminarValorLietner(int valor) {
		negocio.Lietner lietner = new negocio.Lietner(valor, 0);
		boolean existe = LietnerDao.getInstance().existe(valor);
		if (existe){
			LietnerBean lietnerBean = lietner.pasarBean();
			LietnerDao.getInstance().eliminar(lietnerBean);
		}
		Lietner = LietnerDao.getInstance().cargarValores();
		return 0;
	}

	public int eliminarTema(int tema) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null && tema2.isActivo()){
			tema2.eliminar();
			Temas.remove(tema2);
			return tema2.getId();
		}
		System.out.println("El tema no existe");
		return 0;
	}

	public int modificarTema(int tema, String descripcion) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null && tema2.isActivo()){
			tema2.modificar(descripcion);
			return tema2.getId();
		}
		System.out.println("El tema no existe");
		return 0;
		
	}

	public int activarTema(int tema) {
		Tema tema2 = buscarTema(tema);
		if (tema2 != null && !tema2.isActivo()){
			tema2.activar(tema2.getDescripcion());
			Temas.add(tema2);
			return tema2.getId();
		}
		System.out.println("El tema no existe");
		return 0;
	}

}