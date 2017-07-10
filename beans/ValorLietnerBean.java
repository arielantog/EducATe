package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import negocio.ValorLietner;

@Entity
@Table(name="valorLietner")
public class ValorLietnerBean {
	@Id
	@Column(name="nivel")
	private int nivel;
	private int desde;
	private int hasta;
	
	
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
	
	/*NEGOCIO*/
	public ValorLietner pasarNegocio() {
		ValorLietner valorLietner = new ValorLietner();
		valorLietner.setNivel(nivel);
		valorLietner.setDesde(desde);
		valorLietner.setHasta(hasta);
		return valorLietner;
	}
}