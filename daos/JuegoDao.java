package daos;

import hibernate.HibernateUtil;
import negocio.Juego;

import org.hibernate.Query;
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

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from JuegoBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Juego.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
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
