package negocio;

import daos.AlimentoDao;
import beans.AlimentoBean;

public class Alimento {
	
	
	public Alimento(String nombre, int proteinas, int precio) {
		id = ID++;
		this.nombre = nombre;
		this.proteinas = proteinas;
		this.precio = precio;
		this.activo = true;
		AlimentoDao.getInstance().grabar(pasarBean());
	}
	
	public Alimento(int id, String nombre, int proteinas, int precio, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.proteinas = proteinas;
		this.precio = precio;
		this.activo = activo;
	}

	private static int ID = 1;
	private int id;
	private String nombre;
	private int proteinas;
	private int precio;
	private boolean activo;
	
	/*GETTES Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
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
	/*BEAN*/
	public AlimentoBean pasarBean() {
		AlimentoBean alimentoBean = new AlimentoBean();
		alimentoBean.setId(getId());
		alimentoBean.setNombre(nombre);
		alimentoBean.setProteinas(proteinas);
		alimentoBean.setPrecio(precio);
		alimentoBean.setActivo(activo);
		return alimentoBean;
	}
	public void eliminar() {
		activo = false;
		AlimentoDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String nombre, int proteinas, int precio) {
		setNombre(nombre);
		setProteinas(proteinas);
		setPrecio(precio);
		activo = true;
		AlimentoDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String nombre, int proteinas, int precio) {
		setNombre(nombre);
		setProteinas(proteinas);
		setPrecio(precio);
		AlimentoDao.getInstance().actualizar(pasarBean());
	}
}
