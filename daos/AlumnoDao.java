package daos;

import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AlumnoBean;

public class AlumnoDao {
	private static AlumnoDao instancia;
	private static SessionFactory sf;
	
	public static AlumnoDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new AlumnoDao();
		}
		return instancia;
	}

	public void grabar(AlumnoBean alumno){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(alumno);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
