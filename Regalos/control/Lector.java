package control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lector {
	
	/**
	 * Lee un archivo JSON en una ruta especifica.
	 * @param ruta	ruta del archivo JSON.
	 * @return	String con el contenido del archivo JSON
	 * @throws IOException
	 */
	public String leerJson(String ruta) throws IOException {
		String json = Files.readString(Path.of(ruta));		
		return json;
	}
	
}
