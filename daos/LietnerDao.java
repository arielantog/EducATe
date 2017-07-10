package daos;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import beans.LietnerBean;

public class LietnerDao {
	private static LietnerDao instancia;
	private static SessionFactory sf;
	
	public static LietnerDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new LietnerDao();
		}
		return instancia;
	}

	public boolean existe(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			LietnerBean lietnerBean = (LietnerBean) session.get(LietnerBean.class, id);
			if (lietnerBean != null)
				return true;
		}catch (Exception e){
			return false;
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return false;
	}

	public void grabar(LietnerBean lietner){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(lietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(LietnerBean lietner) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(lietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}


}
