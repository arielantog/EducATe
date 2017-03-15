package negocio;

import java.util.*;

public class Curso {

	public Curso(String descripcion) {
		Id = ID++;
		Descripcion = descripcion;
		Alumnos = new HashSet<Alumno>();
	}

	private static Integer ID = 0;
	private Integer Id;
	private String Descripcion;
	private Collection<Alumno> Alumnos;

	public Integer agregarAlumno(Alumno alumno) {
		if (tengoAlumno(alumno)){
			Alumnos.add(alumno);
			return alumno.getId();
		}
		return 0;
	}

	public boolean tengoAlumno (Alumno alumno){
		for (Alumno alumno2: Alumnos)
			if (alumno2.getId() == alumno.getId())
				return true;
		return false;
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
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
}