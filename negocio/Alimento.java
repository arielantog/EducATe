package negocio;

import daos.AlimentoDao;
import beans.AlimentoBean;

public class Alimento {
	
	
	public Alimento(String nombre, int proteinas, int precio) {
		Id = ID++;
		this.nombre = nombre;
		this.proteinas = proteinas;
		this.precio = precio;
		AlimentoDao.getInstance().grabar(pasarBean());
	}
	
	public Alimento(int id, String nombre, int proteinas, int precio) {
		super();
		Id = id;
		this.nombre = nombre;
		this.proteinas = proteinas;
		this.precio = precio;
	}

	private static int ID = 1;
	private int Id;
	private String nombre;
	private int proteinas;
	private int precio;
	
	/*GETTES Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
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
	/*BEAN*/
	public AlimentoBean pasarBean() {
		AlimentoBean alimentoBean = new AlimentoBean();
		alimentoBean.setId(getId());
		alimentoBean.setNombre(nombre);
		alimentoBean.setProteinas(proteinas);
		alimentoBean.setPrecio(precio);
		return alimentoBean;
	}
}
