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
	private int id;
	@OneToMany
	@JoinColumn(name="docenteId")
	private List<CursoBean> cursos;
	private boolean activo;

	public DocenteBean() {
		cursos = new ArrayList<CursoBean>();
	}
	/*GETTERS AND SETTERS*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<CursoBean> getCursos() {
		return cursos;
	}
	public void setCursos(List<CursoBean> cursos) {
		this.cursos = cursos;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public Docente pasarNegocio() {
		 Docente docente = new Docente(id, getTipoDocumento(), getNroDocumento(), getNombre(), getApellido(), getPassword(), getMail(), isActivo());
		 for (CursoBean cursoBean: cursos){
			 docente.agregarCurso(cursoBean.pasarNegocio());
		 }
		 return docente;
	}
	public void agregarCurso(CursoBean cursoBean) {
		cursos.add(cursoBean);
	}
	
}
