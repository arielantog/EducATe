package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.Sistema;
import dto.AlimentoDTO;
import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.DocenteDTO;
import dto.TemaDTO;

@SuppressWarnings("serial")
public class ServerImpl extends UnicastRemoteObject implements IRmiServer{

	protected ServerImpl() throws RemoteException {
		super();
	}
	
	public AlumnoDTO nuevoAlumno(String tipoDocumento, int nroDocumento,
			String nombre, String apellido, String usuario, String password, String mail) throws RemoteException {
		return Sistema.getInstance().nuevoAlumno(tipoDocumento, nroDocumento, nombre, apellido, usuario, password, mail);
	}

	public DocenteDTO nuevoDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password,
			String mail) throws RemoteException {
		return Sistema.getInstance().nuevoDocente(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
	}

	public DocenteDTO loginDocente(String tipoDocumento, int nroDocumento, String password) throws RemoteException {
		return Sistema.getInstance().loginDocente(tipoDocumento, nroDocumento, password);
	}

	public AlumnoDTO loginAlumno(String usuario, String password) throws RemoteException {
		return Sistema.getInstance().loginAlumno(usuario, password);
	}

	public int alumnoBuscarLeccion(int alumno, int juego) throws RemoteException {
		return Sistema.getInstance().alumnoBuscarLeccion(alumno, juego);
	}
	
	public void alumnoAgregarEnsenianza(int alumno, int leccion, boolean resultado) throws RemoteException {
		Sistema.getInstance().alumnoAgregarEnsenianza(alumno, leccion, resultado);
	}

	public int alumnoGetNivel(int alumno) throws RemoteException {
		return Sistema.getInstance().alumnoGetNivel(alumno);		
	}

	public List<TemaDTO> listarTemas() {
		return Sistema.getInstance().listarTemas();
	}
	
	public List<AlimentoDTO> listarAlimentos(int nroTipoAvatar) {
		return Sistema.getInstance().listarAlimentos(nroTipoAvatar);
	}

	public AlumnoDTO traerPerfilAlumno(String usuario) throws RemoteException {
		return Sistema.getInstance().traerPerfilAlumno(usuario);
	}

	public int modificarAlumno(String tipoDocumento, int nroDocumento, String nombre, String apellido,
			String password, String mail, String usuario) throws RemoteException {
		return Sistema.getInstance().modificarAlumno(tipoDocumento, nroDocumento, nombre, apellido, password, mail, usuario);
	}

	public int modificarDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido, String mail,
			String password) throws RemoteException {
		return Sistema.getInstance().modificarDocente(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
	}

	public DocenteDTO traerPerfilDocente(String tipoDocumento, int nroDocumento) throws RemoteException {
		return Sistema.getInstance().traerPerfilDocente(tipoDocumento, nroDocumento);
	}

	public List<CursoDTO> listarCursosPorDocente(int docente) throws RemoteException {
		return Sistema.getInstance().listarCursosPorDocente(docente);
	}

	public CursoDTO traerCursoDocente(int docente, int curso) throws RemoteException {
		return Sistema.getInstance().traerCursoDocente(docente, curso);
	}

	public int elegirJuegoSinTema(int nroAlumno) throws RemoteException {
		return Sistema.getInstance().elegirJuegoSinTema(nroAlumno);
	}

	public int elegirJuegoConTema(int nroAlumno, int nroTema) throws RemoteException {
		return Sistema.getInstance().elegirJuegoConTema(nroAlumno, nroTema);
	}

	public AlumnoDTO alumnoEvolucionarAvatar(int nroAlumno) throws RemoteException {
		return Sistema.getInstance().alumnoEvolucionarAvatar(nroAlumno);
		
	}

	public AlumnoDTO alumnoRevivirAvatar(int nroAlumno) throws RemoteException {
		return Sistema.getInstance().alumnoRevivirAvatar(nroAlumno);
	}

	public AlumnoDTO buscarAlumnoAsignarCurso(String tipoDocumento, int nroDocumento) throws RemoteException {
		return Sistema.getInstance().buscarAlumnoAsignarCurso(tipoDocumento, nroDocumento);
	}

	public CursoDTO cursoAgregarAlumno(int nroDocente, int nroCurso, int nroAlumno) throws RemoteException {
		return Sistema.getInstance().cursoAgregarAlumno(nroDocente, nroCurso, nroAlumno);
	}

	public CursoDTO docenteAgregarCurso(int nroCurso, String descripcion) throws RemoteException {
		return Sistema.getInstance().docenteAgregarCurso(nroCurso, descripcion);
	}

	public AlumnoDTO alumnoAlimentarAvatar(int nroAlumno, int nroAlimento)
			throws RemoteException {
		return Sistema.getInstance().alumnoAlimentarAvatar(nroAlumno, nroAlimento);
		
	}

}
