package daos;

import hibernate.HibernateUtil;
import negocio.Tema;

import org.hibernate.Query;
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

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from TemaBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Tema.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
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

	public Tema buscarTema(String descipcion) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TemaBean t where t.getDescripcion() = ?");
		query.setString(0, descipcion);
		Tema tema = null;
		try{
			TemaBean temaBean = (TemaBean) query.uniqueResult();
			tema = temaBean.pasarNegocio();
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return tema;
	}
	
	
}