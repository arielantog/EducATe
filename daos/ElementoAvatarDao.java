package daos;

import hibernate.HibernateUtil;
import negocio.ElementoAvatar;

import org.hibernate.Query;
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

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from ElementoAvatarBean a ");
			int variableGlobal = (int) query.uniqueResult();
			ElementoAvatar.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(ElementoAvatarBean elementoAvatar){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(elementoAvatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void actualizar(ElementoAvatarBean elementoAvatar){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(elementoAvatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
