package test;

public class IniciarServer {

	public static void main(String[] args) {
		try{
			rmi.Server.getInstancia().start();
			System.out.println("Conectado");
		}catch(Exception e){
			rmi.Server.getInstancia().stop();
		}
		
	}

}
