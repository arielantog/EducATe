package daos;

import hibernate.HibernateUtil;
import negocio.Leccion;
import negocio.Tema;

import java.util.ArrayList;
import java.util.List;

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
			Query query = session.createQuery("select MAX(a.id) from TemaBean a ");
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
		Query query = session.createQuery("SELECT b FROM TemaBean a JOIN a.lecciones b WHERE b.id = ?");
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

	@SuppressWarnings("unchecked")
	//public List<TemaDTO> listarTemas() {
	public List<Tema> listarTemas() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM TemaBean WHERE activo = 1");
		//List<TemaDTO> temas = new ArrayList<TemaDTO>();
		List<Tema> temas = new ArrayList<Tema>();
		try{
			List<TemaBean> temasBean = new ArrayList<TemaBean>();
			temasBean = query.list();
			for (TemaBean temaBean : temasBean){
				//TemaDTO tema = temaBean.pasarDTO();
				Tema tema = temaBean.pasarNegocio();
				temas.add(tema);
			}
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return temas;
	}

	public int cantidadTemas() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select COUNT(a.id) from TemaBean a ");
		int cantidad = 0;
		try{
			long cantidad2 = (long) query.uniqueResult();
			cantidad = (int) cantidad2;
		}catch(Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return cantidad;
	}

	public List<Tema> cargarTemas() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TemaBean");
		List<Tema> temas = new ArrayList<Tema>();
		try{
			@SuppressWarnings("unchecked")
			List<TemaBean> temasBean = (List<TemaBean>) query.list();
			for (TemaBean temaBean: temasBean){
				Tema tema = temaBean.pasarNegocio();
				temas.add(tema);
			}
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return temas;
	}
	
	
}