package negocio;

import daos.HistorialAlumnoDao;
import beans.HistorialAlumnoBean;

public class HistorialAlumno {
		
	public HistorialAlumno(Alumno alumno, Leccion leccion, boolean resultado) {
		this.Id = ID++;
		this.alumno = alumno;
		this.leccion = leccion;
		this.resultado = resultado;
		HistorialAlumnoDao.getInstance().grabar(pasarBean());
	}
	public HistorialAlumno(int id, Alumno alumno, Leccion leccion, boolean resultado) {
		Id = id;
		this.alumno = alumno;
		this.leccion = leccion;
		this.resultado = resultado;
	}
	private static int ID = 1;
	private int Id;
	private Alumno alumno;
	private Leccion leccion;
	private boolean resultado;
	
	/*GETTERS Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Leccion getLeccion() {
		return leccion;
	}
	public void setLeccion(Leccion leccion) {
		this.leccion = leccion;
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	/*BEAN*/
	public HistorialAlumnoBean pasarBean(){
		HistorialAlumnoBean historialAlumnoBean = new HistorialAlumnoBean();
		historialAlumnoBean.setId(Id);
		historialAlumnoBean.setAlumno(alumno.pasarBean());
		historialAlumnoBean.setLeccion(leccion.pasarBean());
		historialAlumnoBean.setResultado(resultado);
		return historialAlumnoBean;
	}
}
