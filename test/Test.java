package test;

import controlador.Sistema;

public class Test {

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		
		int docente3 = sistema.nuevoDocente("DNI", 20987641, "Paula", "Sarasa");
		
		int tema1 = sistema.nuevoTema("Divisi�n pol�tica");
		int tema2 = sistema.nuevoTema("Condiciones naturales");
		sistema.nuevoTema("�reas protegidas");
		sistema.nuevoTema("Problemas ambientales");
		sistema.nuevoTema("Espacios rurales");
		sistema.nuevoTema("Espacios urbanos");
		
		int juego1 = sistema.nuevoJuego("Divisi�n pol�tica 1", tema1);
		sistema.nuevoJuego("Divisi�n pol�tica 2", tema1);
		sistema.nuevoJuego("Condiciones naturales 1", tema2);

		
		int curso1 = sistema.docenteAgregarCurso(docente3, "Curso UADE Noche");

		
		int leccion1 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Corpen Aike");
		int leccion2 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Deseado");
		int leccion3 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento G�er Aike");
		int leccion4 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Lago Argentino");
		int leccion5 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Lago Buenos Aires");
		int leccion6 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Magallanes");
		int leccion7 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento R�o Chico");
		

		sistema.juegoAgregarLeccion(juego1, leccion1);
		sistema.juegoAgregarLeccion(juego1, leccion2);
		sistema.juegoAgregarLeccion(juego1, leccion3);
		sistema.juegoAgregarLeccion(juego1, leccion4);
		sistema.juegoAgregarLeccion(juego1, leccion5);
		sistema.juegoAgregarLeccion(juego1, leccion6);
		sistema.juegoAgregarLeccion(juego1, leccion7);
		
		//TODO Las ense�anzas no se est�n persistiendo
		//TODO Una vez que se persistan, actualizar el alumno en la base
		int alumno1 = sistema.nuevoAlumno("DNI", 35730491, "Ariel", "Antognini");
		int alumno2 = sistema.nuevoAlumno("DNI", 35491512, "Yamil", "Amado");

		sistema.cursoAgregarAlumno(docente3, curso1, alumno1);
		sistema.cursoAgregarAlumno(docente3, curso1, alumno2);
		
		sistema.avatarAgregarElemento(alumno1, "No me acuerdo para qu� es esto", "Gorra", "Rojo");
		
		//Dej� hasta ac� sin probar. Hay que correrlo una vez con el Create And Drop
		//Y una segunda vez sin esa propiedad.
		//Ahora falla porque el alumno no tiene ensenianzas
		int juegoId =sistema.elegirJuegoSinTema(alumno1);
		System.out.println("Juego elegido:" +juegoId);
		
		sistema.elegirJuegoConTema(alumno1, tema1);
	}
}