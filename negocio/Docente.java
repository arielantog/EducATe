package negocio;

import java.util.*;

public class Docente extends Persona {

	public Docente(String tipoDocumento, Integer nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		super.setId(super.getID());
		Cursos = new ArrayList<Curso>();
	}

	private List<Curso> Cursos;

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
	public List<Curso> getCursos() {
		return Cursos;
	}
	public void setCursos(List<Curso> cursos) {
		Cursos = cursos;
	}
}