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
		activo = true;
		CursoDao.getInstance().grabar(pasarBean());
	}
	public Curso(int id, String descripcion, boolean activo) {
		Id = id;
		Descripcion = descripcion;
		Alumnos = new ArrayList<Alumno>();
		this.activo = activo;
	}

	private static Integer ID = 1;
	private Integer Id;
	private String Descripcion;
	private List<Alumno> Alumnos;
	private boolean activo;

	public Integer agregarAlumno(Alumno alumno) {
		if (!tengoAlumno(alumno)){
			Alumnos.add(alumno);
			CursoDao.getInstance().actualizar(pasarBean());
			return alumno.getId();
		}
		System.out.println("El alumno ya está agregado en el curso");
		return 0;
	}
	public Alumno buscarAlumno(int alumno) {
		for (Alumno alumno2: Alumnos)
			if (alumno2.getId() == alumno)
				return alumno2;
		return CursoDao.getInstance().buscarAlumno(getId(), alumno);
	}

	public boolean tengoAlumno (Alumno alumno){
		for (Alumno alumno2: Alumnos)
			if (alumno2.getId() == alumno.getId())
				return true;
		return CursoDao.getInstance().tengoAlumno(getId(), alumno.getId());
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*BEAN*/
	public CursoBean pasarBean() {
		CursoBean cursoBean = new CursoBean();
		cursoBean.setId(getId());
		cursoBean.setDescripcion(getDescripcion());
		cursoBean.setActivo(activo);
		for (Alumno alumno: Alumnos){
			AlumnoBean alumnoBean = alumno.pasarBean();
			cursoBean.agregarAlumno(alumnoBean);
		}
		return cursoBean;
	}
	public void agregarAlumno(Alumno alumno, boolean b) {
		Alumnos.add(alumno);
	}
	public void quitarAlumno(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null){
			Alumnos.remove(alumno2);
			CursoDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El alumno no existe");
		}
		
	}
	public void eliminar() {
		activo = false;
		CursoDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String descripcion) {
		setDescripcion(descripcion);
		activo = true;
		CursoDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String descripcion) {
		setDescripcion(descripcion);
		CursoDao.getInstance().actualizar(pasarBean());
	}
	
}