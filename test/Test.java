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
		
		CursoDTO curso1 = sistema.docenteAgregarCurso(docente3.getId(), "4to Grado A - Escuela 1");
		CursoDTO curso2 = sistema.docenteAgregarCurso(docente3.getId(), "4to Grado B - Escuela 1");
		CursoDTO curso3 = sistema.docenteAgregarCurso(docente3.getId(), "4to Grado C - Escuela 1");
		CursoDTO curso4 = sistema.docenteAgregarCurso(docente3.getId(), "4to Grado A - Escuela 2");
		CursoDTO curso5 = sistema.docenteAgregarCurso(docente3.getId(), "4to Grado B - Escuela 2");

		
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
		
		
		
		int alimento1 = sistema.nuevoAlimento("Lechuga", 2, 20, "http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Lechuga.png");
		int alimento2 = sistema.nuevoAlimento("Tomate", 4, 40, "http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Tomate.png");
		int alimento3 = sistema.nuevoAlimento("Zanahoria", 5, 50,"http://localhost:8080/EducATe_-_FrontEnd/images/Alimentos/Zanahoria.png");
		
		int tipoAvatar1 = sistema.nuevoTipoAvatar("Reptiles 1", 20, 2000,500,500, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Reptiles/11.png");
		int tipoAvatar2 = sistema.nuevoTipoAvatar("Celentéreo 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Celentéreos/02.png");
		sistema.nuevoTipoAvatar("Equinodermos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Equinodermos/01.png");
		sistema.nuevoTipoAvatar("Equinodermos 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Equinodermos/02.png");
		sistema.nuevoTipoAvatar("Moluscos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Moluscos/01.png");
		sistema.nuevoTipoAvatar("Moluscos 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Moluscos/02.png");
		sistema.nuevoTipoAvatar("Peces 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/01.png");
		sistema.nuevoTipoAvatar("Peces 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Peces/02.png");
		sistema.nuevoTipoAvatar("Gusanos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Gusanos/01.png");
		sistema.nuevoTipoAvatar("Gusanos 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Gusanos/02.png");
		sistema.nuevoTipoAvatar("Artrópodos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Artrópodos/01.png");
		sistema.nuevoTipoAvatar("Artrópodos 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Artrópodos/02.png");
//		sistema.nuevoTipoAvatar("Reptiles 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Reptiles/01.png");
//		sistema.nuevoTipoAvatar("Reptiles 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Reptiles/02.png");
		sistema.nuevoTipoAvatar("Aves 1", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Aves/01.png");
		sistema.nuevoTipoAvatar("Aves 2", 100, 1000,10,10, "http://localhost:8080/EducATe_-_FrontEnd/images/TipoAvatar/Aves/02.png");
		
		sistema.tipoAvatarAgregarAlimento(alimento1, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento2, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento3, tipoAvatar1);
		sistema.tipoAvatarAgregarAlimento(alimento2, tipoAvatar2);
		sistema.tipoAvatarAgregarAlimento(alimento3, tipoAvatar2);

		AlumnoDTO alumno1 = sistema.nuevoAlumno("DNI", 35730491, "Ariel", "Antognini", "123456", "arielantog@gmail.com", "Ari");
		AlumnoDTO alumno2 = sistema.nuevoAlumno("DNI", 35491512, "Yamil", "Amado", "123456", "amado.yamil@gmail.com", "Turco");
		AlumnoDTO alumno3 = sistema.nuevoAlumno("DNI", 40254873, "Julián", "Rodriguez", "123456", "alumno3@gmail.com", "juli24");
		AlumnoDTO alumno4 = sistema.nuevoAlumno("DNI", 41653849, "Federico", "Dadamo", "123456", "alumno3@gmail.com", "damo5");
		AlumnoDTO alumno5 = sistema.nuevoAlumno("DNI", 40873271, "Ignacio", "Santoro", "123456", "alumno3@gmail.com", "nacho99");
		AlumnoDTO alumno6 = sistema.nuevoAlumno("DNI", 41886189, "Juan", "Gonzales", "123456", "alumno3@gmail.com", "juan63");
		AlumnoDTO alumno7 = sistema.nuevoAlumno("DNI", 41261627, "Benjamín", "López", "123456", "alumno3@gmail.com", "benja");
		AlumnoDTO alumno8 = sistema.nuevoAlumno("DNI", 41686526, "Santiago", "Fernandez", "123456", "alumno3@gmail.com", "santi52");
		AlumnoDTO alumno9 = sistema.nuevoAlumno("DNI", 41304295, "Thiago", "García", "123456", "alumno3@gmail.com", "tiago15");
		AlumnoDTO alumno10 = sistema.nuevoAlumno("DNI", 41204490, "Lucas", "Perez", "123456", "alumno3@gmail.com", "luperez");
		AlumnoDTO alumno11 = sistema.nuevoAlumno("DNI", 40687725, "Joaquín", "Martinez", "123456", "alumno3@gmail.com", "joa24");
		AlumnoDTO alumno12 = sistema.nuevoAlumno("DNI", 40227248, "Santino", "Gomez", "123456", "alumno3@gmail.com", "santi.gomez");
		AlumnoDTO alumno13 = sistema.nuevoAlumno("DNI", 40759633, "Lautaro", "Díaz", "123456", "alumno3@gmail.com", "lauti");
		AlumnoDTO alumno14 = sistema.nuevoAlumno("DNI", 41471270, "Valentina", "Sanchez", "123456", "alumno3@gmail.com", "valen65");
		AlumnoDTO alumno15 = sistema.nuevoAlumno("DNI", 40180862, "María", "Álvarez", "123456", "alumno3@gmail.com", "malvarez");
		AlumnoDTO alumno16 = sistema.nuevoAlumno("DNI", 41541416, "Isabella", "Romero", "123456", "alumno3@gmail.com", "isa_rome");
		AlumnoDTO alumno17 = sistema.nuevoAlumno("DNI", 40131188, "Zoe", "Sosa", "123456", "alumno3@gmail.com", "zoe87");
		AlumnoDTO alumno18 = sistema.nuevoAlumno("DNI", 40180862, "Catalina", "Ruiz", "123456", "alumno3@gmail.com", "cata");
		AlumnoDTO alumno19 = sistema.nuevoAlumno("DNI", 41049806, "Camila", "Torres", "123456", "alumno3@gmail.com", "cami.torres");
		AlumnoDTO alumno20 = sistema.nuevoAlumno("DNI", 40149066, "Julia", "Suárez", "123456", "alumno3@gmail.com", "julysa");
		AlumnoDTO alumno21 = sistema.nuevoAlumno("DNI", 40717392, "Micaela", "Castro", "123456", "alumno3@gmail.com", "micah");
		AlumnoDTO alumno22 = sistema.nuevoAlumno("DNI", 41810751, "Belén", "Gimenez", "123456", "alumno3@gmail.com", "beluhh");
		AlumnoDTO alumno23 = sistema.nuevoAlumno("DNI", 40684493, "Florencia", "Vazquez", "123456", "alumno3@gmail.com", "flor164");
		AlumnoDTO alumno24 = sistema.nuevoAlumno("DNI", 40927243, "Lucía", "Acosta", "123456", "alumno3@gmail.com", "luacosta");
		AlumnoDTO alumno25 = sistema.nuevoAlumno("DNI", 41774775, "Álvaro", "Del Magro", "123456", "alumno3@gmail.com", "alvaro12");
		AlumnoDTO alumno26 = sistema.nuevoAlumno("DNI", 40334498, "Nicolás", "Sueldo", "123456", "alumno3@gmail.com", "nicho");
		AlumnoDTO alumno27 = sistema.nuevoAlumno("DNI", 40884155, "Matías", "Vera", "123456", "alumno3@gmail.com", "mati.vera");
		AlumnoDTO alumno28 = sistema.nuevoAlumno("DNI", 41674361, "Luca", "Giunta", "123456", "alumno3@gmail.com", "lgiunta");
		AlumnoDTO alumno29 = sistema.nuevoAlumno("DNI", 40409664, "Gustavo", "Buján", "123456", "alumno3@gmail.com", "tavito");
		AlumnoDTO alumno30 = sistema.nuevoAlumno("DNI", 41881424, "Juan", "Viatry", "123456", "alumno3@gmail.com", "juanvi");
		AlumnoDTO alumno31 = sistema.nuevoAlumno("DNI", 40126154, "Manuel", "Cardozo", "123456", "alumno3@gmail.com", "manu89");
		AlumnoDTO alumno32 = sistema.nuevoAlumno("DNI", 41338727, "Melisa", "Pisanu", "123456", "alumno3@gmail.com", "mely");
		AlumnoDTO alumno33 = sistema.nuevoAlumno("DNI", 41797260, "Milagros", "Molinas", "123456", "alumno3@gmail.com", "mimo");
		AlumnoDTO alumno34 = sistema.nuevoAlumno("DNI", 41304295, "Verónica", "Paredes", "123456", "alumno3@gmail.com", "vero346");
		AlumnoDTO alumno35 = sistema.nuevoAlumno("DNI", 40172908, "Sebastián", "Prato", "123456", "alumno3@gmail.com", "prato3");
		AlumnoDTO alumno36 = sistema.nuevoAlumno("DNI", 40478006, "Facundo", "Ferrari", "123456", "alumno3@gmail.com", "fachu");
		AlumnoDTO alumno37 = sistema.nuevoAlumno("DNI", 40807997, "Nicolás", "Gago", "123456", "alumno3@gmail.com", "nico.gago");
		AlumnoDTO alumno38 = sistema.nuevoAlumno("DNI", 41426073, "Ezequiel", "Banyai", "123456", "alumno3@gmail.com", "eze33");
		AlumnoDTO alumno39 = sistema.nuevoAlumno("DNI", 41674470, "Juan Manuel", "Castels", "123456", "alumno3@gmail.com", "jmc");
		AlumnoDTO alumno40 = sistema.nuevoAlumno("DNI", 41420585, "Germán", "Azzola", "123456", "alumno3@gmail.com", "gerazzo");
		
		
			
			
		agregarAlumnosACursos(sistema, docente3, curso1, curso2, curso3,
				curso4, curso5, alumno1, alumno2, alumno3, alumno4, alumno5,
				alumno6, alumno7, alumno8, alumno9, alumno10, alumno11,
				alumno12, alumno13, alumno14, alumno15, alumno16, alumno17,
				alumno18, alumno19, alumno20, alumno21, alumno22, alumno23,
				alumno24, alumno25, alumno26, alumno27, alumno28, alumno29,
				alumno30, alumno31, alumno32, alumno33, alumno34, alumno35,
				alumno36, alumno37, alumno38, alumno39, alumno40);
		
		
		int juego98 =sistema.elegirJuegoSinTema(alumno1.getId());
		sistema.alumnoBuscarLeccion(alumno1.getId(), juego98);
		
		sistema.elegirJuegoConTema(alumno1.getId(), tema1);
		
		agregarEnsenianzas(sistema, leccion1, leccion2, leccion3, leccion4,
				leccion5, leccion6, leccion7, leccion8, leccion9, leccion10,
				leccion11, leccion12, leccion13, leccion14, leccion15,
				leccion16, leccion17, leccion18, leccion19, leccion20,
				leccion21, leccion22, leccion23, leccion24, leccion25,
				leccion26, alumno1, alumno2, alumno3, alumno4, alumno5,
				alumno6, alumno7, alumno8, alumno9, alumno10, alumno11,
				alumno12, alumno13, alumno14, alumno15, alumno16, alumno17,
				alumno18, alumno19, alumno20, alumno21, alumno22, alumno23,
				alumno24, alumno25, alumno26, alumno27, alumno28, alumno29,
				alumno30, alumno31, alumno32, alumno33, alumno34, alumno35,
				alumno36, alumno37, alumno38, alumno39, alumno40);

		
		evolucionDoble(sistema, alimento2, alumno19);
		evolucionDoble(sistema, alimento2, alumno27);
		evolucionDoble(sistema, alimento2, alumno21);
		evolucionDoble(sistema, alimento2, alumno13);

		
		evolucionSimple(sistema, alumno18);
		evolucionSimple(sistema, alumno30);
		evolucionSimple(sistema, alumno12);
		evolucionSimple(sistema, alumno33);
		evolucionSimple(sistema, alumno20);
		evolucionSimple(sistema, alumno25);
		
				
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
		int tema99 = sistema.nuevoTema("Divisiones políticas");
		sistema.modificarTema(tema99,"División política");
		sistema.eliminarTema(tema99);
		sistema.nuevoTema("División política");
		
		sistema.eliminarJuego(juego1);
		int juego99 = sistema.nuevoJuego("Divisiones políticas 1",tema1);
		sistema.modificarJuego(juego99, "División política 1",tema1);
		sistema.eliminarJuego(juego99);
		sistema.nuevoJuego("División política 1",tema2);
		sistema.modificarJuego(juego1,"División política 1",tema1);
		
		sistema.temaEliminarLeccion(tema1, leccion1);
		int leccion99 = sistema.temaAgregarLeccion(tema1, "Posiciones departamento Corpen Aike");
		sistema.temaModificarLeccion(tema1, leccion99,"Posición departamento Corpen Aike");
		sistema.temaEliminarLeccion(tema1, leccion99);
		int leccion98 = sistema.temaAgregarLeccion(tema1, "Posición departamento Corpen Aike");
		sistema.juegoAgregarLeccion(juego1, leccion98);
		
		sistema.juegoQuitarLeccion(juego1, leccion98);
		sistema.juegoAgregarLeccion(juego1, leccion98);

		sistema.eliminarAlimento(alimento1);
		int alimento99 = sistema.nuevoAlimento("Coomida1", 2, 20, null);
		sistema.modificarAlimento(alimento99,"Comida1", 2, 20, null);
		sistema.eliminarAlimento(alimento99);
		sistema.nuevoAlimento("Comida1", 2, 20, null);
		
		sistema.eliminarTipoAvatar(tipoAvatar1);
		int tipoAvatar99 = sistema.nuevoTipoAvatar("Celentéreo1", 20, 2000,500,500, "http://localhost:7616/EducATe_-_FrontEnd/images/TipoAvatar/Celentéreos/1.png");
		sistema.modificarTipoAvatar(tipoAvatar99,"Celentéreo1", 20, 2000,500,500,"http://localhost:7616/EducATe_-_FrontEnd/images/TipoAvatar/Celentéreos/1.png");
		sistema.eliminarTipoAvatar(tipoAvatar99);
		sistema.nuevoTipoAvatar("Celentéreo 1", 20, 2000,500,500, "http://localhost:7616/EducATe_-_FrontEnd/images/TipoAvatar/Celentéreos/1.png");
		
		sistema.docenteEliminarCurso(docente3.getId(),curso1.getId());
		CursoDTO curso99 = sistema.docenteAgregarCurso(docente3.getId(), "Curso VADE Noche");
		sistema.docenteModificarCurso(docente3.getId(), curso99.getId(),"Curso UADE Noche");
		sistema.docenteEliminarCurso(docente3.getId(),curso99.getId());
		sistema.docenteAgregarCurso(docente3.getId(), "Curso UADE Noche");
		
		CursoDTO curso2 = sistema.docenteAgregarCurso(docente3.getId(), "Curso UADE Mañana");
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

	private static void evolucionSimple(Sistema sistema, AlumnoDTO alumno4)
			throws RemoteException {
		sistema.alumnoEvolucionarAvatar(alumno4.getId());
	}

	private static void evolucionDoble(Sistema sistema, int alimento2,
			AlumnoDTO alumno4) throws RemoteException {
		evolucionSimple(sistema, alumno4);
		sistema.alumnoAlimentarAvatar(alumno4.getId(), alimento2);
		sistema.alumnoAlimentarAvatar(alumno4.getId(), alimento2);
		sistema.alumnoAlimentarAvatar(alumno4.getId(), alimento2);
		sistema.alumnoAlimentarAvatar(alumno4.getId(), alimento2);
		sistema.alumnoAlimentarAvatar(alumno4.getId(), alimento2);
		evolucionSimple(sistema, alumno4);
	}

	private static void agregarAlumnosACursos(Sistema sistema,
			DocenteDTO docente3, CursoDTO curso1, CursoDTO curso2,
			CursoDTO curso3, CursoDTO curso4, CursoDTO curso5,
			AlumnoDTO alumno1, AlumnoDTO alumno2, AlumnoDTO alumno3,
			AlumnoDTO alumno4, AlumnoDTO alumno5, AlumnoDTO alumno6,
			AlumnoDTO alumno7, AlumnoDTO alumno8, AlumnoDTO alumno9,
			AlumnoDTO alumno10, AlumnoDTO alumno11, AlumnoDTO alumno12,
			AlumnoDTO alumno13, AlumnoDTO alumno14, AlumnoDTO alumno15,
			AlumnoDTO alumno16, AlumnoDTO alumno17, AlumnoDTO alumno18,
			AlumnoDTO alumno19, AlumnoDTO alumno20, AlumnoDTO alumno21,
			AlumnoDTO alumno22, AlumnoDTO alumno23, AlumnoDTO alumno24,
			AlumnoDTO alumno25, AlumnoDTO alumno26, AlumnoDTO alumno27,
			AlumnoDTO alumno28, AlumnoDTO alumno29, AlumnoDTO alumno30,
			AlumnoDTO alumno31, AlumnoDTO alumno32, AlumnoDTO alumno33,
			AlumnoDTO alumno34, AlumnoDTO alumno35, AlumnoDTO alumno36,
			AlumnoDTO alumno37, AlumnoDTO alumno38, AlumnoDTO alumno39,
			AlumnoDTO alumno40) {
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno1.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno2.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno3.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno4.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno5.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno6.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno7.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno8.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso5.getId(), alumno9.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso5.getId(), alumno10.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno11.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno12.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno13.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno14.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno15.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno16.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno17.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno18.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso5.getId(), alumno19.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso5.getId(), alumno20.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno21.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno22.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno23.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno24.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno25.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno26.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno27.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno28.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso5.getId(), alumno29.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso5.getId(), alumno30.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno31.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso1.getId(), alumno32.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno33.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso2.getId(), alumno34.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno35.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno36.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno37.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso4.getId(), alumno38.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso5.getId(), alumno39.getId());
		sistema.cursoAgregarAlumno(docente3.getId(), curso3.getId(), alumno40.getId());
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

	private static void agregarEnsenianzas(Sistema sistema, int leccion1,
			int leccion2, int leccion3, int leccion4, int leccion5,
			int leccion6, int leccion7, int leccion8, int leccion9,
			int leccion10, int leccion11, int leccion12, int leccion13,
			int leccion14, int leccion15, int leccion16, int leccion17,
			int leccion18, int leccion19, int leccion20, int leccion21,
			int leccion22, int leccion23, int leccion24, int leccion25,
			int leccion26, AlumnoDTO alumno1, AlumnoDTO alumno2,
			AlumnoDTO alumno3, AlumnoDTO alumno4, AlumnoDTO alumno5,
			AlumnoDTO alumno6, AlumnoDTO alumno7, AlumnoDTO alumno8,
			AlumnoDTO alumno9, AlumnoDTO alumno10, AlumnoDTO alumno11,
			AlumnoDTO alumno12, AlumnoDTO alumno13, AlumnoDTO alumno14,
			AlumnoDTO alumno15, AlumnoDTO alumno16, AlumnoDTO alumno17,
			AlumnoDTO alumno18, AlumnoDTO alumno19, AlumnoDTO alumno20,
			AlumnoDTO alumno21, AlumnoDTO alumno22, AlumnoDTO alumno23,
			AlumnoDTO alumno24, AlumnoDTO alumno25, AlumnoDTO alumno26,
			AlumnoDTO alumno27, AlumnoDTO alumno28, AlumnoDTO alumno29,
			AlumnoDTO alumno30, AlumnoDTO alumno31, AlumnoDTO alumno32,
			AlumnoDTO alumno33, AlumnoDTO alumno34, AlumnoDTO alumno35,
			AlumnoDTO alumno36, AlumnoDTO alumno37, AlumnoDTO alumno38,
			AlumnoDTO alumno39, AlumnoDTO alumno40) {
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion2, false);
		sistema.alumnoAgregarEnsenianza(alumno26.getId(), leccion13, false);
		sistema.alumnoAgregarEnsenianza(alumno14.getId(), leccion8, true);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion19, true);
		sistema.alumnoAgregarEnsenianza(alumno20.getId(), leccion24, true);
		sistema.alumnoAgregarEnsenianza(alumno37.getId(), leccion12, true);
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion25, true);
		sistema.alumnoAgregarEnsenianza(alumno4.getId(), leccion19, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion13, true);
		sistema.alumnoAgregarEnsenianza(alumno14.getId(), leccion11, true);
		sistema.alumnoAgregarEnsenianza(alumno5.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno37.getId(), leccion22, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion21, true);
		sistema.alumnoAgregarEnsenianza(alumno9.getId(), leccion21, false);
		sistema.alumnoAgregarEnsenianza(alumno3.getId(), leccion18, false);
		sistema.alumnoAgregarEnsenianza(alumno29.getId(), leccion12, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion9, true);
		sistema.alumnoAgregarEnsenianza(alumno38.getId(), leccion17, false);
		sistema.alumnoAgregarEnsenianza(alumno31.getId(), leccion2, false);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion22, true);
		sistema.alumnoAgregarEnsenianza(alumno24.getId(), leccion21, false);
		sistema.alumnoAgregarEnsenianza(alumno26.getId(), leccion9, true);
		sistema.alumnoAgregarEnsenianza(alumno38.getId(), leccion15, true);
		sistema.alumnoAgregarEnsenianza(alumno39.getId(), leccion3, false);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno40.getId(), leccion9, false);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion24, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion2, false);
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion2, false);
		sistema.alumnoAgregarEnsenianza(alumno34.getId(), leccion22, true);
		sistema.alumnoAgregarEnsenianza(alumno7.getId(), leccion7, true);
		sistema.alumnoAgregarEnsenianza(alumno33.getId(), leccion15, true);
		sistema.alumnoAgregarEnsenianza(alumno7.getId(), leccion13, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion24, true);
		sistema.alumnoAgregarEnsenianza(alumno30.getId(), leccion18, true);
		sistema.alumnoAgregarEnsenianza(alumno26.getId(), leccion16, true);
		sistema.alumnoAgregarEnsenianza(alumno33.getId(), leccion4, true);
		sistema.alumnoAgregarEnsenianza(alumno32.getId(), leccion22, true);
		sistema.alumnoAgregarEnsenianza(alumno36.getId(), leccion1, false);
		sistema.alumnoAgregarEnsenianza(alumno35.getId(), leccion9, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion14, true);
		sistema.alumnoAgregarEnsenianza(alumno14.getId(), leccion14, false);
		sistema.alumnoAgregarEnsenianza(alumno5.getId(), leccion4, false);
		sistema.alumnoAgregarEnsenianza(alumno31.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion11, true);
		sistema.alumnoAgregarEnsenianza(alumno11.getId(), leccion6, false);
		sistema.alumnoAgregarEnsenianza(alumno32.getId(), leccion23, false);
		sistema.alumnoAgregarEnsenianza(alumno35.getId(), leccion9, false);
		sistema.alumnoAgregarEnsenianza(alumno23.getId(), leccion17, false);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion23, false);
		sistema.alumnoAgregarEnsenianza(alumno6.getId(), leccion10, true);
		sistema.alumnoAgregarEnsenianza(alumno35.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion26, false);
		sistema.alumnoAgregarEnsenianza(alumno28.getId(), leccion18, false);
		sistema.alumnoAgregarEnsenianza(alumno40.getId(), leccion9, true);
		sistema.alumnoAgregarEnsenianza(alumno15.getId(), leccion5, false);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion17, true);
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion4, true);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion8, false);
		sistema.alumnoAgregarEnsenianza(alumno11.getId(), leccion14, true);
		sistema.alumnoAgregarEnsenianza(alumno3.getId(), leccion9, false);
		sistema.alumnoAgregarEnsenianza(alumno38.getId(), leccion15, true);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion12, true);
		sistema.alumnoAgregarEnsenianza(alumno2.getId(), leccion19, true);
		sistema.alumnoAgregarEnsenianza(alumno32.getId(), leccion23, true);
		sistema.alumnoAgregarEnsenianza(alumno15.getId(), leccion13, false);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion6, true);
		sistema.alumnoAgregarEnsenianza(alumno4.getId(), leccion13, true);
		sistema.alumnoAgregarEnsenianza(alumno18.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno26.getId(), leccion26, false);
		sistema.alumnoAgregarEnsenianza(alumno13.getId(), leccion8, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion23, true);
		sistema.alumnoAgregarEnsenianza(alumno28.getId(), leccion18, true);
		sistema.alumnoAgregarEnsenianza(alumno24.getId(), leccion25, false);
		sistema.alumnoAgregarEnsenianza(alumno29.getId(), leccion6, true);
		sistema.alumnoAgregarEnsenianza(alumno33.getId(), leccion11, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion16, true);
		sistema.alumnoAgregarEnsenianza(alumno25.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion7, true);
		sistema.alumnoAgregarEnsenianza(alumno20.getId(), leccion8, true);
		sistema.alumnoAgregarEnsenianza(alumno36.getId(), leccion6, true);
		sistema.alumnoAgregarEnsenianza(alumno40.getId(), leccion2, true);
		sistema.alumnoAgregarEnsenianza(alumno3.getId(), leccion10, true);
		sistema.alumnoAgregarEnsenianza(alumno13.getId(), leccion23, true);
		sistema.alumnoAgregarEnsenianza(alumno39.getId(), leccion8, true);
		sistema.alumnoAgregarEnsenianza(alumno11.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno20.getId(), leccion19, false);
		sistema.alumnoAgregarEnsenianza(alumno13.getId(), leccion8, true);
		sistema.alumnoAgregarEnsenianza(alumno20.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno20.getId(), leccion5, true);
		sistema.alumnoAgregarEnsenianza(alumno9.getId(), leccion20, false);
		sistema.alumnoAgregarEnsenianza(alumno32.getId(), leccion2, false);
		sistema.alumnoAgregarEnsenianza(alumno16.getId(), leccion18, false);
		sistema.alumnoAgregarEnsenianza(alumno8.getId(), leccion16, false);
		sistema.alumnoAgregarEnsenianza(alumno3.getId(), leccion5, true);
		sistema.alumnoAgregarEnsenianza(alumno5.getId(), leccion19, false);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion13, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno39.getId(), leccion5, true);
		sistema.alumnoAgregarEnsenianza(alumno22.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno31.getId(), leccion5, true);
		sistema.alumnoAgregarEnsenianza(alumno2.getId(), leccion26, true);
		sistema.alumnoAgregarEnsenianza(alumno40.getId(), leccion4, true);
		sistema.alumnoAgregarEnsenianza(alumno30.getId(), leccion23, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion10, true);
		sistema.alumnoAgregarEnsenianza(alumno13.getId(), leccion8, false);
		sistema.alumnoAgregarEnsenianza(alumno31.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno25.getId(), leccion5, true);
		sistema.alumnoAgregarEnsenianza(alumno11.getId(), leccion18, false);
		sistema.alumnoAgregarEnsenianza(alumno17.getId(), leccion25, false);
		sistema.alumnoAgregarEnsenianza(alumno25.getId(), leccion19, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion11, true);
		sistema.alumnoAgregarEnsenianza(alumno8.getId(), leccion4, true);
		sistema.alumnoAgregarEnsenianza(alumno11.getId(), leccion24, true);
		sistema.alumnoAgregarEnsenianza(alumno3.getId(), leccion14, false);
		sistema.alumnoAgregarEnsenianza(alumno26.getId(), leccion16, false);
		sistema.alumnoAgregarEnsenianza(alumno14.getId(), leccion16, true);
		sistema.alumnoAgregarEnsenianza(alumno18.getId(), leccion4, true);
		sistema.alumnoAgregarEnsenianza(alumno18.getId(), leccion5, true);
		sistema.alumnoAgregarEnsenianza(alumno10.getId(), leccion25, true);
		sistema.alumnoAgregarEnsenianza(alumno33.getId(), leccion8, false);
		sistema.alumnoAgregarEnsenianza(alumno36.getId(), leccion15, false);
		sistema.alumnoAgregarEnsenianza(alumno17.getId(), leccion17, false);
		sistema.alumnoAgregarEnsenianza(alumno20.getId(), leccion15, false);
		sistema.alumnoAgregarEnsenianza(alumno29.getId(), leccion11, true);
		sistema.alumnoAgregarEnsenianza(alumno9.getId(), leccion24, false);
		sistema.alumnoAgregarEnsenianza(alumno13.getId(), leccion9, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion24, true);
		sistema.alumnoAgregarEnsenianza(alumno29.getId(), leccion19, true);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno6.getId(), leccion13, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion17, true);
		sistema.alumnoAgregarEnsenianza(alumno5.getId(), leccion4, false);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion4, true);
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion21, true);
		sistema.alumnoAgregarEnsenianza(alumno30.getId(), leccion21, true);
		sistema.alumnoAgregarEnsenianza(alumno6.getId(), leccion22, true);
		sistema.alumnoAgregarEnsenianza(alumno18.getId(), leccion15, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion26, false);
		sistema.alumnoAgregarEnsenianza(alumno8.getId(), leccion10, false);
		sistema.alumnoAgregarEnsenianza(alumno12.getId(), leccion13, false);
		sistema.alumnoAgregarEnsenianza(alumno25.getId(), leccion8, false);
		sistema.alumnoAgregarEnsenianza(alumno15.getId(), leccion21, true);
		sistema.alumnoAgregarEnsenianza(alumno7.getId(), leccion9, false);
		sistema.alumnoAgregarEnsenianza(alumno36.getId(), leccion15, true);
		sistema.alumnoAgregarEnsenianza(alumno3.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno33.getId(), leccion9, true);
		sistema.alumnoAgregarEnsenianza(alumno37.getId(), leccion17, false);
		sistema.alumnoAgregarEnsenianza(alumno13.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno4.getId(), leccion17, true);
		sistema.alumnoAgregarEnsenianza(alumno31.getId(), leccion14, false);
		sistema.alumnoAgregarEnsenianza(alumno9.getId(), leccion22, false);
		sistema.alumnoAgregarEnsenianza(alumno26.getId(), leccion1, false);
		sistema.alumnoAgregarEnsenianza(alumno15.getId(), leccion23, true);
		sistema.alumnoAgregarEnsenianza(alumno4.getId(), leccion1, false);
		sistema.alumnoAgregarEnsenianza(alumno29.getId(), leccion6, false);
		sistema.alumnoAgregarEnsenianza(alumno35.getId(), leccion12, false);
		sistema.alumnoAgregarEnsenianza(alumno18.getId(), leccion1, true);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno14.getId(), leccion7, true);
		sistema.alumnoAgregarEnsenianza(alumno22.getId(), leccion13, true);
		sistema.alumnoAgregarEnsenianza(alumno30.getId(), leccion17, true);
		sistema.alumnoAgregarEnsenianza(alumno33.getId(), leccion17, true);
		sistema.alumnoAgregarEnsenianza(alumno22.getId(), leccion10, true);
		sistema.alumnoAgregarEnsenianza(alumno25.getId(), leccion23, true);
		sistema.alumnoAgregarEnsenianza(alumno6.getId(), leccion2, false);
		sistema.alumnoAgregarEnsenianza(alumno1.getId(), leccion11, true);
		sistema.alumnoAgregarEnsenianza(alumno23.getId(), leccion12, true);
		sistema.alumnoAgregarEnsenianza(alumno28.getId(), leccion11, false);
		sistema.alumnoAgregarEnsenianza(alumno30.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno18.getId(), leccion21, true);
		sistema.alumnoAgregarEnsenianza(alumno25.getId(), leccion15, true);
		sistema.alumnoAgregarEnsenianza(alumno30.getId(), leccion21, true);
		sistema.alumnoAgregarEnsenianza(alumno23.getId(), leccion3, true);
		sistema.alumnoAgregarEnsenianza(alumno19.getId(), leccion26, true);
		sistema.alumnoAgregarEnsenianza(alumno24.getId(), leccion6, true);
		sistema.alumnoAgregarEnsenianza(alumno21.getId(), leccion9, true);
		sistema.alumnoAgregarEnsenianza(alumno13.getId(), leccion18, true);
		sistema.alumnoAgregarEnsenianza(alumno10.getId(), leccion11, false);
		sistema.alumnoAgregarEnsenianza(alumno16.getId(), leccion20, false);
		sistema.alumnoAgregarEnsenianza(alumno36.getId(), leccion13, true);
		sistema.alumnoAgregarEnsenianza(alumno4.getId(), leccion21, false);
		sistema.alumnoAgregarEnsenianza(alumno28.getId(), leccion22, false);
		sistema.alumnoAgregarEnsenianza(alumno36.getId(), leccion7, false);
		sistema.alumnoAgregarEnsenianza(alumno20.getId(), leccion7, true);
		sistema.alumnoAgregarEnsenianza(alumno5.getId(), leccion6, true);
		sistema.alumnoAgregarEnsenianza(alumno17.getId(), leccion25, true);
		sistema.alumnoAgregarEnsenianza(alumno16.getId(), leccion3, false);
		sistema.alumnoAgregarEnsenianza(alumno5.getId(), leccion17, true);
		sistema.alumnoAgregarEnsenianza(alumno8.getId(), leccion12, true);
		sistema.alumnoAgregarEnsenianza(alumno26.getId(), leccion14, true);
		sistema.alumnoAgregarEnsenianza(alumno27.getId(), leccion23, true);
		sistema.alumnoAgregarEnsenianza(alumno7.getId(), leccion24, false);
		sistema.alumnoAgregarEnsenianza(alumno38.getId(), leccion4, false);
		sistema.alumnoAgregarEnsenianza(alumno7.getId(), leccion21, false);
		sistema.alumnoAgregarEnsenianza(alumno33.getId(), leccion21, false);
		sistema.alumnoAgregarEnsenianza(alumno2.getId(), leccion20, true);
		sistema.alumnoAgregarEnsenianza(alumno31.getId(), leccion10, true);
		sistema.alumnoAgregarEnsenianza(alumno10.getId(), leccion19, true);
	} 
}