


import java.sql.Connection;
import controladores.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;




public class Interfaz {

	public static void main(String[] args) throws ParseException {
			Interfaz();
	}
	
	private static void Interfaz() throws ParseException {

		int opcionElegida;
		do {
			System.out.println("\n\t\tGESTIÓN AUTOS");
			System.out.println("\n\t1.- Gestión de Concesionario.");
			System.out.println("\t2.- Gestión de Coches.");
			System.out.println("\t3.- Gestión de Fabricante.");
			System.out.println("\t4.- Gestión de Clientes.");
			System.out.println("\t5.- Gestión de Venta.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");
			
			opcionElegida = Utils.getIntConsola(0, 5);
			
			switch (opcionElegida) {
			case 0:
				System.out.println("\n!Que tenga un feliz día!");
				break;
			case 1:
				//GestionConcesionario.menuGestion(conn);
				break;
			case 2:
				//GestionCoche.menuGestion(conn);
				break;
			case 3:
				//GestionFabricante.menuGestion(conn);
				break;
			case 4:
				GestionCliente.menuGestion();
				break;
			case 5:
				//GestionVenta.menuGestion(conn);
				break;
			}
			
		} while (opcionElegida != 0);
	}
	
}
