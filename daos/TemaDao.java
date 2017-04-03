package daos;

import hibernate.HibernateUtil;
import negocio.Leccion;
import negocio.Tema;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.LeccionBean;
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
			System.out.println("No existen temas");
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

	public Tema buscar(int Id) {
		Session session = sf.openSession();
		session.beginTransaction();
		TemaBean temaBean = (TemaBean) session.get(TemaBean.class, Id);
		Tema tema = temaBean.pasarNegocio();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return tema;
	}
	
	public Tema buscar(String descipcion) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TemaBean where descripcion = ?");
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

	public Leccion buscarConLeccion(int leccion) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT b FROM TemaBean a JOIN a.lecciones b WHERE b.Id = ?");
		query.setInteger(0, leccion);
		Leccion leccion2 = null;
		try{
			LeccionBean leccionBean = (LeccionBean) query.uniqueResult();
			leccion2 = leccionBean.pasarNegocio();
		}catch(Exception e){
			System.out.print(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return leccion2;
	}
	
	
}