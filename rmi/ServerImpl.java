package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controlador.Sistema;

@SuppressWarnings("serial")
public class ServerImpl extends UnicastRemoteObject implements IRmiServer{

	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int nuevoAlumno(String tipoDocumento, int nroDocumento,
			String nombre, String apellido) throws RemoteException {
		return Sistema.getInstance().nuevoAlumno(tipoDocumento, nroDocumento, nombre, apellido);
	}

}
