package test;

import java.rmi.RemoteException;

import controlador.Sistema;

public class Test {
	
	public static void main(String[] args) throws RemoteException{
		Sistema sistema = new Sistema();
		
		sistema.agregarValorLietner(0,0,35);
		sistema.agregarValorLietner(1,36,55);
		sistema.agregarValorLietner(2,56,75);
		sistema.agregarValorLietner(3,75,87);
		sistema.agregarValorLietner(4,88,94);
		sistema.agregarValorLietner(5,95,99);
		
		
		int tema1 = sistema.nuevoTema("División política");
		int tema2 = sistema.nuevoTema("Condiciones naturales");
		int tema3 = sistema.nuevoTema("Áreas protegidas");
		int tema4 = sistema.nuevoTema("Problemas ambientales");
		sistema.nuevoTema("Espacios rurales");
		sistema.nuevoTema("Espacios urbanos");
		
		int juego1 = sistema.nuevoJuego("División política 1", tema1);
		int juego2 = sistema.nuevoJuego("Condiciones naturales 1", tema2);
		int juego3 = sistema.nuevoJuego("Áreas protegidas 1", tema3);
		int juego4 = sistema.nuevoJuego("Problemas ambientales 1", tema4);

		int leccion1  = sistema.temaAgregarLeccion(tema1, "Posición departamento Corpen Aike");
		int leccion2  = sistema.temaAgregarLeccion(tema1, "Posición departamento Deseado");
		int leccion3  = sistema.temaAgregarLeccion(tema1, "Posición departamento Güer Aike");
		int leccion4  = sistema.temaAgregarLeccion(tema1, "Posición departamento Lago Argentino");
		int leccion5  = sistema.temaAgregarLeccion(tema1, "Posición departamento Lago Buenos Aires");
		int leccion6  = sistema.temaAgregarLeccion(tema1, "Posición departamento Magallanes");
		int leccion7  = sistema.temaAgregarLeccion(tema1, "Posición departamento Río Chico");
		
		int leccion8  = sistema.temaAgregarLeccion(tema2, "Posición zona clima Frio nival");
		int leccion9  = sistema.temaAgregarLeccion(tema2, "Posición zona clima Frio humedo");
		int leccion10 = sistema.temaAgregarLeccion(tema2, "Posición zona clima Frio humedo austral");
		int leccion11 = sistema.temaAgregarLeccion(tema2, "Posición zona clima Frio arido de la Patagonia");
		
		int leccion12 = sistema.temaAgregarLeccion(tema3, "Nombre Sitio Arqueológico Cueva de Las Manos");
		int leccion13 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Interjurisdiccional Marino Isla Pingüino");
		int leccion14 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Bosques Petrificados de Jaramillo");
		int leccion15 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Los Glaciares");
		int leccion16 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Interjurisdiccional Marino Makenke");
		int leccion17 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Monte Leon");
		int leccion18 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Patagonia");
		int leccion19 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Perito Moreno");
		
		int leccion20 = sistema.temaAgregarLeccion(tema4, "Problema Contaminación hidrocarburífera");
		int leccion21 = sistema.temaAgregarLeccion(tema4, "Problema Erupciones volcánicas y de cenizas");
		int leccion22 = sistema.temaAgregarLeccion(tema4, "Problema Tormentas de nieve y heladas");
		int leccion23 = sistema.temaAgregarLeccion(tema4, "Problema Incendios silvestres");
		int leccion24 = sistema.temaAgregarLeccion(tema4, "Problema Aluviones");
		int leccion25 = sistema.temaAgregarLeccion(tema4, "Problema Materiales químicos");
		int leccion26 = sistema.temaAgregarLeccion(tema4, "Problema Desertificación");
		
		agregarLecciones(sistema, juego1, juego2, juego3, juego4, leccion1,
				leccion2, leccion3, leccion4, leccion5, leccion6, leccion7,
				leccion8, leccion9, leccion10, leccion11, leccion12, leccion13,
				leccion14, leccion15, leccion16, leccion17, leccion18,
				leccion19, leccion20, leccion21, leccion22, leccion23,
				leccion24, leccion25, leccion26);
		
		int alimento1 = sistema.nuevoAlimento("Artemia", 2, 20, "http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Artemia.png");
		int alimento2 = sistema.nuevoAlimento("Pez marrón", 5, 100, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/12.png");
		int alimento3 = sistema.nuevoAlimento("Pez dorado", 4, 85, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/14.png");
		int alimento4 = sistema.nuevoAlimento("Lechuga", 2, 50, "http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Lechuga.png");
		int alimento5 = sistema.nuevoAlimento("Tomate", 4, 75, "http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Tomate.png");
		int alimento6 = sistema.nuevoAlimento("Zanahoria", 5, 100,"http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Zanahoria.png");
		int alimento7 = sistema.nuevoAlimento("Gusano", 3, 150, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Gusanos/10.png");
		int alimento8 = sistema.nuevoAlimento("Semillas", 2, 120,"http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Semillas.png");
		int alimento9 = sistema.nuevoAlimento("Carne", 10, 500,"http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Carne.png");
		int alimento10 = sistema.nuevoAlimento("Lagarto", 5,300,"http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Reptiles/14.png");
		int alimento11 = sistema.nuevoAlimento("Pez azul", 3, 200, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/10.png");
		

		int tipoAvatar1 = sistema.nuevoTipoAvatar("Caballo de mar", 20, 2000,1000,250, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/16.png");
		int tipoAvatar2 = sistema.nuevoTipoAvatar("Tiburón", 100, 2000,2500,500, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/09.png");
		int tipoAvatar3 = sistema.nuevoTipoAvatar("Tortuga", 80, 2000,4000,1000, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Reptiles/11.png");
		int tipoAvatar4 = sistema.nuevoTipoAvatar("Pájaro", 50, 2000,10000,2500, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Aves/01.png");
		int tipoAvatar5 = sistema.nuevoTipoAvatar("Lobo", 120, 2000,25000,5000, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Mamiferos/Carnívoros/01.png");
		
		
		sistema.tipoAvatarAgregarAlimento(alimento1, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento2, tipoAvatar2);
		sistema.tipoAvatarAgregarAlimento(alimento3, tipoAvatar2);
		sistema.tipoAvatarAgregarAlimento(alimento4, tipoAvatar3);
		sistema.tipoAvatarAgregarAlimento(alimento5, tipoAvatar3);
		sistema.tipoAvatarAgregarAlimento(alimento6, tipoAvatar3);
		sistema.tipoAvatarAgregarAlimento(alimento7, tipoAvatar4);
		sistema.tipoAvatarAgregarAlimento(alimento8, tipoAvatar4);
		sistema.tipoAvatarAgregarAlimento(alimento9, tipoAvatar5);
		sistema.tipoAvatarAgregarAlimento(alimento10, tipoAvatar5);
		sistema.tipoAvatarAgregarAlimento(alimento11, tipoAvatar5);
		
		iniciarServer();
		
	}



	private static void iniciarServer() {
		try{
			rmi.Server.getInstancia().start();
			System.out.println("Conectado");
		}catch(Exception e){
			rmi.Server.getInstancia().stop();
		}
	}

	private static void agregarLecciones(Sistema sistema, int juego1,
			int juego2, int juego3, int juego4, int leccion1, int leccion2,
			int leccion3, int leccion4, int leccion5, int leccion6,
			int leccion7, int leccion8, int leccion9, int leccion10,
			int leccion11, int leccion12, int leccion13, int leccion14,
			int leccion15, int leccion16, int leccion17, int leccion18,
			int leccion19, int leccion20, int leccion21, int leccion22,
			int leccion23, int leccion24, int leccion25, int leccion26) {
		sistema.juegoAgregarLeccion(juego1, leccion1);
		sistema.juegoAgregarLeccion(juego1, leccion2);
		sistema.juegoAgregarLeccion(juego1, leccion3);
		sistema.juegoAgregarLeccion(juego1, leccion4);
		sistema.juegoAgregarLeccion(juego1, leccion5);
		sistema.juegoAgregarLeccion(juego1, leccion6);
		sistema.juegoAgregarLeccion(juego1, leccion7);
		
		sistema.juegoAgregarLeccion(juego2, leccion8);
		sistema.juegoAgregarLeccion(juego2, leccion9);
		sistema.juegoAgregarLeccion(juego2, leccion10);
		sistema.juegoAgregarLeccion(juego2, leccion11);
		
		sistema.juegoAgregarLeccion(juego3, leccion12);
		sistema.juegoAgregarLeccion(juego3, leccion13);
		sistema.juegoAgregarLeccion(juego3, leccion14);
		sistema.juegoAgregarLeccion(juego3, leccion15);
		sistema.juegoAgregarLeccion(juego3, leccion16);
		sistema.juegoAgregarLeccion(juego3, leccion17);
		sistema.juegoAgregarLeccion(juego3, leccion18);
		sistema.juegoAgregarLeccion(juego3, leccion19);
		
		sistema.juegoAgregarLeccion(juego4, leccion20);
		sistema.juegoAgregarLeccion(juego4, leccion21);
		sistema.juegoAgregarLeccion(juego4, leccion22);
		sistema.juegoAgregarLeccion(juego4, leccion23);
		sistema.juegoAgregarLeccion(juego4, leccion24);
		sistema.juegoAgregarLeccion(juego4, leccion25);
		sistema.juegoAgregarLeccion(juego4, leccion26);
	} 
}