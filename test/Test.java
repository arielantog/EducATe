package test;

import controlador.Sistema;

public class Test {

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		
		int docente3 = sistema.nuevoDocente("DNI", 20987641, "Paula", "Sarasa");
		//Borrar
		int docente4 = sistema.nuevoDocente("DNI", 66666666, "sssss", "asdasd");
		int docente5 = sistema.nuevoDocente("DNI", 77777777, "probando", "probando");
		
		int tema1 = sistema.nuevoTema("División política");
		int tema2 = sistema.nuevoTema("Condiciones naturales");
		sistema.nuevoTema("Áreas protegidas");
		sistema.nuevoTema("Problemas ambientales");
		sistema.nuevoTema("Espacios rurales");
		sistema.nuevoTema("Espacios urbanos");
		//borrar
		sistema.nuevoTema("probando");
		
		int juego1 = sistema.nuevoJuego("División política 1", tema1);
		sistema.nuevoJuego("División política 2", tema1);
		sistema.nuevoJuego("Condiciones naturales 1", tema2);
		//borrar
		sistema.nuevoJuego("probando", tema2);
		
		int curso1 = sistema.docenteAgregarCurso(docente3, "Curso UADE Noche");
		//borrar
		int curso2 = sistema.docenteAgregarCurso(docente5, "probandoCurso");
		
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
		//Borrar
		int alumno3 = sistema.nuevoAlumno("DNI", 12235677, "Nombre", "Apellido");
		
		sistema.cursoAgregarAlumno(docente3, curso1, alumno1);
		sistema.cursoAgregarAlumno(docente3, curso1, alumno2);
		
		sistema.avatarAgregarElemento(alumno1, "No me acuerdo para qué es esto", "Gorra", "Rojo");
		
		//HASTA ACÁ HACE LOS PERSIST. FALTAN TODOS LOS BUSCAR
		int juegoId =sistema.elegirJuegoSinTema(alumno1);
		System.out.println("Juego elegido:" +juegoId);
		
		sistema.elegirJuegoConTema(alumno1, tema1);
	}
}