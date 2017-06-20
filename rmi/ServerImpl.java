package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controlador.Sistema;
import dto.AlumnoDTO;
import dto.DocenteDTO;

@SuppressWarnings("serial")
public class ServerImpl extends UnicastRemoteObject implements IRmiServer{

	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int nuevoAlumno(String tipoDocumento, int nroDocumento,
			String nombre, String apellido, String usuario, String password, String mail) throws RemoteException {
		return Sistema.getInstance().nuevoAlumno(tipoDocumento, nroDocumento, nombre, apellido, usuario, password, mail);
	}

	public int nuevoDocente(String tipoDocumento, int nroDocumento, String nombre, String apellido, String password,
			String mail) throws RemoteException {
		return Sistema.getInstance().nuevoDocente(tipoDocumento, nroDocumento, nombre, apellido, password, mail);
	}
/*
	public int loginDocente(String tipoDocumento, int nroDocumento, String password) throws RemoteException {
		return Sistema.getInstance().loginDocente(tipoDocumento, nroDocumento, password);
	}
*/
	public DocenteDTO loginDocente(String tipoDocumento, int nroDocumento, String password) throws RemoteException {
		return Sistema.getInstance().loginDocente(tipoDocumento, nroDocumento, password);
	}

	public AlumnoDTO loginAlumno(String usuario, String password) throws RemoteException {
		return Sistema.getInstance().loginAlumno(usuario, password);
	}

}
