package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tienda {
	
	private List<Producto> productos = new ArrayList<>();

	public Tienda(List<Producto> productos) {
		this.productos = productos;
	}
	
	/**
	 * Busca en la lista de productos los adecuados para cierta edad.
	 * @param edad
	 * @return List de Productos filtrados por edad
	 */
	public List<Producto> buscarProductoPorEdad(int edad) {
		
		List<Producto> productosPorEdad = new ArrayList<>();
		
		for (Producto producto : this.getProductos()) {
			if (producto.getEdad()==edad) {
				productosPorEdad.add(producto);
			}
		}
		
		return productosPorEdad;
		
	}

	private List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
		
}
