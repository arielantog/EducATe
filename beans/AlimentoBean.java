package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.AlimentoDTO;
import negocio.Alimento;

@Entity
@Table(name="alimentos") 
public class AlimentoBean {
	@Id
	@Column(name="alimentoId")
	private int id;
	private String nombre;
	private int proteinas;
	private int precio;
	private boolean activo;
	
	/*GETTES Y SETTERS*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public Alimento pasarNegocio() {
		Alimento alimento = new Alimento(id, nombre, proteinas, precio,activo);
		return alimento;
	}
	/*DTO*/
	public AlimentoDTO pasarDTO() {
		AlimentoDTO alimento = new AlimentoDTO(id, nombre, proteinas, precio,activo);
		return alimento;
	}
	
}
