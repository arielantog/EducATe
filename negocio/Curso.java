package negocio;

import java.util.*;

import daos.CursoDao;
import beans.AlumnoBean;
import beans.CursoBean;

public class Curso {

	public Curso(String descripcion) {
		id = ID++;
		this.descripcion = descripcion;
		alumnos = new ArrayList<Alumno>();
		activo = true;
		CursoDao.getInstance().grabar(pasarBean());
	}
	public Curso(int id, String descripcion, boolean activo) {
		this.id = id;
		this.descripcion = descripcion;
		alumnos = new ArrayList<Alumno>();
		this.activo = activo;
	}

	private static int ID = 1;
	private int id;
	private String descripcion;
	private List<Alumno> alumnos;
	private boolean activo;

	public int agregarAlumno(Alumno alumno) {
		if (!tengoAlumno(alumno)){
			alumnos.add(alumno);
			CursoDao.getInstance().actualizar(pasarBean());
			return alumno.getId();
		}
		System.out.println("El alumno ya está agregado en el curso");
		return 0;
	}
	public Alumno buscarAlumno(int alumno) {
		for (Alumno alumno2: alumnos)
			if (alumno2.getId() == alumno)
				return alumno2;
		return CursoDao.getInstance().buscarAlumno(getId(), alumno);
	}

	public boolean tengoAlumno (Alumno alumno){
		for (Alumno alumno2: alumnos)
			if (alumno2.getId() == alumno.getId())
				return true;
		return CursoDao.getInstance().tengoAlumno(getId(), alumno.getId());
	}
	
	/*GETTERS Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		for (Alumno alumno: alumnos){
			AlumnoBean alumnoBean = alumno.pasarBean();
			cursoBean.agregarAlumno(alumnoBean);
		}
		return cursoBean;
	}
	public void agregarAlumno(Alumno alumno, boolean b) {
		alumnos.add(alumno);
	}
	public int quitarAlumno(int alumno) {
		Alumno alumno2 = buscarAlumno(alumno);
		if (alumno2 != null){
			alumnos.remove(alumno2);
			CursoDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El alumno no existe");
		}
		return 0;
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