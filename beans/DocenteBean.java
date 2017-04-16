package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import negocio.Docente;

@Entity
@Table (name="docentes")
public class DocenteBean extends PersonaBean{
	@Id
	@Column(name="docenteId")
	private int Id;
	@OneToMany
	@JoinColumn(name="docenteId")
	private List<CursoBean> Cursos;
	private boolean activo;

	public DocenteBean() {
		Cursos = new ArrayList<CursoBean>();
	}
	/*GETTERS AND SETTERS*/
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public List<CursoBean> getCursos() {
		return Cursos;
	}
	public void setCursos(List<CursoBean> cursos) {
		Cursos = cursos;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public Docente pasarNegocio() {
		 Docente docente = new Docente(Id, getTipoDocumento(), getNroDocumento(), getNombre(), getApellido(), isActivo());
		 for (CursoBean cursoBean: Cursos){
			 docente.agregarCurso(cursoBean.pasarNegocio());
		 }
		 return docente;
	}
	public void agregarCurso(CursoBean cursoBean) {
		Cursos.add(cursoBean);
	}
}
