package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dto.CursoDTO;
import negocio.Curso;

@Entity
@Table (name="cursos")
public class CursoBean {
	@Id
	@Column(name="cursoId")
	private int id;
	private String descripcion;	
	@ManyToMany
	@JoinTable(name="curso_alumno",
				joinColumns			= @JoinColumn(name="cursoID"),
				inverseJoinColumns	= @JoinColumn(name="alumnoId"))
	private List<AlumnoBean> alumnos;
	private boolean activo;
	
	
	public CursoBean() {
		alumnos = new ArrayList<AlumnoBean>();
	}
	/*GETTERS Y SETTERS*/
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
	public List<AlumnoBean> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<AlumnoBean> alumnos) {
		this.alumnos = alumnos;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public Curso pasarNegocio() {
		Curso curso = new Curso(id, descripcion,activo);
		for (AlumnoBean alumnoBean: alumnos){
			curso.agregarAlumno(alumnoBean.pasarNegocio(),true);
		}
		return curso;
	}
	public void agregarAlumno(AlumnoBean alumnoBean) {
		alumnos.add(alumnoBean);
	}
	
	/*DTO*/
	public CursoDTO pasarDTO() {
		CursoDTO curso = new CursoDTO(id, descripcion,activo);
		for (AlumnoBean alumnoBean: alumnos){
			curso.agregarAlumnoDTO(alumnoBean.pasarDTO(),true);
		}
		return curso;
	}
	public void agregarAlumnoDTO(AlumnoBean alumnoBean) {
		alumnos.add(alumnoBean);
	}

}
