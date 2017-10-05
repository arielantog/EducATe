package test;

import java.rmi.RemoteException;

import controlador.Sistema;
import dto.AlumnoDTO;
import dto.CursoDTO;
import dto.DocenteDTO;

public class Test {
	
	/*Usar solo con Create and Drop*/
	public static void main(String[] args) throws RemoteException{
		Sistema sistema = new Sistema();
				
		sistema.agregarValorLietner(0,0,35);
		sistema.agregarValorLietner(1,36,55);
		sistema.agregarValorLietner(2,56,75);
		sistema.agregarValorLietner(3,75,87);
		sistema.agregarValorLietner(4,88,94);
		sistema.agregarValorLietner(5,95,99);
		
		DocenteDTO docente3 = sistema.nuevoDocente("DNI", 20987641, "Paula", "Sarasa", "123456", "psarasa@uade.edu.ar");
		
		int tema1 = sistema.nuevoTema("Divisi�n pol�tica");
		int tema2 = sistema.nuevoTema("Condiciones naturales");
		int tema3 = sistema.nuevoTema("�reas protegidas");
		int tema4 = sistema.nuevoTema("Problemas ambientales");
		sistema.nuevoTema("Espacios rurales");
		sistema.nuevoTema("Espacios urbanos");
		
		int juego1 = sistema.nuevoJuego("Divisi�n pol�tica 1", tema1);
		int juego2 = sistema.nuevoJuego("Condiciones naturales 1", tema2);
		int juego3 = sistema.nuevoJuego("�reas protegidas 1", tema3);
		int juego4 = sistema.nuevoJuego("Problemas ambientales 1", tema4);
		
		CursoDTO curso1 = sistema.docenteAgregarCurso(docente3.getId(), "Curso UADE Noche");

		
		int leccion1  = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Corpen Aike");
		int leccion2  = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Deseado");
		int leccion3  = sistema.temaAgregarLeccion(tema1, "Posici�n departamento G�er Aike");
		int leccion4  = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Lago Argentino");
		int leccion5  = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Lago Buenos Aires");
		int leccion6  = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Magallanes");
		int leccion7  = sistema.temaAgregarLeccion(tema1, "Posici�n departamento R�o Chico");
		
		int leccion8  = sistema.temaAgregarLeccion(tema2, "Posici�n zona clima Frio nival");
		int leccion9  = sistema.temaAgregarLeccion(tema2, "Posici�n zona clima Frio humedo");
		int leccion10 = sistema.temaAgregarLeccion(tema2, "Posici�n zona clima Frio humedo austral");
		int leccion11 = sistema.temaAgregarLeccion(tema2, "Posici�n zona clima Frio arido de la Patagonia");
		
		int leccion12 = sistema.temaAgregarLeccion(tema3, "Nombre Sitio Arqueol�gico Cueva de Las Manos");
		int leccion13 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Interjurisdiccional Marino Isla Ping�ino");
		int leccion14 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Bosques Petrificados de Jaramillo");
		int leccion15 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Los Glaciares");
		int leccion16 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Interjurisdiccional Marino Makenke");
		int leccion17 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Monte Leon");
		int leccion18 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Patagonia");
		int leccion19 = sistema.temaAgregarLeccion(tema3, "Nombre Parque Nacional Perito Moreno");
		
		int leccion20 = sistema.temaAgregarLeccion(tema4, "Problema Contaminaci�n hidrocarbur�fera");
		int leccion21 = sistema.temaAgregarLeccion(tema4, "Problema Erupciones volc�nicas y de cenizas");
		int leccion22 = sistema.temaAgregarLeccion(tema4, "Problema Tormentas de nieve y heladas");
		int leccion23 = sistema.temaAgregarLeccion(tema4, "Problema Incendios silvestres");
		int leccion24 = sistema.temaAgregarLeccion(tema4, "Problema Aluviones");
		int leccion25 = sistema.temaAgregarLeccion(tema4, "Problema Materiales qu�micos");
		int leccion26 = sistema.temaAgregarLeccion(tema4, "Problema Desertificaci�n");
		

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
		
		
		
		int alimento1 = sistema.nuevoAlimento("Comida1", 2, 20, null);
		int alimento2 = sistema.nuevoAlimento("Comida2", 4, 40, null);
		int alimento3 = sistema.nuevoAlimento("Comida3", 5, 50, null);
		
		int tipoAvatar1 = sistema.nuevoTipoAvatar("Celent�reo 1", 20, 2000,500,500, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Celent�reos/01.png");
		int tipoAvatar2 = sistema.nuevoTipoAvatar("Celent�reo 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Celent�reos/02.png");
		sistema.nuevoTipoAvatar("Equinodermos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Equinodermos/01.png");
		sistema.nuevoTipoAvatar("Equinodermos 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Equinodermos/02.png");
		sistema.nuevoTipoAvatar("Moluscos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Moluscos/01.png");
		sistema.nuevoTipoAvatar("Moluscos 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Moluscos/02.png");
		sistema.nuevoTipoAvatar("Peces 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/01.png");
		sistema.nuevoTipoAvatar("Peces 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/02.png");
		sistema.nuevoTipoAvatar("Gusanos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Gusanos/01.png");
		sistema.nuevoTipoAvatar("Gusanos 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Gusanos/02.png");
		sistema.nuevoTipoAvatar("Artr�podos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Artr�podos/01.png");
		sistema.nuevoTipoAvatar("Artr�podos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Artr�podos/02.png");
		sistema.nuevoTipoAvatar("Reptiles 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Reptiles/01.png");
		sistema.nuevoTipoAvatar("Reptiles 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Reptiles/02.png");
		sistema.nuevoTipoAvatar("Aves 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Aves/01.png");
		sistema.nuevoTipoAvatar("Aves 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Aves/02.png");
		
		sistema.tipoAvatarAgregarAlimento(alimento1, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento2, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento2, tipoAvatar2);
		sistema.tipoAvatarAgregarAlimento(alimento3, tipoAvatar2);

		AlumnoDTO alumno1 = sistema.nuevoAlumno("DNI", 35730491, "Ariel", "Antognini", "123456", "arielantog@gmail.com", "Ari");
		AlumnoDTO alumno2 = sistema.nuevoAlumno("DNI", 35491512, "Yamil", "Amado", "123456", "amado.yamil@gmail.com", "Turco");
		sistema.nuevoAlumno("DNI", 30321819, "Alumno3", "Alumno3", "123456", "alumno3@gmail.com", "Alu3");
			
			
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno1.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno2.getId());
		
		
		int juego98 =sistema.elegirJuegoSinTema(alumno1.getId());
		sistema.alumnoBuscarLeccion(alumno1.getId(), juego98);
		
		sistema.elegirJuegoConTema(alumno1.getId(), tema1);
		
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, false);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, false);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion1, true);
		
 		//sistema.alumnoAlimentarAvatar(alumno1.getId(), alimento1);
		//sistema.alumnoAlimentarAvatar(alumno1.getId(), alimento3);
		//sistema.alumnoEvolucionarAvatar(alumno1.getId());
		
		/*
		//sistema.alumnoRevivirAvatar(alumno1.getId());
		
		
		No funciona desde las excepciones
		sistema.eliminarAlumno(alumno1.getId());
		sistema.nuevoAlumno("DNI", 35730491, "Arielo", "Antognini", "123456", "arielantog@gmail.com", "Ari");
		sistema.activarAlumno("DNI", 35730491, "Arielo", "Antognini", "123456", "arielantog@gmail.com", "Ari");
		sistema.modificarAlumno("DNI", 35730491, "Ariel", "Antognini", "123456", "arielantog@gmail.com", "Ari");
		
		sistema.eliminarDocente(docente3.getId());
		sistema.nuevoDocente("DNI", 20987641, "Paula", "Zarasa", "123456", "pzarasa@uade.edu.ar");
		sistema.modificarDocente("DNI", 20987641, "Paula", "Sarasa", "123456", "pzarasa@uade.edu.ar");
		
		sistema.eliminarValorLietner(4);
		sistema.agregarValorLietner(4,88,95);
		sistema.modificarValorLietner(4,88,94);
		
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
		int leccion98 = sistema.temaAgregarLeccion(tema1, "Posici�n departamento Corpen Aike");
		sistema.juegoAgregarLeccion(juego1, leccion98);
		
		sistema.juegoQuitarLeccion(juego1, leccion98);
		sistema.juegoAgregarLeccion(juego1, leccion98);

		sistema.eliminarAlimento(alimento1);
		int alimento99 = sistema.nuevoAlimento("Coomida1", 2, 20, null);
		sistema.modificarAlimento(alimento99,"Comida1", 2, 20, null);
		sistema.eliminarAlimento(alimento99);
		sistema.nuevoAlimento("Comida1", 2, 20, null);
		
		sistema.eliminarTipoAvatar(tipoAvatar1);
		int tipoAvatar99 = sistema.nuevoTipoAvatar("Celent�reo1", 20, 2000,500,500, "http://localhost:7616/EducATe_-_FrontEnd/images/TipoAvatar/Celent�reos/1.png");
		sistema.modificarTipoAvatar(tipoAvatar99,"Celent�reo1", 20, 2000,500,500,"http://localhost:7616/EducATe_-_FrontEnd/images/TipoAvatar/Celent�reos/1.png");
		sistema.eliminarTipoAvatar(tipoAvatar99);
		sistema.nuevoTipoAvatar("Celent�reo 1", 20, 2000,500,500, "http://localhost:7616/EducATe_-_FrontEnd/images/TipoAvatar/Celent�reos/1.png");
		
		sistema.docenteEliminarCurso(docente3.getId(),curso1.getId());
		CursoDTO curso99 = sistema.docenteAgregarCurso(docente3.getId(), "Curso VADE Noche");
		sistema.docenteModificarCurso(docente3.getId(), curso99.getId(),"Curso UADE Noche");
		sistema.docenteEliminarCurso(docente3.getId(),curso99.getId());
		sistema.docenteAgregarCurso(docente3.getId(), "Curso UADE Noche");
		
		CursoDTO curso2 = sistema.docenteAgregarCurso(docente3.getId(), "Curso UADE Ma�ana");
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno1.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno2.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno3.getId());
		
		sistema.cursoQuitarAlumno(docente3.getId(), curso1.getId(), alumno1.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno1.getId());
		
		sistema.tipoAvatarQuitarAlimento(tipoAvatar1,alimento1);
		sistema.tipoAvatarAgregarAlimento(tipoAvatar1,alimento1);
		*/
		sistema.alumnoBuscarLeccion(1, 3);
		
		sistema.listarCursosPorDocente(docente3.getId());
		
	} 
}