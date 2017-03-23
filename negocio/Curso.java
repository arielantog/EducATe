package negocio;

import java.util.*;

import daos.CursoDao;
import beans.AlumnoBean;
import beans.CursoBean;

public class Curso {

	public Curso(String descripcion) {
		Id = ID++;
		Descripcion = descripcion;
		Alumnos = new ArrayList<Alumno>();
		CursoDao.getInstance().grabar(pasarBean());
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	private List<Alumno> Alumnos;

	public Integer agregarAlumno(Alumno alumno) {
		if (!tengoAlumno(alumno)){
			Alumnos.add(alumno);
			CursoDao.getInstance().actualizar(pasarBean());
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
	/*BEAN*/
	public CursoBean pasarBean() {
		CursoBean cursoBean = new CursoBean();
		cursoBean.setId(getId());
		cursoBean.setDescripcion(getDescripcion());
		
		for (Alumno alumno: Alumnos){
			AlumnoBean alumnoBean = alumno.pasarBean();
			cursoBean.agregarAlumno(alumnoBean);
		}
		return cursoBean;
	}
}