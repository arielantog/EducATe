package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.HistorialAlumno;
@Entity
@Table(name="historialAlumnos") 
public class HistorialAlumnoBean {
	@Id
	@Column(name="historialAlumnoId")
	private int Id;
	@OneToOne
	@JoinColumn(name="alumnoId")
	private AlumnoBean alumno;
	@OneToOne
	@JoinColumn(name="leccionId")
	private LeccionBean leccion;
	private boolean resultado;
	
	/*GETTERS Y SETTERS*/
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public AlumnoBean getAlumno() {
		return alumno;
	}
	public void setAlumno(AlumnoBean alumno) {
		this.alumno = alumno;
	}
	public LeccionBean getLeccion() {
		return leccion;
	}
	public void setLeccion(LeccionBean leccion) {
		this.leccion = leccion;
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	/*NEGOCIO*/
	public HistorialAlumno pasarNegocio(){
		return new HistorialAlumno(Id,alumno.pasarNegocio(), leccion.pasarNegocio(), resultado);
	}
}
