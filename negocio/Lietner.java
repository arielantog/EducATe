package negocio;

import daos.LietnerDao;
import beans.LietnerBean;

public class Lietner {
	
	
	public Lietner(int pos, int valor) {
		id = pos;
		Valor = valor;
	}
	public Lietner() {
	}
	
	private int id;
	private int Valor;
	
	public Integer[] calcularLietner() {
		Integer[] lietnerValores = cargarLietner();
		Integer[] lietners = new Integer[100];
		int contador = 0;
		@SuppressWarnings("unused")
		int posicion = 0;
		int cantidad = cantidadVector(lietnerValores);
		boolean noPrimero = false;
		//Agrego el 0
		for (int i = 0;i<lietnerValores[0];i++){
			lietners[i] = contador;
			posicion = i;
		}
		contador++;
		posicion++;
		
		//Por cada otro nivel que haya
		for (int i = 1;i<=cantidad;i++){
			//Copio la cadena
			int cantidadAct = cantidadVector(lietners);
			for (int j = 0;j<=cantidadAct;j++)
			{
				lietners[cantidadAct+j+1] = lietners[j];
			}
			cantidadAct = cantidadVector(lietners);
			//Agrego el 0
			if (noPrimero){
				for (int j = 0;j<lietnerValores[0];j++){
					lietners[cantidadAct+1] = 0;
					cantidadAct++;
				}
			}
			noPrimero = true;
			//Agrego el nuevo valor
			for(int j = 0;j<lietnerValores[cantidad];j++){
				lietners[cantidadAct+j+1] = contador;
			}
			contador++;
			
		}
		
		return lietners;
	}

	private int cantidadVector(Integer[] lietnerValores) {
		int cantidad = 0;
		int c = 0;
		while (lietnerValores[c]!= null){
			cantidad = c;
			c++;
		}
		return cantidad;
	}

	private Integer[] cargarLietner() {
		Integer[] lietnerValores = new Integer[10];
		lietnerValores = LietnerDao.getInstance().cargarValores();
		if (lietnerValores[0] == null){
			//Default
			lietnerValores[0] = 1;
			lietnerValores[1] = 1;
			lietnerValores[2] = 1;
			lietnerValores[3] = 1;
			lietnerValores[4] = 1;
		}
		return lietnerValores;
	}
	
	/*GETTERS Y SETTERS*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getValor() {
		return Valor;
	}
	public void setValor(int valor) {
		Valor = valor;
	}
	/*BEAN*/
	public LietnerBean pasarBean() {
		LietnerBean lietnerBean = new LietnerBean();
		lietnerBean.setId(id);
		lietnerBean.setValor(Valor);
		return lietnerBean;
		
	}
	
}
