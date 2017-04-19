package daos;

import hibernate.HibernateUtil;
import negocio.Curso;
import negocio.Docente;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AlumnoBean;
import beans.CursoBean;
import beans.DocenteBean;


public class DocenteDao {
	private static DocenteDao instancia;
	private static SessionFactory sf;
	
	public static DocenteDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new DocenteDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.id) from DocenteBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Docente.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen docentes");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(DocenteBean docente){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(docente);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public void actualizar(DocenteBean docente){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(docente);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void eliminar(AlumnoBean alumno)
	{
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(alumno);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public Docente buscar(int Id) {
		Session session = sf.openSession();
		session.beginTransaction();
		DocenteBean docenteBean = (DocenteBean) session.get(DocenteBean.class, Id);
		Docente docente = docenteBean.pasarNegocio();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return docente;
	}
	public Docente buscar(String tipoDocumento, int nroDocumento) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from DocenteBean where tipoDocumento = ? "
															+ "and nroDocumento = ? ");
		query.setString(0, tipoDocumento);
		query.setInteger(1, nroDocumento);
		Docente docente = null;
		try{
			DocenteBean docenteBean = (DocenteBean) query.uniqueResult();
			docente = docenteBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return docente;
	}

	public Curso buscarCurso(int docente, int curso) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select b from DocenteBean a join a.cursos b "
				+ " where a.id = ? and b.id = ?");
		query.setInteger(0, docente);
		query.setInteger(1, curso);
		Curso curso2 = null;
		try{
			CursoBean cursoBean = (CursoBean) query.uniqueResult();
			curso2 = cursoBean.pasarNegocio();
		}catch (Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return curso2;
		
	}

}
