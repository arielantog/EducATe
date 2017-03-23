package daos;

import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.DocenteBean;


public class DocenteDao {
	private static DocenteDao instancia;
	private static SessionFactory sf;
	
	public static DocenteDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new DocenteDao();
		}
		return instancia;
	}

	public void grabar(DocenteBean docente){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(docente);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public void actualizar(DocenteBean docente){
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(docente);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

/*	public void cargarVariableGlobal(Integer oficinaVentaID) {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(c.clienteID.nroCliente) from ClienteBean c "
											+ "where c.clienteID.nroOficina = ?");
			query.setInteger(0, oficinaVentaID);
			int variableGlobal = (int) query.uniqueResult();
			Cliente.setNumeroCliente(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println(e);
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		
	}

	public Cliente buscarCliente(int nroOficinaVenta, int nroCliente) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ClienteBean c where c.clienteID.nroOficina = ? "
															+ "and c.clienteID.nroCliente = ? ");
		query.setInteger(0, nroOficinaVenta);
		query.setInteger(1, nroCliente);
		Cliente cliente = null;
		try{
			ClienteBean clienteBean = (ClienteBean) query.uniqueResult();
			cliente = clienteBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return cliente;
	}
	
	public void eliminarCliente(ClienteBean cliente)
	{
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(cliente);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public void modificarCliente(ClienteBean cliente)
	{
		Session session = sf.openSession();
		session.beginTransaction();
		//session.update(cliente);
		Query query = session.createQuery("update ClienteBean c set c.cuit = :cuit, c.razonSocial = :razonSocial, c.condicionVenta = :condicionVenta, " +
				"c.descuentos = :descuento where c.clienteID.nroCliente = :nroCliente and c.clienteID.nroOficina = :nroOficina");
		query.setString("cuit", cliente.getCuit());
		query.setString("razonSocial", cliente.getRazonSocial());
		query.setString("condicionVenta", cliente.getCondicionVenta());
		query.setFloat("descuento", cliente.getDescuentos());
		query.setInteger("nroCliente", cliente.getClienteID().getNroCliente());
		query.setInteger("nroOficina", cliente.getClienteID().getNroOficina());
		query.executeUpdate();
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	public Cliente buscarClientePorOficina(int nroCliente, int nroOficina) 
	{
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from ClienteBean c where c.clienteID.nroCliente = :nroCliente and c.clienteID.nroOficina = :nroOficina");
		query.setInteger("nroCliente", nroCliente);
		query.setInteger("nroOficina", nroOficina);
		Cliente cliente = null;
		try{
			ClienteBean clienteBean = (ClienteBean) query.uniqueResult();
			cliente = clienteBean.pasarNegocio();
		}catch (Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return cliente;
	}
	
	public void asignarDescuento(ClienteBean clienteBean, DescuentoClienteBean descuentoCliente) 
	{
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("update ClienteBean cb set cb.descuentoCliente.descuentoClienteID.descuentoClienteID = :nroDescuento, " +
				"cb.descuentoCliente.descuentoClienteID.nroOficinaVenta = :nroOficina where cb.clienteID.nroCliente = :nroCliente " +
				"and cb.clienteID.nroOficina = :nroOficina");
				
		query.setInteger("nroDescuento", descuentoCliente.getDescuentoClienteID().getDescuentoClienteID());
		query.setInteger("nroOficina", clienteBean.getClienteID().getNroOficina());
		query.setInteger("nroCliente", clienteBean.getClienteID().getNroCliente());
		query.executeUpdate();
		session.flush();
		session.getTransaction().commit();
		session.close();
	}*/

}
