package negocio;

import java.util.*;

public class Docente extends Persona {

	public Docente(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		super.setId(super.getID()); 
		super.setTipoDocumento(tipoDocumento);
		super.setNroDocumento(nroDocumento);
		super.setNombre(nombre);
		super.setApellido(apellido);
		Cursos = new HashSet<Curso>();
	}

	private Collection<Curso> Cursos;

	public Integer agregarCurso(String descripcion) {
		Curso curso = buscarCurso(descripcion);
		if (curso == null){
			Curso curso2 = new Curso(descripcion);
			Cursos.add(curso2);
			return curso2.getId();
		}
		return 0;
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
	public Integer getId() {
		return getId();
	}
	public void setId(Integer id) {
		setId(id);
	}
	public String getTipoDocumento() {
		return getTipoDocumento();
	}
	public void setTipoDocumento(String tipoDocumento) {
		setTipoDocumento(tipoDocumento);
	}
	public Integer getNroDocumento() {
		return getNroDocumento();
	}
	public void setNroDocumento(Integer nroDocumento) {
		setNroDocumento(nroDocumento);
	}
	public String getNombre() {
		return getNombre();
	}
	public void setNombre(String nombre) {
		setNombre(nombre);
	}
	public String getApellido() {
		return getApellido();
	}
	public void setApellido(String apellido) {
		setApellido(apellido);
	}
	public Collection<Curso> getCursos() {
		return Cursos;
	}
	public void setCursos(Collection<Curso> cursos) {
		Cursos = cursos;
	}
}