package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lietner")
public class LietnerBean {
	@Id
	@Column(name="lietnerId")
	private int id;
	@OneToMany
	@JoinColumn(name="lietnerId")
	private List<ValorLietnerBean> valoresLietner;

	public LietnerBean(){
		valoresLietner = new ArrayList<ValorLietnerBean>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public List<ValorLietnerBean> getValoresLietner() {
		return valoresLietner;
	}

	public void setValoresLietner(List<ValorLietnerBean> valoresLietner) {
		this.valoresLietner = valoresLietner;
	}
	
	public void agregarValorLietner(ValorLietnerBean valorLietnet){
		valoresLietner.add(valorLietnet);
	}
}
