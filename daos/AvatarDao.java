package daos;

import hibernate.HibernateUtil;
import negocio.Avatar;

import org.hibernate.Query;
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

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.id) from AvatarBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Avatar.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Avatares");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
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
