package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name="cursos")
public class CursoBean {
	@Id
	@Column(name="cursoId")
	private Integer Id;
	private String Descripcion;	
	@ManyToMany
	@JoinTable(name="curso_alumno",
				joinColumns			= @JoinColumn(name="cursoID"),
				inverseJoinColumns	= @JoinColumn(name="alumnoId"))
	private List<AlumnoBean> Alumnos;
	
	/*GETTERS Y SETTERS*/
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
	public List<AlumnoBean> getAlumnos() {
		return Alumnos;
	}
	public void setAlumnos(List<AlumnoBean> alumnos) {
		Alumnos = alumnos;
	}

}
