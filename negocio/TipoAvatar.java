package negocio;

import java.util.ArrayList;
import java.util.List;

import daos.TipoAvatarDao;
import beans.AlimentoBean;
import beans.TipoAvatarBean;

public class TipoAvatar {
	
	
	public TipoAvatar(String nombre, int alimentoMax, int tiempoHambre, int precioEvolucion, int precioRevivir,String url) {
		id = ID++;
		this.nombre = nombre;
		this.alimentoMax = alimentoMax;
		this.tiempoHambre = tiempoHambre;
		this.precioEvolucion = precioEvolucion;
		this.precioRevivir = precioRevivir;
		this.alimentos = new ArrayList<Alimento>();
		this.activo = true;
		this.url = url;
		TipoAvatarDao.getInstance().grabar(pasarBean());
	}
	
	public TipoAvatar(int id, String nombre, int alimentoMax,
			int tiempoHambre, int precioEvolucion, int precioRevivir, boolean activo, String url) {
		this.id = id;
		this.nombre = nombre;
		this.alimentoMax = alimentoMax;
		this.tiempoHambre = tiempoHambre;
		this.precioEvolucion = precioEvolucion;
		this.precioRevivir = precioRevivir;
		this.alimentos = new ArrayList<Alimento>();
		this.activo = activo;
		this.url = url;
	}

	private static int ID = 1;
	private int id;
	private String nombre;
	private int alimentoMax;
	private int tiempoHambre;
	private int precioEvolucion;
	private int precioRevivir;
	private List<Alimento> alimentos;
	private boolean activo;
	private String url;
	
	public int agregarAlimento(Alimento alimento, boolean b) {
		if (!tengoAlimento(alimento)){
			alimentos.add(alimento);
			TipoAvatarDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El alimento ya existe en el Tipo de Avatar");
		}
		return 0;
	}

	public boolean tengoAlimento(Alimento alimento) {
		for (Alimento alimento2: alimentos){
			if (alimento.getId() == alimento2.getId())
				return true;
		}
		return TipoAvatarDao.getInstance().tengoAlimento(this.getId(), alimento.getId());
	}

	public int quitarAlimento(int alimento) {
		Alimento alimento2 = buscarAlimento(alimento);
		if (alimento2 != null){
			alimentos.remove(alimento2);
			TipoAvatarDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El alimento no existe en el Tipo de Avatar");
		}
		return 0;
	}

	private Alimento buscarAlimento(int alimento) {
		for (Alimento alimento2: alimentos){
			if (alimento2.getId() == alimento)
				return alimento2;
		}
		return TipoAvatarDao.getInstance().buscarAlimento(getId(),alimento);
	}
	
	public void agregarAlimento(Alimento alimento) {
		alimentos.add(alimento);
	}
	
	public void eliminar() {
		activo = false;
		TipoAvatarDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String nombre, int alimentoMax, int tiempoHambre, int precioEvolucion, int precioRevivir, String url) {
		setNombre(nombre);
		setAlimentoMax(alimentoMax);
		setTiempoHambre(tiempoHambre);
		setPrecioEvolucion(precioEvolucion);
		setPrecioRevivir(precioRevivir);
		activo = true;
		TipoAvatarDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String nombre, int alimentoMax, int tiempoHambre, int precioEvolucion, int precioRevivir, String url) {
		setNombre(nombre);
		setAlimentoMax(alimentoMax);
		setTiempoHambre(tiempoHambre);
		setPrecioEvolucion(precioEvolucion);
		setPrecioRevivir(precioRevivir);
		setUrl(url);
		TipoAvatarDao.getInstance().actualizar(pasarBean());
	}
	
	/*GETTERS Y SETTERS*/
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
	public int getAlimentoMax() {
		return alimentoMax;
	}
	public void setAlimentoMax(int alimentoMax) {
		this.alimentoMax = alimentoMax;
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
	public List<Alimento> getAlimentos() {
		return alimentos;
	}
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
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
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/*BEAN*/
	public TipoAvatarBean pasarBean() {
		TipoAvatarBean tipoAvatarBean = new TipoAvatarBean();
		tipoAvatarBean.setId(getId());
		tipoAvatarBean.setNombre(nombre);
		tipoAvatarBean.setAlimentoMax(alimentoMax);
		tipoAvatarBean.setTiempoHambre(tiempoHambre);
		tipoAvatarBean.setPrecioEvolucion(precioEvolucion);
		tipoAvatarBean.setPrecioRevivir(precioRevivir);
		for (Alimento alimento: alimentos){
			AlimentoBean alimentoBean = alimento.pasarBean();
			tipoAvatarBean.agregarAlimento(alimentoBean);
		}
		tipoAvatarBean.setActivo(activo);
		tipoAvatarBean.setUrl(url);
		return tipoAvatarBean;
	}
}
