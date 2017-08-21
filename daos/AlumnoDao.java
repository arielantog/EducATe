package daos;

import java.util.ArrayList;
import java.util.List;

import hibernate.HibernateUtil;
import negocio.Alumno;
import negocio.Leccion;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AlumnoBean;
import beans.LeccionBean;
import dto.AlumnoDTO;

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
			Query query = session.createQuery("select MAX(a.id) from AlumnoBean a ");
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
	
	public Alumno buscar(String usuario) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from AlumnoBean where usuario = ? " );
		query.setString(0, usuario);
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

	@SuppressWarnings("unchecked")
	public List<Alumno> cargarAlumnos() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from AlumnoBean where activo = 1 ");
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try{
			List<AlumnoBean> alumnosBean = new ArrayList<AlumnoBean>();
			alumnosBean = query.list();
			for(AlumnoBean alumnoBean: alumnosBean){
				Alumno alumno = alumnoBean.pasarNegocio();
				alumnos.add(alumno);
			}
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return alumnos;
	}

	public AlumnoDTO loginAlumno(String usuario, String password) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from AlumnoBean where 	usuario  = ? "
															+ "and	password = ?");
		query.setString(0, usuario);
		query.setString(1, password);
		AlumnoDTO alumno = null;
		try{
			AlumnoBean alumnoBean = (AlumnoBean) query.uniqueResult();
			alumno = alumnoBean.pasarDTO();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return alumno;
	}

	public Leccion buscarLeccionMasAntigua(int alumno) {
		Session session = sf.openSession();
		session.beginTransaction();
		Leccion leccion = null;
		try{
			Query query = session.createQuery("select c from AlumnoBean a "
					+ " join a.ensenianzas b "
					+ " join b.leccion c "
					+ " where a.id = ? "
					+ " and b.fechaUltRepaso = (select MIN(e.fechaUltRepaso) "
					+ "								from AlumnoBean d "
					+ "								join d.ensenianzas e"
					+ "								where d.id = ? )");
			query.setInteger(0, alumno);
			query.setInteger(1, alumno);
			
		
			LeccionBean leccionBean = (LeccionBean) query.uniqueResult();
			leccion = leccionBean.pasarNegocio();
		}catch(Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return leccion;
	}
}
