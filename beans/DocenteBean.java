package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name="docentes")
public class DocenteBean extends PersonaBean{
	@OneToMany
	@JoinColumn(name="docenteId")
	private List<CursoBean> Cursos;

	public DocenteBean() {
		Cursos = new ArrayList<CursoBean>();
	}
	/*GETTERS AND SETTERS*/
	public List<CursoBean> getCursos() {
		return Cursos;
	}
	public void setCursos(List<CursoBean> cursos) {
		Cursos = cursos;
	}
	public void agregarCurso(CursoBean cursoBean) {
		Cursos.add(cursoBean);
	}

}
