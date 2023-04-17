package modelo;

public class Producto {
	private String nombre;
	private int edad;
	private Double precio;
	private Proveedor proveedor;
	
	/**
	 * 
	 * @param nombre Nombre del Producto
	 * @param edad Edad recomendada del Producto
	 * @param precio Precio del producto
	 * @param proveedor Proveedor del producto
	 */
	public Producto(String nombre, int edad, Double precio, Proveedor proveedor) {
		this.nombre = nombre;
		this.edad = edad;
		this.precio = precio;
		this.proveedor = proveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
	
}

