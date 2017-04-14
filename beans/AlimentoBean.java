package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import negocio.Alimento;

@Entity
@Table(name="alimentos") 
public class AlimentoBean {
	@Id
	@Column(name="alimentoId")
	private int Id;
	private String nombre;
	private int proteinas;
	private int precio;
	
	/*GETTES Y SETTERS*/
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getProteinas() {
		return proteinas;
	}
	public void setProteinas(int proteinas) {
		this.proteinas = proteinas;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	/*NEGOCIO*/
	public Alimento pasarNegocio() {
		Alimento alimento = new Alimento(Id, nombre, proteinas, precio);
		return alimento;
	}
}
