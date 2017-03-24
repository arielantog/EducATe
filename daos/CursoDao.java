package daos;

import hibernate.HibernateUtil;
import negocio.Curso;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
			System.out.println(e);
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
}
