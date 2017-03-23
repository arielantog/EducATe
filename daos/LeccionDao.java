package daos;

import hibernate.HibernateUtil;

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

	public void grabar(LeccionBean leccion){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(leccion);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
