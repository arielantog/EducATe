package daos;

import hibernate.HibernateUtil;
import negocio.Alumno;
import negocio.Ensenianza;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.EnsenianzaBean;

public class EnsenianzaDao {
	private static EnsenianzaDao instancia;
	private static SessionFactory sf;
	
	public static EnsenianzaDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new EnsenianzaDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.id) from EnsenianzaBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Alumno.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Ensenianzas");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(EnsenianzaBean ensenianza){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(ensenianza);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void actualizar(EnsenianzaBean ensenianza){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(ensenianza);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void eliminar(EnsenianzaBean ensenianza)
	{
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(ensenianza);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public Ensenianza buscar(int Id) {
		Session session = sf.openSession();
		session.beginTransaction();
		EnsenianzaBean ensenianzaBean = (EnsenianzaBean) session.get(EnsenianzaBean.class, Id);
		Ensenianza ensenianza = ensenianzaBean.pasarNegocio();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return ensenianza;
	}
	


}
