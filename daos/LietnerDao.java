package daos;
import java.util.List;

import hibernate.HibernateUtil;


import org.hibernate.Query;
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

	public Integer[] cargarValores() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from LietnerBean");
		Integer[] valores = new Integer[10];
		try{
			@SuppressWarnings("unchecked")
			List<LietnerBean> lietnersBean = (List<LietnerBean>) query.list();
			int i = 0;
			for (LietnerBean lietnerBean: lietnersBean){
				valores[i]=lietnerBean.getValor();
				i++;
			}
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return valores;
		
	}

	public void grabar(LietnerBean lietner){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(lietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void actualizar(LietnerBean lietner){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(lietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
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

	public void eliminar(LietnerBean lietner) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(lietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
