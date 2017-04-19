package daos;

import hibernate.HibernateUtil;
import negocio.Leccion;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.LeccionBean;

public class LeccionDao {
	private static LeccionDao instancia;
	private static SessionFactory sf;
	
	public static LeccionDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new LeccionDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.id) from LeccionBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Leccion.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Lecciones");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(LeccionBean leccion){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(leccion);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void actualizar(LeccionBean leccion){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(leccion);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public Leccion buscar(int tema, String descripcion) {
		Session session = sf.openSession();
		session.beginTransaction();
		Leccion leccion = null;
		try{
			Query query = session.createQuery("SELECT a FROM TemaBean a JOIN a.lecciones b WHERE a.id = ? "
				+ "AND b.descripcion = ? ");
			query.setInteger(0, tema);
			query.setString(1, descripcion);
			LeccionBean leccionBean = (LeccionBean) query.uniqueResult();
			leccion = leccionBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return leccion;
	}

	public Leccion buscar(int tema, int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT a FROM TemaBean a JOIN a.lecciones b WHERE a.id = ? "
				+ "AND b.id = ? ");
		query.setInteger(0, tema);
		query.setInteger(1, id);
		Leccion leccion = null;
		try{
			LeccionBean leccionBean = (LeccionBean) query.uniqueResult();
			leccion = leccionBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return leccion;
	}

	public Leccion buscar(String descripcion) {
		Session session = sf.openSession();
		session.beginTransaction();
		Leccion leccion = null;
		try{
			Query query = session.createQuery("SELECT a FROM LeccionBean a WHERE a.descripcion = ? ");
			query.setString(0, descripcion);
			LeccionBean leccionBean = (LeccionBean) query.uniqueResult();
			leccion = leccionBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return leccion;
	}
}
