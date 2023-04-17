package vista;

import java.io.IOException;
import java.util.Scanner;
import control.ControlConsultas;

//////////////////////////////////////////////////////////////////
// Tarea 2 - Santiago Escobar A.								//
// Programa para buscar regalos segun la edad y precioMaximo.	//
// Universidad de Caldas - 2023									//
//////////////////////////////////////////////////////////////////

public class Main {

	public static void main(String[] args) {
		
		//Creamos una instancia del control de pruebas.
		ControlConsultas prueba = new ControlConsultas();
		
		//Se maneja la excepcion IOException.
		try {
			
			//Se piden los datos edad y precioMaximo
			Scanner scanner = new Scanner(System.in);
			System.out.print("Introduce la edad del niño: ");
			int edad = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Introduce el precio Maximo a pagar: ");
			Double precioMaximo = scanner.nextDouble();
			scanner.close();
			
			//Se imprime la consulta de regalos para los datos pedidos.
			System.out.print(prueba.buscarRegalos(edad, precioMaximo));
			
		} catch (IOException e) {
			//Errores en la lectura de archivos.
			System.out.print("Error al leer los archivos JSON");
		}
		
		//prueba1();
		//prueba2();
		//prueba3();
		
	}
	
	//Resumen de la prueba1 para facilitar las pruebas
	private static void prueba1() {
		
		ControlConsultas prueba1 = new ControlConsultas();
		try {
			System.out.print(prueba1.buscarRegalos(5, 90000d));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Error al leer base de datos - Verificar Ruta");
		}
		
	}
	
	//Resumen de la prueba2 para facilitar las pruebas
	private static void prueba2() {
		
		ControlConsultas prueba2 = new ControlConsultas();
		try {
			System.out.print(prueba2.buscarRegalos(7, 50000d));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Error al leer base de datos - Verificar Ruta");
		}
	}
	
	//Resumen de la prueba3 para facilitar las pruebas
	private static void prueba3() {
		
		ControlConsultas prueba3 = new ControlConsultas();
		try {
			System.out.print(prueba3.buscarRegalos(8, 50000d));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Error al leer base de datos - Verificar Ruta");
		}
	}

}
