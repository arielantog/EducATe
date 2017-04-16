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
		
		int comida1 = sistema.nuevoAlimento("Comida1", 2, 20);
		int comida2 = sistema.nuevoAlimento("Comida2", 4, 40);
		int comida3 = sistema.nuevoAlimento("Comida3", 5, 50);
		
		int tipoAvatar1 = sistema.nuevoTipoAvatar("Célula", 20, 1, 2000,500,500);
		int tipoAvatar2 = sistema.nuevoTipoAvatar("Pez", 100, 1, 1000,2000,1500);
		
		sistema.tipoAvatarAgregarAlimento(comida1, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(comida2, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(comida2, tipoAvatar2);
		sistema.tipoAvatarAgregarAlimento(comida3, tipoAvatar2);

		int alumno1 = sistema.nuevoAlumno("DNI", 35730491, "Ariel", "Antognini");
		int alumno2 = sistema.nuevoAlumno("DNI", 35491512, "Yamil", "Amado");

		sistema.cursoAgregarAlumno(docente3, curso1, alumno1);
		sistema.cursoAgregarAlumno(docente3, curso1, alumno2);
		
		
		
		int juegoId =sistema.elegirJuegoSinTema(alumno1);
		System.out.println("Juego elegido:" + juegoId);
		juegoId = sistema.elegirJuegoConTema(alumno1, tema1);
		System.out.println("Juego elegido:" + juegoId);
		
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1, leccion1, true);
		sistema.alumnoAlimentarAvatar(alumno1, comida1);
		sistema.alumnoAlimentarAvatar(alumno1, comida3);
		sistema.alumnoRevivirAvatar(alumno1);
		sistema.alumnoEvolucionarAvatar(alumno1);
		
		sistema.eliminarAlumno(alumno1);
		sistema.nuevoAlumno("DNI", 35730491, "Arielo", "Antognini");
		sistema.modificarAlumno("DNI", 35730491, "Ariel", "Antognini");
		
		sistema.eliminarDocente(docente3);
		sistema.nuevoDocente("DNI", 20987641, "Paula", "Zarasa");
		sistema.modificarDocente("DNI", 20987641, "Paula", "Sarasa");
		
		//TODO Agregar un timer para que vaya disminuyendo el hambre a los Avatares
		//TODO Agregar el campo para la baja lógica y tener en cuenta para los SELECTs
	}
}