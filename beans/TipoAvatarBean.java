package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import negocio.TipoAvatar;

@Entity
@Table(name="tipoAvatar")
public class TipoAvatarBean {
	@Id
	@Column(name="tipoAvatarId")
	private int Id;
	private String nombre;
	private int alimentoMax;
	private int nivel;
	private int tiempoHambre;
	private int precioEvolucion;
	private int precioRevivir;
	@ManyToMany
	@JoinTable(name="tipoAvatar_alimento",
				joinColumns			= @JoinColumn(name="tipoAvatarID"),
				inverseJoinColumns	= @JoinColumn(name="alimentoId"))
	private List<AlimentoBean> alimentos;
	private boolean activo;
	
	public TipoAvatarBean() {
		alimentos = new ArrayList<AlimentoBean>();
	}
	/*GETTERS Y SETTERS*/
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
	public int getAlimentoMax() {
		return alimentoMax;
	}
	public void setAlimentoMax(int alimentoMax) {
		this.alimentoMax = alimentoMax;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getTiempoHambre() {
		return tiempoHambre;
	}
	public void setTiempoHambre(int tiempoHambre) {
		this.tiempoHambre = tiempoHambre;
	}
	public int getPrecioEvolucion() {
		return precioEvolucion;
	}
	public void setPrecioEvolucion(int precioEvolucion) {
		this.precioEvolucion = precioEvolucion;
	}
	public List<AlimentoBean> getAlimentos() {
		return alimentos;
	}
	public void setAlimentos(List<AlimentoBean> alimentos) {
		this.alimentos = alimentos;
	}
	public void agregarAlimento(AlimentoBean alimentoBean) {
		alimentos.add(alimentoBean);
	}
	public int getPrecioRevivir() {
		return precioRevivir;
	}
	public void setPrecioRevivir(int precioRevivir) {
		this.precioRevivir = precioRevivir;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*NEGOCIO*/
	public TipoAvatar pasarNegocio() {
		TipoAvatar tipoAvatar = new TipoAvatar(Id, nombre, alimentoMax, nivel, tiempoHambre, precioEvolucion, precioRevivir,activo);
		for(AlimentoBean alimento: alimentos){
			tipoAvatar.agregarAlimento(alimento.pasarNegocio());
		}
		return tipoAvatar;
	}
}
