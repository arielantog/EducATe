package daos;

import hibernate.HibernateUtil;
import negocio.Leccion;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.LeccionBean;

public class LeccionDao {
	private static LeccionDao instancia;
	private static SessionFactory sf;
	
	public static LeccionDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new LeccionDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from LeccionBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Leccion.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(LeccionBean leccion){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(leccion);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void actualizar(LeccionBean leccion){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(leccion);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
