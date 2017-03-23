package daos;

import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.JuegoBean;

public class JuegoDao {
	private static JuegoDao instancia;
	private static SessionFactory sf;
	
	public static JuegoDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new JuegoDao();
		}
		return instancia;
	}

	public void grabar(JuegoBean juego){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(juego);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(JuegoBean juego) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(juego);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
