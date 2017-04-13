package negocio;

import beans.LietnerBean;

public class Lietner {
	
	
	public Lietner(int pos, int valor) {
		Id = pos;
		Valor = valor;
	}
	private Integer Id;
	private int Valor;
	
	/*GETTERS Y SETTERS*/
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public int getValor() {
		return Valor;
	}
	public void setValor(int valor) {
		Valor = valor;
	}
	public LietnerBean pasarBean() {
		LietnerBean lietnerBean = new LietnerBean();
		lietnerBean.setId(Id);
		lietnerBean.setValor(Valor);
		return lietnerBean;
		
	}
	
}
