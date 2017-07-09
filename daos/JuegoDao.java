package daos;

import java.util.ArrayList;
import java.util.List;

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
			Query query = session.createQuery("select MAX(a.id) from JuegoBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Juego.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Juegos");
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

	public Juego buscar(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		JuegoBean juegoBean = (JuegoBean) session.get(JuegoBean.class, id);
		Juego juego = juegoBean.pasarNegocio();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return juego;
	}
	
	public Juego buscar(String nombre) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from JuegoBean where nombre = ?");
		query.setString(0, nombre);
		Juego juego = null;
		try{
			JuegoBean juegoBean = (JuegoBean) query.uniqueResult();
			juego = juegoBean.pasarNegocio();
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return juego;
	}

	public int cantidadJuegos() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select COUNT(a.id) from JuegoBean a ");
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

	public List<Juego> cargarJuegos() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from JuegoBean");
		List<Juego> juegos = new ArrayList<Juego>();
		try{
			@SuppressWarnings("unchecked")
			List<JuegoBean> juegosBean = (List<JuegoBean>) query.list();
			for (JuegoBean juegoBean: juegosBean){
				Juego juego = juegoBean.pasarNegocio();
				juegos.add(juego);
			}
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return juegos;
		
	}

	public boolean tengoLeccion(int juego, int leccion) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select a from JuegoBean a join a.lecciones b "
				+ " where a.id = ? and b.id = ? ");
		query.setInteger(0, juego);
		query.setInteger(1, leccion);
		boolean existe = false;
		try{
			 JuegoBean juegoBean = (JuegoBean) query.uniqueResult();
			 if (juegoBean != null)
				 existe = true;
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return existe;
	}
}
