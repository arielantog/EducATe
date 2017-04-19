package negocio;

import beans.LietnerBean;

public class Lietner {
	
	
	public Lietner(int pos, int valor) {
		id = pos;
		Valor = valor;
	}
	private int id;
	private int Valor;
	
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
	public LietnerBean pasarBean() {
		LietnerBean lietnerBean = new LietnerBean();
		lietnerBean.setId(id);
		lietnerBean.setValor(Valor);
		return lietnerBean;
		
	}
	
}
