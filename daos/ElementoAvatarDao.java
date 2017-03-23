package daos;

import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.ElementoAvatarBean;

public class ElementoAvatarDao {
	private static ElementoAvatarDao instancia;
	private static SessionFactory sf;
	
	public static ElementoAvatarDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new ElementoAvatarDao();
		}
		return instancia;
	}

	public void grabar(ElementoAvatarBean elementoAvatar){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(elementoAvatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
