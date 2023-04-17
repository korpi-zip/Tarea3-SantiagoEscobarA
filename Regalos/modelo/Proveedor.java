package modelo;

public class Proveedor {
	private String nombre;
	private Double precioEnvio;
			
	
	public Proveedor(String nombre, Double precioEnvio) {
		this.nombre = nombre;
		this.precioEnvio = precioEnvio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecioEnvio() {
		return precioEnvio;
	}
	public void setPrecioEnvio(Double precioEnvio) {
		this.precioEnvio = precioEnvio;
	}
	
	
	
}
