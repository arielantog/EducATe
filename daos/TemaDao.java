package daos;

import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.TemaBean;


public class TemaDao {
	private static TemaDao instancia;
	private static SessionFactory sf;
	
	public static TemaDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new TemaDao();
		}
		return instancia;
	}

	public void grabar(TemaBean tema){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(tema);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(TemaBean tema) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(tema);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}
}