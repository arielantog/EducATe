package daos;

import hibernate.HibernateUtil;
import negocio.Alimento;
import negocio.TipoAvatar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AlimentoBean;
import beans.TipoAvatarBean;

public class TipoAvatarDao {
	private static TipoAvatarDao instancia;
	private static SessionFactory sf;
	
	public static TipoAvatarDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new TipoAvatarDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from TipoAvatarBean a ");
			int variableGlobal = (int) query.uniqueResult();
			TipoAvatar.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Tipos de Avatares");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(TipoAvatarBean tipoAvatar){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(tipoAvatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(TipoAvatarBean tipoAvatar) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(tipoAvatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public TipoAvatar buscar(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		TipoAvatarBean tipoAvatarBean = (TipoAvatarBean) session.get(TipoAvatarBean.class, id);
		TipoAvatar tipoAvatar = null;
		try{
			tipoAvatar = tipoAvatarBean.pasarNegocio();
			session.flush();
			session.getTransaction().commit();
		}catch(Exception e){
			System.out.println("No existen Tipos de Avatares");
		}
		session.close();
		return tipoAvatar;
	}
	
	public TipoAvatar buscar(String nombre) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from TipoAvatarBean a where a.nombre = ?");
		query.setString(0, nombre);
		TipoAvatar tipoAvatar = null;
		try{
			TipoAvatarBean tipoAvatarBean = (TipoAvatarBean) query.uniqueResult();
			tipoAvatar = tipoAvatarBean.pasarNegocio();
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return tipoAvatar;
	}

	public boolean tengoAlimento(int tipoAvatar, int alimento) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select a from TipoAvatarBean a join a.alimentos b "
				+ " where a.Id = ? and b.Id = ?");
		query.setInteger(0, tipoAvatar);
		query.setInteger(1, alimento);
		boolean existe = false;
		try{
			 TipoAvatarBean tipoAvatarBean = (TipoAvatarBean) query.uniqueResult();
			 if (tipoAvatarBean != null)
				 existe = true;
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return existe;
	}

	public Alimento buscarAlimento(int tipoAvatar, int alimento) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select b from TipoAvatarBean a join a.alimentos b "
				+ " where a.Id = ? and b.Id = ?");
		query.setInteger(0, tipoAvatar);
		query.setInteger(1, alimento);
		try{
			 AlimentoBean alimentoBean = (AlimentoBean) query.uniqueResult();
			 return alimentoBean.pasarNegocio();
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return null;
	}
}
