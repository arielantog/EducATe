package daos;

import hibernate.HibernateUtil;
import negocio.Curso;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AlumnoBean;
import beans.CursoBean;

public class CursoDao {
	private static CursoDao instancia;
	private static SessionFactory sf;
	
	public static CursoDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new CursoDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from CursoBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Curso.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen cursos");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(CursoBean curso){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(curso);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(CursoBean curso) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(curso);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public Curso buscar(String descripcion) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from CursoBean where descripcion = ?");
		query.setString(0, descripcion);
		Curso curso = null;
		try{
			CursoBean cursoBean = (CursoBean) query.uniqueResult();
			curso = cursoBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return curso;
	}

	public boolean tengoAlumno(Integer curso, Integer alumno) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select b from CursoBean a join a.Alumnos b "
				+ " where a.Id = ? and b.Id = ? ");
		query.setInteger(0, curso);
		query.setInteger(1, alumno);
		try{
			AlumnoBean alumnoBean = (AlumnoBean) query.uniqueResult();
			if (alumnoBean != null){
				return true;
			}
		}catch (Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return false;
	}
}
