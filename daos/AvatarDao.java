package daos;

import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AvatarBean;

public class AvatarDao {
	private static AvatarDao instancia;
	private static SessionFactory sf;
	
	public static AvatarDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new AvatarDao();
		}
		return instancia;
	}

	public void grabar(AvatarBean avatar){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(avatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(AvatarBean avatar) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(avatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}
}
