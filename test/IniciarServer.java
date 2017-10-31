package test;

import controlador.Sistema;

public class IniciarServer {

	public static void main(String[] args) {
		try{
			new Sistema();
			rmi.Server.getInstancia().start();
			System.out.println("Conectado");
		}catch(Exception e){
			rmi.Server.getInstancia().stop();
		}
		
	}

}
