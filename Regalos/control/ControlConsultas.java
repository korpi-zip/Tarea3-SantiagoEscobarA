package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import modelo.Producto;
import modelo.Proveedor;
import modelo.Tienda;

public class ControlConsultas {
	
	private Tienda tienda;
	private final String RUTAPRODUCTOS = "regalos/data/productos.json";
	private final String RUTAPROVEEDORES = "regalos/data/proveedores.json";

	
	/**
	 * Metodo para buscar entre los regalos de la tienda una lista de regalos
	 * adecuados para cierta edad y un maximo de precio total.
	 * @param edad
	 * @param precioMaximo
	 * @return String con los regalos filtrados.
	 * @throws IOException
	 */
	public String buscarRegalos(int edad, Double precioMaximo) throws IOException {
		
		//Se cargan los datos de los JSON en cada consulta ya que pueden
		//	ser modificados por otros
		this.cargarDatos(RUTAPRODUCTOS, RUTAPROVEEDORES);
		
		//Filramos inicialmente los regalos por edad en regalosFiltrados usando
		//	un servicio de la tienda.
		List<Producto> regalosFiltrados = this.tienda.buscarProductoPorEdad(edad);
		
		//En el bloque for se busca entre los regalos filtrados y resta del total
		//	disponible su precio total.  Toma la informacion de los regalos si están 
		//	pueden ser pagados y la agrega a el String "resutado".
		Double total = 0d;
		String resultado= "";		
		for (Producto regalo: regalosFiltrados) {
			Double precioTotal= regalo.getPrecio()+regalo.getProveedor().getPrecioEnvio();
			if (precioTotal<=precioMaximo-total) {
				total=total+precioTotal;
				
				resultado=resultado+
						regalo.getNombre()+" - precio base: $"+regalo.getPrecio()+
						" - precio de envío: $"+regalo.getProveedor().getPrecioEnvio()+
						"- precio total: $"+precioTotal+"\n";
			}
		}
		
		//Si el String "resultado" está vacio significa que no encontro ningun juguete
		// para ese rango de precios o edad.
		if (resultado.isEmpty()) {
			resultado= "No se tiene productos para esa edad o precio\n";
		}
		
		return resultado;

	}
	
	/**
	 * Este metodo busca la informacion en archivos JSON y la carga en la tienda
	 * @param rutaProductos ruta del archivo JSON con los productos
	 * @param rutaProveedores ruta del archivo JSON con los proveedores
	 * @throws IOException
	 */
	private void cargarDatos(String rutaProductos, String rutaProveedores) throws IOException {
		
		//Se crea un lector para traer la informacion de los JSON 
		Lector lector= new Lector();		
		String productos = lector.leerJson(rutaProductos);	
		String proveedores = lector.leerJson(rutaProveedores);		
					
		//Se crea una instancia de la Tienda que recibe una lista de Productos. 
		//El metodo crearProductos se encarga de tomar la informacion de los String
		//	y convertirla en una lista de Productos entendibles por la tienda.
		this.tienda = new Tienda(this.crearProductos(productos, proveedores));
	}
	
	
	/**
	 * Crea una lista de productos para una Tienda.
	 * @param productos String con la informacion de los productos
	 * @param proveedores String con la informacion de los proveedores
	 * @return Lista de productos
	 */
	private List<Producto> crearProductos(String productos, String proveedores){
		
		//Se usa la libreria de JSONArray y JSONObject para transformar el string
		// en objetos de facil manipulacion.
		JSONArray JSONArrayProductos = new JSONArray(productos);
		
		//Se recorre el arreglo de productos para crear un objeto Producto en cada 
		// iteración y añadirla a listaProductos. 
		List<Producto> listaProductos = new ArrayList<>();		
		for (int i = 0; i < JSONArrayProductos.length(); i++) {
			
			//Se toma un elemento del array y lo convierte en JSONObject.
			JSONObject JSONObjectProducto = JSONArrayProductos.getJSONObject(i);
		    
			//Se crean los datos necesarios para crear un producto tomando la
			//	informacion del JSONObject. Para el Proveedor se crea un objeto 
			//  "Proveedor" usando la informaciond del String de proveedores.
			String nombre = JSONObjectProducto.getString("nombre");
			int edad = JSONObjectProducto.getInt("edad");
			Double precio = JSONObjectProducto.getDouble("precio");
			String nombreProveedor = JSONObjectProducto.getString("proveedor");			
			Proveedor proveedor = new Proveedor(nombreProveedor, this.buscarPrecioEnvio(nombreProveedor, proveedores));
			
			Producto producto = new Producto(nombre, edad, precio, proveedor);
			
			listaProductos.add(producto);
			
		}
		
		return listaProductos;
	}
	
	/**
	 * Busca el precio de Envio de un proveedor especifico .
	 * @param nombreProveedor proveedor que desea consultar.
	 * @param proveedores String con la informacion de proveedores.
	 * @return	precio de envio de un proveedor.
	 */
	private Double buscarPrecioEnvio(String nombreProveedor, String proveedores) {

		JSONArray JSONArrayProveedores = new JSONArray(proveedores);
		for (int i = 0; i < JSONArrayProveedores.length(); i++) {
			
			JSONObject JSONObjectProveedor = JSONArrayProveedores.getJSONObject(i);
			
			if (nombreProveedor.equals(JSONObjectProveedor.getString("nombre"))) {
				return JSONObjectProveedor.getDouble("precioEnvio");
			}

		}
		
		return null;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	
	
}
