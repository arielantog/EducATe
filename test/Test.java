package test;

import controlador.Sistema;

public class Test {

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		sistema.agregarValorLietner(1,1);
		sistema.agregarValorLietner(2,1);
		sistema.agregarValorLietner(3,1);
		sistema.agregarValorLietner(4,1);
		
		int docente3 = sistema.nuevoDocente("DNI", 20987641, "Paula", "Sarasa");
		
		int tema1 = sistema.nuevoTema("División política");
		int tema2 = sistema.nuevoTema("Condiciones naturales");
		sistema.nuevoTema("Áreas protegidas");
		sistema.nuevoTema("Problemas ambientales");
		sistema.nuevoTema("Espacios rurales");
		sistema.nuevoTema("Espacios urbanos");
		
		int juego1 = sistema.nuevoJuego("División política 1", tema1);
		sistema.nuevoJuego("División política 2", tema1);
		sistema.nuevoJuego("Condiciones naturales 1", tema2);

		
		int curso1 = sistema.docenteAgregarCurso(docente3, "Curso UADE Noche");

		
		int leccion1 = sistema.temaAgregarLeccion(tema1, "Posición departamento Corpen Aike");
		int leccion2 = sistema.temaAgregarLeccion(tema1, "Posición departamento Deseado");
		int leccion3 = sistema.temaAgregarLeccion(tema1, "Posición departamento Güer Aike");
		int leccion4 = sistema.temaAgregarLeccion(tema1, "Posición departamento Lago Argentino");
		int leccion5 = sistema.temaAgregarLeccion(tema1, "Posición departamento Lago Buenos Aires");
		int leccion6 = sistema.temaAgregarLeccion(tema1, "Posición departamento Magallanes");
		int leccion7 = sistema.temaAgregarLeccion(tema1, "Posición departamento Río Chico");
		

		sistema.juegoAgregarLeccion(juego1, leccion1);
		sistema.juegoAgregarLeccion(juego1, leccion2);
		sistema.juegoAgregarLeccion(juego1, leccion3);
		sistema.juegoAgregarLeccion(juego1, leccion4);
		sistema.juegoAgregarLeccion(juego1, leccion5);
		sistema.juegoAgregarLeccion(juego1, leccion6);
		sistema.juegoAgregarLeccion(juego1, leccion7);
		

		int alumno1 = sistema.nuevoAlumno("DNI", 35730491, "Ariel", "Antognini");
		int alumno2 = sistema.nuevoAlumno("DNI", 35491512, "Yamil", "Amado");

		sistema.cursoAgregarAlumno(docente3, curso1, alumno1);
		sistema.cursoAgregarAlumno(docente3, curso1, alumno2);
		
		sistema.avatarAgregarElemento(alumno1, "No me acuerdo para qué es esto", "Gorra", "Rojo");
		
		int juegoId =sistema.elegirJuegoSinTema(alumno1);
		System.out.println("Juego elegido:" +juegoId);
		juegoId = sistema.elegirJuegoConTema(alumno1, tema1);
		System.out.println("Juego elegido:" +juegoId);
	}
}