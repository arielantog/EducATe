package negocio;

import daos.AlimentoDao;
import dto.AlimentoDTO;
import beans.AlimentoBean;

public class Alimento {
	
	
	public Alimento(String nombre, int proteinas, int precio, String url) {
		id = ID++;
		this.nombre = nombre;
		this.proteinas = proteinas;
		this.precio = precio;
		this.activo = true;
		this.setUrl(url);
		AlimentoDao.getInstance().grabar(pasarBean());
	}
	
	public Alimento(int id, String nombre, int proteinas, int precio, boolean activo, String url) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.proteinas = proteinas;
		this.precio = precio;
		this.activo = activo;
		this.setUrl(url);
	}

	private static int ID = 1;
	private int id;
	private String nombre;
	private int proteinas;
	private int precio;
	private boolean activo;
	private String url;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	/*BEAN*/
	public AlimentoBean pasarBean() {
		AlimentoBean alimentoBean = new AlimentoBean();
		alimentoBean.setId(getId());
		alimentoBean.setNombre(nombre);
		alimentoBean.setProteinas(proteinas);
		alimentoBean.setPrecio(precio);
		alimentoBean.setActivo(activo);
		alimentoBean.setUrl(url);
		return alimentoBean;
	}
	public void eliminar() {
		activo = false;
		AlimentoDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String nombre, int proteinas, int precio, String url) {
		setNombre(nombre);
		setProteinas(proteinas);
		setPrecio(precio);
		activo = true;
		setUrl(url);
		AlimentoDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String nombre, int proteinas, int precio, String url) {
		setNombre(nombre);
		setProteinas(proteinas);
		setPrecio(precio);
		setUrl(url);
		AlimentoDao.getInstance().actualizar(pasarBean());
	}

<<<<<<< HEAD
	/* DTO */
	public AlimentoDTO pasarDTO() {
		AlimentoDTO alimento = new AlimentoDTO(id, getNombre(), getProteinas(), getPrecio(), activo, getUrl());
=======
	/*DTO*/
	public AlimentoDTO pasarDTO() {
		AlimentoDTO alimento = new AlimentoDTO(id, nombre, proteinas, precio,activo,url);
>>>>>>> refs/remotes/origin/master
		return alimento;
	}
}
