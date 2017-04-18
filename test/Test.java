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
		
		int alimento1 = sistema.nuevoAlimento("Comida1", 2, 20);
		int alimento2 = sistema.nuevoAlimento("Comida2", 4, 40);
		int alimento3 = sistema.nuevoAlimento("Comida3", 5, 50);
		
		int tipoAvatar1 = sistema.nuevoTipoAvatar("C�lula", 20, 1, 2000,500,500);
		int tipoAvatar2 = sistema.nuevoTipoAvatar("Pez", 100, 1, 1000,2000,1500);
		
		sistema.tipoAvatarAgregarAlimento(alimento1, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento2, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento2, tipoAvatar2);
		sistema.tipoAvatarAgregarAlimento(alimento3, tipoAvatar2);

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
		sistema.alumnoAlimentarAvatar(alumno1, alimento1);
		sistema.alumnoAlimentarAvatar(alumno1, alimento3);
		sistema.alumnoRevivirAvatar(alumno1);
		sistema.alumnoEvolucionarAvatar(alumno1);
		
		sistema.eliminarAlumno(alumno1);
		sistema.nuevoAlumno("DNI", 35730491, "Arielo", "Antognini");
		sistema.modificarAlumno("DNI", 35730491, "Ariel", "Antognini");
		
		sistema.eliminarDocente(docente3);
		sistema.nuevoDocente("DNI", 20987641, "Paula", "Zarasa");
		sistema.modificarDocente("DNI", 20987641, "Paula", "Sarasa");
		
		sistema.eliminarValorLietner(4);
		sistema.agregarValorLietner(4,1);
		
		sistema.eliminarTema(tema1);
		int tema99 = sistema.nuevoTema("Divisiones pol�ticas");
		sistema.modificarTema(tema99,"Divisi�n pol�tica");
		sistema.eliminarTema(tema99);
		sistema.nuevoTema("Divisi�n pol�tica");
		
		sistema.eliminarJuego(juego1);
		int juego99 = sistema.nuevoJuego("Divisiones pol�ticas 1",tema1);
		sistema.modificarJuego(juego99, "Divisi�n pol�tica 1",tema1);
		sistema.eliminarJuego(juego99);
		sistema.nuevoJuego("Divisi�n pol�tica 1",tema2);
		sistema.modificarJuego(juego1,"Divisi�n pol�tica 1",tema1);
		
		sistema.temaEliminarLeccion(tema1, leccion1);
		int leccion99 = sistema.temaAgregarLeccion(tema1, "Posiciones departamento Corpen Aike");
		sistema.temaModificarLeccion(tema1, leccion99,"Posici�n departamento Corpen Aike");
		sistema.temaEliminarLeccion(tema1, leccion99);
		int leccion9 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Corpen Aike");
		sistema.juegoAgregarLeccion(juego1, leccion9);
		
		sistema.juegoQuitarLeccion(juego1, leccion9);
		sistema.juegoAgregarLeccion(juego1, leccion9);

		sistema.eliminarAlimento(alimento1);
		int alimento99 = sistema.nuevoAlimento("Coomida1", 2, 20);
		sistema.modificarAlimento(alimento99,"Comida1", 2, 20);
		sistema.eliminarAlimento(alimento99);
		sistema.nuevoAlimento("Comida1", 2, 20);
		
		sistema.eliminarTipoAvatar(tipoAvatar1);
		int tipoAvatar99 = sistema.nuevoTipoAvatar("C�lulas", 20, 1, 2000,500,500);
		sistema.modificarTipoAvatar(tipoAvatar99,"C�lula", 20, 1, 2000,500,500);
		sistema.eliminarTipoAvatar(tipoAvatar99);
		sistema.nuevoTipoAvatar("C�lula", 20, 1, 2000,500,500);
		
		sistema.docenteEliminarCurso(docente3,curso1);
		int curso99 = sistema.docenteAgregarCurso(docente3, "Curso VADE Noche");
		sistema.docenteModificarCurso(docente3, curso99,"Curso UADE Noche");
		sistema.docenteEliminarCurso(docente3,curso99);
		sistema.docenteAgregarCurso(docente3, "Curso UADE Noche");
		
		sistema.cursoQuitarAlumno(docente3, curso1, alumno1);
		sistema.cursoAgregarAlumno(docente3, curso1, alumno1);
		
		sistema.tipoAvatarQuitarAlimento(tipoAvatar1,alimento1);
		sistema.tipoAvatarAgregarAlimento(tipoAvatar1,alimento1);
		
		//TODO Agregar un timer para que vaya disminuyendo el hambre a los Avatares
	}
}