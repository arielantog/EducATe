package daos;

import hibernate.HibernateUtil;
import negocio.Avatar;
import negocio.ElementoAvatar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AvatarBean;
import beans.ElementoAvatarBean;

public class AvatarDao {
	private static AvatarDao instancia;
	private static SessionFactory sf;
	
	public static AvatarDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new AvatarDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from AvatarBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Avatar.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Avatares");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(AvatarBean avatar){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(avatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(AvatarBean avatar) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(avatar);
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}

	public ElementoAvatar buscarElemento(Integer avatar, String tipo) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select b from AvatarBean a join a.ElementosAvatar b "
				+ " where a.Id = ? and b.Tipo = ? ");
		query.setInteger(0, avatar);
		query.setString(1, tipo);
		ElementoAvatar elementoAvatar = null;
		try{
			ElementoAvatarBean elementoAvatarBean = (ElementoAvatarBean) query.uniqueResult();
			if (elementoAvatarBean != null)
				elementoAvatar = elementoAvatarBean.pasarNegocio();
		}catch (Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return elementoAvatar;
	}
}
