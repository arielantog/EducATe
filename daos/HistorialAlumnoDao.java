package daos;

import hibernate.HibernateUtil;
import negocio.HistorialAlumno;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.HistorialAlumnoBean;

public class HistorialAlumnoDao {
	private static HistorialAlumnoDao instancia;
	private static SessionFactory sf;
	
	public static HistorialAlumnoDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HistorialAlumnoDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.id) from HistorialAlumnoBean a ");
			int variableGlobal = (int) query.uniqueResult();
			HistorialAlumno.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existe ningún registro de historial de alumnos");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(HistorialAlumnoBean historialAlumno){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(historialAlumno);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
