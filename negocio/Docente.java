package negocio;

import java.util.*;

import daos.CursoDao;
import daos.DocenteDao;
import beans.CursoBean;
import beans.DocenteBean;

public class Docente extends Persona {

	public Docente(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		Id = ID++;
		Cursos = new ArrayList<Curso>();
		activo = true;
		DocenteDao.getInstance().grabar(pasarBean());
		
	}
	public Docente(int Id, String tipoDocumento, Integer nroDocumento, String nombre, String apellido, boolean activo) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		this.Id = Id;
		this.activo = activo;
		Cursos = new ArrayList<Curso>();
	}

	private static Integer ID = 1;
	private Integer Id;
	private List<Curso> Cursos;
	private boolean activo;

	public Integer agregarCurso(String descripcion) {
		Curso curso = buscarCurso(descripcion);
		if (curso == null){
			Curso curso2 = new Curso(descripcion);
			Cursos.add(curso2);
			DocenteDao.getInstance().actualizar(pasarBean());
			return curso2.getId();
		}
		curso.activar(descripcion);
		Cursos.add(curso);
		DocenteDao.getInstance().actualizar(pasarBean());
		return curso.getId();
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
		return CursoDao.getInstance().buscar(getId(), Curso);
	}
	
	public Curso buscarCurso(String descripcion) {
		for (Curso curso: Cursos)
			if (curso.getDescripcion().equals(curso.getDescripcion()))
				return curso;
		return CursoDao.getInstance().buscar(descripcion);
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*BEAN*/
	public DocenteBean pasarBean() {
		DocenteBean docenteBean = new DocenteBean();
		docenteBean.setId(getId());
		docenteBean.setTipoDocumento(getTipoDocumento());
		docenteBean.setNroDocumento(getNroDocumento());
		docenteBean.setNombre(getNombre());
		docenteBean.setApellido(getApellido());
		docenteBean.setActivo(activo);
		for (Curso curso: Cursos){
			CursoBean cursoBean = curso.pasarBean();
			docenteBean.agregarCurso(cursoBean);
		}
			
		
		return docenteBean;
	}
	public void eliminar() {
		activo = false;
		DocenteDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String nombre, String apellido) {
		setNombre(nombre);
		setApellido(apellido);
		activo = true;
		DocenteDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String nombre, String apellido) {
		setNombre(nombre);
		setApellido(apellido);
		DocenteDao.getInstance().actualizar(pasarBean());
	}
	public void cursoQuitarAlumno(int curso, int alumno) {
		Curso curso2 = buscarCurso(curso);
		if (curso2 != null && curso2.isActivo()){
			curso2.quitarAlumno(alumno);
			DocenteDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El curso no existe");
		}
		
	}
	public void eliminarCurso(int curso) {
		Curso curso2 = buscarCurso(curso);
		if (curso2 != null && curso2.isActivo()){
			Cursos.remove(curso2);
			DocenteDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El curso no existe");
		}
	}
	public void modificarCurso(int curso, String descripcion) {
		Curso curso2 = buscarCurso(curso);
		if (curso2 != null && curso2.isActivo()){
			Curso curso3 = buscarCurso(descripcion);
			if (curso3 == null){
				curso2.modificar(descripcion);
				DocenteDao.getInstance().actualizar(pasarBean());
			}else{
				System.out.println("Ya existe un curso con el mismo nombre");
			}
		}else{
			System.out.println("El curso no existe");
		}
		
	}
	
}