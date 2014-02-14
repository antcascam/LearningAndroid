package es.masterd.cap05.miadapter;

public class AndroidVersion {
	String nombre;
	String version;
	int logo;

	public AndroidVersion(String nombre, String version, int logo) {
		super();
		this.nombre = nombre;
		this.version = version;
		this.logo = logo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getLogo() {
		return logo;
	}

	public void setLogo(int logo) {
		this.logo = logo;
	}
}