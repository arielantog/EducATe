package rmi;

import rmi.IRmiServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server{
	public static ServerImpl implementacion;
	public static Server server; 
	
	public static Server getInstancia(){
		if (server==null){
			server = new Server();
		}
		return server;
	}

	public void start(){
		try {
			LocateRegistry.createRegistry(1099);
			implementacion = new ServerImpl();
			Naming.rebind(IRmiServer.url, implementacion);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void stop(){
		try {
			Naming.unbind(IRmiServer.url);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	
}
