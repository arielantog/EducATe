package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import daos.LietnerDao;
import daos.ValorLietnerDao;
import beans.LietnerBean;

public class Lietner {
	
	
	public Lietner(int id) {
		setId(id);
		valoresLietner = new ArrayList<ValorLietner>();
		LietnerDao.getInstance().grabar(this.pasarBean());
	}
	
	public Lietner() {
		valoresLietner = new ArrayList<ValorLietner>();
	}
	
	private int id;
	private int iteracion = 0;
	private List<ValorLietner> valoresLietner;
	
	public int agregarValorLietner(int nivel, int desde, int hasta) {
		ValorLietner valorLietner = new ValorLietner(nivel,desde,hasta);
		valoresLietner.add(valorLietner);
		LietnerDao.getInstance().actualizar(this.pasarBean());
		return valorLietner.getNivel();
	}
	
	public void cargarValores() {
		List<ValorLietner> valoresLietner = new ArrayList<ValorLietner>();
		valoresLietner = ValorLietnerDao.getInstance().cargarValores();
		for (ValorLietner valorLietner: valoresLietner)
			this.valoresLietner.add(valorLietner);
	}
	
	public int getNivel() {
		//En caso de que no haya encontrado ninguno, vuelve a empezar.
		if (iteracion == 99)
			iteracion = 0;
		
		//Genera un número Random entre el número de iteración y 99.
		int aleatorio = new Random(System.currentTimeMillis()).nextInt(100-iteracion)+iteracion;
		for (ValorLietner valorLietner: valoresLietner){
			if (aleatorio >= valorLietner.getDesde() && aleatorio <= valorLietner.getHasta())
				return valorLietner.getNivel();
		}
		return 0;
	}
	
	public boolean existeNivel(int nivel) {
		for (ValorLietner valorLietner: valoresLietner)
			if (valorLietner.getNivel() == nivel)
				return true;
		return false;
	}
	
	public int modificarValor(int nivel, int desde, int hasta) {
		for (ValorLietner valorLietner: valoresLietner)
			if (valorLietner.getNivel() == nivel){
				valorLietner.setDesde(desde);
				valorLietner.setHasta(hasta);
				ValorLietnerDao.getInstance().actualizar(valorLietner.pasarBean());
				return nivel;
			}
		return 0;
	}

	public int eliminarValorLietner(int nivel) {
		for (ValorLietner valorLietner: valoresLietner)
			if (valorLietner.getNivel() == nivel){
				valoresLietner.remove(valorLietner);
				ValorLietnerDao.getInstance().eliminar(valorLietner.pasarBean());
				return nivel;
			}
		return 0;
	}
	
	
	/*GETTERS Y SETTERS*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public List<ValorLietner> getValoresLietner() {
		return valoresLietner;
	}
	public void setValoresLietner(List<ValorLietner> valoresLietner) {
		this.valoresLietner = valoresLietner;
	}
	public int getIteracion() {
		return iteracion;
	}
	public void setIteracion(int iteracion) {
		this.iteracion = iteracion;
	}
	
	/*BEAN*/
	public LietnerBean pasarBean() {
		LietnerBean lietnerBean = new LietnerBean();
		
		lietnerBean.setId(id);
		for (ValorLietner valorLietner: valoresLietner)
			lietnerBean.agregarValorLietner(valorLietner.pasarBean());
		return lietnerBean;
	}
	
}
