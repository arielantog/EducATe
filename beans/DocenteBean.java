package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name="docentes")
public class DocenteBean extends PersonaBean{
	@OneToMany
	@JoinColumn(name="docenteID")
	private List<CursoBean> Cursos;

	/*GETTERS AND SETTERS*/
	public List<CursoBean> getCursos() {
		return Cursos;
	}

	public void setCursos(List<CursoBean> cursos) {
		Cursos = cursos;
	}

}
