package controladores;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		menuPrincipal();
	}

	/**
	 * @throws ParseException
	 * 
	 */
	private static void menuPrincipal() throws ParseException {

		int opcionElegida;
		do {
			System.out.println("\n\t\t\tGESTIÓN DE VENTAS DE COCHES");

			System.out.println("\n\t1.- Gestión de fabricantes.");
			System.out.println("\t2.- Gestión de concesionarios.");
			System.out.println("\t3.- Gestión de ventas.");
			System.out.println("\t4.- Gestión de clientes.");
			System.out.println("\t5.- Gestión de coches.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");

			opcionElegida = Utils.getIntConsola(0, 5);

			switch (opcionElegida) {
			case 0:
				System.out.println("\n!Qué tenga un feliz día!");
				break;
			case 1:
				GestionFabricante.menuGestion();
				break;

			case 2:
				GestionConcesionario.menuGestion();
				break;
			case 3:
				GestionVenta.menuGestion();
				break;
			case 4:
				GestionCliente.menuGestion();
				break;
			case 5:
				GestionCoche.menuGestion();
				break;
			}

		} while (opcionElegida != 0);
	}

}
