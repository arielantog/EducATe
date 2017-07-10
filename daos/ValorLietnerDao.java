package daos;
import java.util.ArrayList;
import java.util.List;
import hibernate.HibernateUtil;
import negocio.ValorLietner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import beans.ValorLietnerBean;

public class ValorLietnerDao {
	private static ValorLietnerDao instancia;
	private static SessionFactory sf;
	
	public static ValorLietnerDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new ValorLietnerDao();
		}
		return instancia;
	}

	public void grabar(ValorLietnerBean valorLietner){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(valorLietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void actualizar(ValorLietnerBean valorLietner){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(valorLietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public boolean existe(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			ValorLietnerBean valorLietnerBean = (ValorLietnerBean) session.get(ValorLietnerBean.class, id);
			if (valorLietnerBean != null)
				return true;
		}catch (Exception e){
			return false;
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return false;
	}

	public void eliminar(ValorLietnerBean valorLietner) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(valorLietner);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<ValorLietner> cargarValores() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ValorLietnerBean");
		List<ValorLietner> valoresLietner = new ArrayList<ValorLietner>();
		try{
			List<ValorLietnerBean> valoresLietnerBean = new ArrayList<ValorLietnerBean>();
			valoresLietnerBean = query.list();
			for(ValorLietnerBean valorLietnerBean: valoresLietnerBean){
				ValorLietner valorLietner = valorLietnerBean.pasarNegocio();
				valoresLietner.add(valorLietner);
			}
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return valoresLietner;
	}

}