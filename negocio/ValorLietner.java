package negocio;

import daos.ValorLietnerDao;
import beans.ValorLietnerBean;

public class ValorLietner {
	
	public ValorLietner(int nivel, int desde, int hasta) {
		this.nivel = nivel;
		this.desde = desde;
		this.hasta = hasta;
		ValorLietnerDao.getInstance().grabar(this.pasarBean());
	}
	
	public ValorLietner() {
		
	}

	private int nivel;
	private int desde;
	private int hasta;
	
	/*GETTERS Y SETTERS*/
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getDesde() {
		return desde;
	}
	public void setDesde(int desde) {
		this.desde = desde;
	}
	public int getHasta() {
		return hasta;
	}

	public void setHasta(int hasta) {
		this.hasta = hasta;
	}

	/*BEAN*/
	public ValorLietnerBean pasarBean() {
		ValorLietnerBean valorLietnerBean = new ValorLietnerBean();
		valorLietnerBean.setNivel(nivel);
		valorLietnerBean.setDesde(desde);
		valorLietnerBean.setHasta(hasta);
		return valorLietnerBean;
	}
}
