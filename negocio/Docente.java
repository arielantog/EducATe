package negocio;

import java.util.*;

import daos.DocenteDao;
import beans.CursoBean;
import beans.DocenteBean;

public class Docente extends Persona {

	public Docente(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		Id = ID++;
		Cursos = new ArrayList<Curso>();
		DocenteDao.getInstance().grabar(pasarBean());
		
	}
	public Docente(int Id, String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		this.Id = Id;
		Cursos = new ArrayList<Curso>();
	}

	private static Integer ID = 1;
	private Integer Id;
	private List<Curso> Cursos;

	public Integer agregarCurso(String descripcion) {
		Curso curso = buscarCurso(descripcion);
		if (curso == null){
			Curso curso2 = new Curso(descripcion);
			Cursos.add(curso2);
			DocenteDao.getInstance().actualizar(pasarBean());
			return curso2.getId();
		}
		return 0;
	}
	public void agregarCurso(Curso curso) {
		Cursos.add(curso);
	}

	public Integer cursoAgregarAlumno(Integer Curso, Alumno Alumno) {
		Curso curso = buscarCurso(Curso);
		if (curso != null)
			return curso.agregarAlumno(Alumno);
		return null;
	}

	public Curso buscarCurso(Integer Curso) {
		for (Curso curso: Cursos)
			if (curso.getId() == curso.getId())
				return curso;
		return null;
	}
	
	public Curso buscarCurso(String descripcion) {
		for (Curso curso: Cursos)
			if (curso.getDescripcion().equals(curso.getDescripcion()))
				return curso;
		return null;
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
	public List<Curso> getCursos() {
		return Cursos;
	}
	public void setCursos(List<Curso> cursos) {
		Cursos = cursos;
	}
	/*BEAN*/
	public DocenteBean pasarBean() {
		DocenteBean docenteBean = new DocenteBean();
		docenteBean.setId(getId());
		docenteBean.setTipoDocumento(getTipoDocumento());
		docenteBean.setNroDocumento(getNroDocumento());
		docenteBean.setNombre(getNombre());
		docenteBean.setApellido(getApellido());
		
		for (Curso curso: Cursos){
			CursoBean cursoBean = curso.pasarBean();
			docenteBean.agregarCurso(cursoBean);
		}
			
		
		return docenteBean;
	}
	
}