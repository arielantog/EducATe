package negocio;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {
	
	public long fechaActual() {
		Calendar fecha = new GregorianCalendar();
		//TODO Agregar horas, minutos y segundos ¿Falla por tamaño del tipo de dato?
		long fecha2 = fecha.get(Calendar.YEAR) * 10000 +
						 fecha.get(Calendar.MONTH) * 100 + 
						 fecha.get(Calendar.DAY_OF_MONTH * 1);
		return fecha2;
	}
	
}
