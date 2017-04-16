package daos;

import hibernate.HibernateUtil;
import negocio.Alumno;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;




import beans.AlumnoBean;

public class AlumnoDao {
	private static AlumnoDao instancia;
	private static SessionFactory sf;
	
	public static AlumnoDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new AlumnoDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from AlumnoBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Alumno.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Alumnos");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(AlumnoBean alumno){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(alumno);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void actualizar(AlumnoBean alumno){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(alumno);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void eliminar(AlumnoBean alumno)
	{
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(alumno);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public Alumno buscar(int Id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Alumno  alumno = null;
		try{
			AlumnoBean alumnoBean = (AlumnoBean) session.get(AlumnoBean.class, Id);
			alumno = alumnoBean.pasarNegocio();
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return alumno;
	}
	public Alumno buscar(String tipoDocumento, int nroDocumento) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from AlumnoBean where tipoDocumento = ? "
				+ "and nroDocumento = ? ");
		query.setString(0, tipoDocumento);
		query.setInteger(1, nroDocumento);
		Alumno alumno = null;
		try{
			AlumnoBean alumnoBean = (AlumnoBean) query.uniqueResult();
			alumno = alumnoBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return alumno;
	}

}
